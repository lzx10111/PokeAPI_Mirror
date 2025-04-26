import paginationController from "./paginationController.js";

const myGlobalObject = {};
myGlobalObject.myPaginationObject = {};

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
            myGlobalObject.myPaginationObject.updatePrevNextButtonText("<", ">");
            updateRuleNavbar("navbarNavDropdown", "hr");
        }
    }

    function sm() {
        if($(window).width() >= 576) {
            $("#myPagination").removeClass("pagination-sm");
            myGlobalObject.myPaginationObject.updatePrevNextButtonText("Anterior", "Siguiente");
            updateRuleNavbar("navbarNavDropdown", "hr");
        }
    }

    function md() {
        if($(window).width() >= 768) {
            $("#myPagination").removeClass("pagination-sm");
            myGlobalObject.myPaginationObject.updatePrevNextButtonText("Anterior", "Siguiente");
            updateRuleNavbar("navbarNavDropdown", "hr");
        }
    }

    function lg() {
        if($(window).width() >= 992) {
            $("#myPagination").removeClass("pagination-sm");
            myGlobalObject.myPaginationObject.updatePrevNextButtonText("Anterior", "Siguiente");
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
        const multiplier = $("#myMultiplier");
        const widthChildren = $("#myPagination li").first().width();
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

    myGlobalObject.myPaginationObject = new paginationController(undefined,undefined,undefined,1 , 3, "Anterior", "Siguiente");
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
            userId: "",
            pokemonId: childrenInput
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

    $("#myMultiplier").change(function () {
        myGlobalObject.myPaginationObject.updateMultiplier();
    });

    $("#myPagination").on("click", "li", function () {
        myGlobalObject.myPaginationObject.updatePagination(this);
    });
});