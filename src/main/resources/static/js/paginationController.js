export default class paginationController {
    constructor(paginationId = "myPagination",
        tableId = "myTable",
        multiplierId = "myMultiplier",
        currentPage = 1,
        initialRange = 1,
        textPrevious = "Previous",
        textNext = "Next") {
        
        this.paginationId = paginationId;
        this.tableId = tableId;
        this.multiplierId = multiplierId;
        this.currentPage = currentPage;
        this.initialRange = initialRange;
        this.range = initialRange;
        this.textPrevious = textPrevious;
        this.textNext = textNext;

        this.updateAll(this.currentPage);
    }

    createPagination() {
        const $li = $("<li>", {"class": "page-item"});
        const $a = $("<a>", {"class": "page-link", "href": "#" + this.paginationId});
        const arr = [];
        const totalPages = this.updateTotalPages();
        const pagination = $("#" + this.paginationId);

        pagination.html("");

        for (let i=0; i<(totalPages + 2); i++) {
            const $elem1 = $li.clone();
            const $elem2 = $a.clone();
            if (i === 0) {
                $elem1.addClass("disabled");
                $elem2.html(this.textPrevious);
            }
            else if (i === totalPages + 1) {
                if (totalPages === 1) {
                    $elem1.addClass("disabled");
                }
                $elem2.html(this.textNext);
            }
            else {
                if (i === this.currentPage) {
                    $elem1.addClass("active");
                }
                $elem2.html(i)
            }

            arr[i] = $elem1.append($elem2);
        }

        pagination.append(arr);
    }

    updateAll(currentPage) {
        this.updateTableForPagination(currentPage);
        this.createPagination();
        this.updateRange();
        this.updateButtonRangeWithDefault();
    }

    updatePagination(elem) {
        const pagination = $("#" + this.paginationId + " li");
        const index = pagination.index(elem);
        const length = pagination.children().length;
        const totalPages = this.updateTotalPages();
        
        this.updateCurrentPage(index, length, totalPages);
        this.updatePrevButton(index, length, totalPages);
        this.updateNextButton(index, length, totalPages);
        this.updateButtonRange(index, length);
        this.updateFocusCurrentPage();
        this.updateTableForPagination(this.currentPage);
    }

    updateMultiplier() {
        this.currentPage = 1;
        this.updateAll(1);
    }
 
    updateTableForPagination(start) {
        const multiplier = $("#" + this.multiplierId).val();
        const children = $("#" + this.tableId + " tbody tr");

        for (let i=0; i<children.length; i++) {
            const currentChild = children.eq(i);
            if ((i >= (start - 1) * multiplier) && (i <= (start * multiplier) - 1)) {
                currentChild.css("display", "table-row");
            }
            else {
                currentChild.css("display", "none");
            }
        }
    }

    updateTotalPages() {
        const tableChildren = $("#" + this.tableId + " tbody tr");
        const multiplier = $("#" + this.multiplierId).val();

        if (tableChildren.length % multiplier === 0) {
            return Math.floor(tableChildren.length / multiplier);
        }
        else {
            return Math.floor(tableChildren.length / multiplier) + 1;
        }
    }

    updateFocusCurrentPage() {
        let currentChild;
        const children = $("#" + this.paginationId).children("li");
        for (let i = 1; i < children.length - 1; i++) {
            currentChild = children.eq(i);
            currentChild.removeClass("active");
        }

        children.eq(this.currentPage).addClass("active");
    }

    updateCurrentPage(index, length, totalPages) {
        if (index === 0 && this.currentPage > 1) {
            this.currentPage -= 1;
        }

        if (index === (length - 1) && this.currentPage < totalPages) {
            this.currentPage += 1;
        }

        if (index >= 1 && index <= (length - 2)) {
            this.currentPage = index;
        }
    }

    updateRange() {
        if (this.initialRange > this.updateTotalPages()) {
            this.range = this.updateTotalPages();
        }
        else if (this.initialRange <= this.updateTotalPages()) {
            this.range = this.initialRange;
        }
    }

    updatePrevButtonWithNumberPage(index, length) {
        if (index === 1) {
            return true;
        }
        else if (index === (length - 2)) {
            return false;
        }
        else if (index !== 0 && index !== length - 1) {
            return false;
        }
    }

    updateNextButtonWithNumberPage(index, length) {
        if (index === 1) {
            return false;
        }
        else if (index === (length - 2)) {
            return true;
        }
        else if (index !== 0 && index !== length - 1) {
            return false;
        }
    }

    updatePrevButtonWithSelf(index, length, totalPages) {
        if (index === 1) {
            return true;
        }
        else if (index === 0 && totalPages !== 1 && this.currentPage === 1) {
            return true;
        }
        else if (index === length - 1 && totalPages !== 1) {
            return false;
        }
        else if (totalPages === 1) {
            return true;
        }
    }

    updateNextButtonWithSelf(index, length, totalPages) {
        if (index === length - 2) {
            return true;
        }
        else if (index === 0 && totalPages !== 1) {
            return false;
        }
        else if (index === length - 1 && totalPages !== 1 && this.currentPage === totalPages) {
            return true;
        }
        else if (totalPages === 1) {
            return true;
        }
    }

    updatePrevButton(index, length, totalPages) {
        const firstChildren = $("#" + this.paginationId + " li").first();

        if (this.updatePrevButtonWithNumberPage(index, length)) {
            firstChildren.addClass("disabled");
        }
        else {
            firstChildren.removeClass("disabled");
        }

        if (this.updatePrevButtonWithSelf(index, length, totalPages)) {
            firstChildren.addClass("disabled");
        }
        else {
            firstChildren.removeClass("disabled");
        }
    }

    updateNextButton(index, length, totalPages) {
        const lastChildren = $("#" + this.paginationId + " li").last();

        if (this.updateNextButtonWithNumberPage(index, length)) {
            lastChildren.addClass("disabled");
        }
        else {
            lastChildren.removeClass("disabled");
        }

        if (this.updateNextButtonWithSelf(index, length, totalPages)) {
            lastChildren.addClass("disabled");
        }
        else {
            lastChildren.removeClass("disabled");
        }
    }

    updatePrevNextButtonText(text_prev, text_next) {
        const pagination = $("#" + this.paginationId + " li a");
        pagination.first().html(text_prev);
        pagination.last().html(text_next);
        this.textPrevious = text_prev;
        this.textNext = text_next;
    }

    updateButtonRangeWithDefault() {
        const children = $("#" + this.paginationId).children("li");

        for (let i = 1; i < children.length - 1; i++) {
            const currentChild = children.eq(i);
            if (i <= this.range) {
                currentChild.css("display", "list-item");
            }
            else {
                currentChild.css("display", "none");
            }
        }
    }

    updateButtonRange(index, length) {
        let i;
        let currentChild;
        const children = $("#" + this.paginationId).children("li");

        if (index === 0 && this.currentPage <= this.range) {
            this.updateButtonRangeWithDefault();
        }

        if (index === 0 && this.currentPage > this.range) {
            for (i = 1; i < children.length - 1; i++) {
                currentChild = children.eq(i);
                if (i >= this.currentPage - this.range + 1 && i <= this.currentPage) {
                    currentChild.css("display", "list-item");
                }
                else {
                    currentChild.css("display", "none");
                }
            }
        }

        if (index === length - 1 && this.currentPage > this.range) {
            for (i = 1; i < children.length - 1; i++) {
                currentChild = children.eq(i);
                if (i >= this.currentPage - this.range + 1 && i <= this.currentPage) {
                    currentChild.css("display", "list-item");
                }
                else {
                    currentChild.css("display", "none");
                }
            }
        }
    }
}