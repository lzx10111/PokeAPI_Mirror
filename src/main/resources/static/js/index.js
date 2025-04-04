const myGlobalObject = {};
myGlobalObject.currentPage = 1;
myGlobalObject.range = 5;
myGlobalObject.textPrevious = "Anterior";
myGlobalObject.textNext = "Siguiente";

$(document).ready(function () {
    function updateRuleNavbar(id, rule) {
        const children = $("#" + id + " ul li").children();
        let remove;

        if (rule === "vr") {
            remove = "hr";
        }
        else {
            remove = "vr";
        }

        for (let i=0; i<children.length; i++) {
            if (i % 2 === 0) {
                if (rule === "hr") {
                    children.eq(i).removeClass("h-100");
                }
                else if (rule === "vr") {
                    children.eq(i).addClass("h-100");
                }

                children.eq(i).addClass(rule);
                children.eq(i).removeClass(remove);
            }
        }
    }
    
    function xs() {
        if($(window).width() < 576) {
            $("#myPagination").addClass("pagination-sm");
            updatePrevNextButtonText("<", ">");
            updateRuleNavbar("navbarNavDropdown", "hr");
        }
    }

    function sm() {
        if($(window).width() >= 576) {
            $("#myPagination").removeClass("pagination-sm");
            updatePrevNextButtonText("Anterior", "Siguiente");
            updateRuleNavbar("navbarNavDropdown", "hr");
        }
    }

    function md() {
        if($(window).width() >= 768) {
            $("#myPagination").removeClass("pagination-sm");
            updatePrevNextButtonText("Anterior", "Siguiente");
            updateRuleNavbar("navbarNavDropdown", "hr");
        }
    }

    function lg() {
        if($(window).width() >= 992) {
            $("#myPagination").removeClass("pagination-sm");
            updatePrevNextButtonText("Anterior", "Siguiente");
            updateRuleNavbar("navbarNavDropdown", "vr");
        }
    }

    function breakPoint() {
        xs();
        sm();
        md();
        lg();
    }
    
    function selectResize() {
        const multiplier = $("#multiplier");
        const widthChildren = $(".pagination li").first().width();
        const outerWidth = multiplier.outerWidth();
        const width = multiplier.width();

        multiplier.width(widthChildren - (outerWidth - width));
    }

    function showAccordion(selector) {
        const accordionButton = $("#" + selector + " div h2 button");
        const accordionCollapse = $("#" + selector + " div div");
        accordionButton.removeClass("collapsed");
        accordionButton.attr("aria-expanded", "true");
        accordionCollapse.addClass("show");
    }

    function createPagination() {
        const $li = $("<li>", {"class": "page-item"});
        const $a = $("<a>", {"class": "page-link", "href": "#myPagination"});
        const pagination = $("#myPagination");
        const totalPages = updateTotalPages();
        const arr = [];

        pagination.html("");

        for (let i=0; i<(totalPages + 2); i++) {
            const $elem1 = $li.clone();
            const $elem2 = $a.clone();
            if (i === 0) {
                $elem1.addClass("disabled");
                $elem2.html(myGlobalObject.textPrevious);
            }
            else if (i === totalPages + 1) {
                if (totalPages === 1) {
                    $elem1.addClass("disabled");
                }
                $elem2.html(myGlobalObject.textNext);
            }
            else {
                $elem2.html(i)
            }

            arr[i] = $elem1.append($elem2);
        }

        pagination.append(arr);
    }

    function updateTableForPagination(start) {
        const multiplier = $("#multiplier").val();
        const children = $("tbody tr");

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

    function updateTotalPages() {
        const tableChildren = $("tbody tr");
        const multiplier = $("#multiplier").val();

        if (tableChildren.length % multiplier === 0) {
            return Math.floor(tableChildren.length / multiplier);
        }
        else {
            return Math.floor(tableChildren.length / multiplier) + 1;
        }
    }

    function updateCurrentPage(index, length, totalPages) {
        if (index === 0 && myGlobalObject.currentPage > 1) {
            myGlobalObject.currentPage -= 1;
        }

        if (index === (length - 1) && myGlobalObject.currentPage < totalPages) {
            myGlobalObject.currentPage += 1;
        }

        if (index >= 1 && index <= (length - 2)) {
            myGlobalObject.currentPage = index;
        }
    }

    function updatePrevButtonWithNumberPage(index, length) {
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

    function updateNextButtonWithNumberPage(index, length) {
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

    function updatePrevButtonWithSelf(index, length, totalPages) {
        if (index === 1) {
            return true;
        }
        else if (index === 0 && totalPages !== 1 && myGlobalObject.currentPage === 1) {
            return true;
        }
        else if (index === length - 1 && totalPages !== 1) {
            return false;
        }
        else if (totalPages === 1) {
            return true;
        }
    }

    function updateNextButtonWithSelf(index, length, totalPages) {
        if (index === length - 2) {
            return true;
        }
        else if (index === 0 && totalPages !== 1) {
            return false;
        }
        else if (index === length - 1 && totalPages !== 1 && myGlobalObject.currentPage === totalPages) {
            return true;
        }
        else if (totalPages === 1) {
            return true;
        }
    }

    function updatePrevButton(index, length, totalPages) {
        const firstChildren = $(".pagination li").first();

        if (updatePrevButtonWithNumberPage(index, length)) {
            firstChildren.addClass("disabled");
        }
        else {
            firstChildren.removeClass("disabled");
        }

        if (updatePrevButtonWithSelf(index, length, totalPages)) {
            firstChildren.addClass("disabled");
        }
        else {
            firstChildren.removeClass("disabled");
        }
    }

    function updateNextButton(index, length, totalPages) {
        const lastChildren = $(".pagination li").last();

        if (updateNextButtonWithNumberPage(index, length)) {
            lastChildren.addClass("disabled");
        }
        else {
            lastChildren.removeClass("disabled");
        }

        if (updateNextButtonWithSelf(index, length, totalPages)) {
            lastChildren.addClass("disabled");
        }
        else {
            lastChildren.removeClass("disabled");
        }
    }

    function updatePrevNextButtonText(text_prev, text_next) {
        const pagination = $("#myPagination li a");
        pagination.first().html(text_prev);
        pagination.last().html(text_next);
        myGlobalObject.textPrevious = text_prev;
        myGlobalObject.textNext = text_next;
    }

    function updateButtonRangeWithDefault() {
        const children = $(".pagination li").children();
        for (let i = 0; i < children.length; i++) {
            if (i === 0) {
                continue;
            }

            if (i === children.length - 1) {
                break;
            }

            const currentChild = children.eq(i);
            if (i <= myGlobalObject.range) {
                currentChild.css("display", "list-item");
            }
            else {
                currentChild.css("display", "none");
            }
        }
    }

    function updateButtonRange(index, length) {
        let i;
        let currentChild;
        if (myGlobalObject.currentPage === 1) {
            updateButtonRangeWithDefault();
        }

        if (index === 0 && myGlobalObject.currentPage === myGlobalObject.range) {
            updateButtonRangeWithDefault();
        }

        if (index === 0 && myGlobalObject.currentPage > myGlobalObject.range) {
            const children = $(".pagination li").children();

            for (i = 0; i < children.length; i++) {
                if (i === 0) {
                    continue;
                }

                if (i === children.length - 1) {
                    break;
                }

                currentChild = children.eq(i);
                if (i >= myGlobalObject.currentPage - myGlobalObject.range + 1 && i <= myGlobalObject.currentPage) {
                    currentChild.css("display", "list-item");
                }
                else {
                    currentChild.css("display", "none");
                }
            }
        }

        if (index === length - 1 && myGlobalObject.currentPage > myGlobalObject.range) {
            const children = $(".pagination li").children();
            for (i = 0; i < children.length; i++) {
                if (i === 0) {
                    continue;
                }

                if (i === children.length - 1) {
                    break;
                }

                currentChild = children.eq(i);
                if (i >= myGlobalObject.currentPage - myGlobalObject.range + 1 && (i <= myGlobalObject.currentPage)) {
                    currentChild.css("display", "list-item");
                }
                else {
                    currentChild.css("display", "none");
                }
            }
        }
    }

    updateTableForPagination(myGlobalObject.currentPage);

    createPagination();

    updateButtonRangeWithDefault();

    selectResize();

    showAccordion("accordionExample");

    breakPoint();

    $(window).resize(function(){
        breakPoint();
    });

    $(".favorite").click(function () {
        const childrenSvg = $(this).children().eq(0);
        const childrenInput = $(this).children().eq(1).val();

        const token = $("meta[name='_csrf']").attr("content");
        const header = $("meta[name='_csrf_header']").attr("content");

        const dataFavorite = {
            id: {
                userId: "",
                pokemonId: childrenInput
            }
        };

        $.ajax({
            type: "POST",
            url: "/user/addFavorite",
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            data: JSON.stringify(dataFavorite),
            beforeSend: function(xhr) {
                xhr.setRequestHeader(header, token);
            },
            success: function(data) {
                if (Boolean(Number(data))) {
                    childrenSvg.toggleClass('bi-star bi-star-fill');
                }
                else {
                    childrenSvg.toggleClass('bi-star-fill bi-star');
                }
            }
        });

    });

    $('#multiplier').change(function () {
        myGlobalObject.currentPage = 1;
        createPagination();
        updateTableForPagination(myGlobalObject.currentPage);
        updateButtonRangeWithDefault();
    });

    $(".pagination").on("click", "li", function () {
        const pagination = $(".pagination li");
        const index = pagination.index(this);
        const length = pagination.children().length;
        const totalPages = updateTotalPages();

        updateCurrentPage(index, length, totalPages);
        updatePrevButton(index, length, totalPages);
        updateNextButton(index, length, totalPages);
        updateButtonRange(index, length);

        updateTableForPagination(myGlobalObject.currentPage);
    });
});