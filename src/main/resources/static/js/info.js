$(document).ready(function () {
    $(".favorite").click(function () {
        const childrenSvg = $(this).children().eq(0);
        const childrenInput = $(this).children().eq(1).val();
        const childrenCount = $(this).children().eq(2);

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

                $.ajax({
                    type: "POST",
                    url: "/pokemon/pokemonCount",
                    headers: {
                        'Accept': 'application/json',
                        'Content-Type': 'application/json'
                    },
                    data: JSON.stringify(dataFavorite),
                    beforeSend: function(xhr) {
                        xhr.setRequestHeader(header, token);
                    },
                    success: function(data) {
                        childrenCount.text(" ".concat(data));
                    }
                });
            }
        });

    });
});