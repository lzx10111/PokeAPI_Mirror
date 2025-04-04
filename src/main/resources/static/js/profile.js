$(document).ready(function () {
    function showModal() {
        const value = Cookies.get("showModal");
        if (Boolean(Number(value))) {
            Cookies.remove("showModal", { path: '/user', domain: 'localhost' });
            const myModal = new bootstrap.Modal($("#profileModal"), {});
            myModal.show();
        }
    }

    showModal();

    $("#profileModal").on('hide.bs.modal', function () {
        $("#modalContentProfile").html("");
    });

    $("#modifyName").click(function () {
        const url = "/user/profileModal?field=name";
        const myModal = new bootstrap.Modal($("#profileModal"), {});

        $("#modalContentProfile").load(url);
        myModal.show();
    });

    $("#modifyEmail").click(function () {
        const url = "/user/profileModal?field=email";
        const myModal = new bootstrap.Modal($("#profileModal"), {});

        $("#modalContentProfile").load(url);
        myModal.show();
    });

    $("#modifyDateBirth").click(function () {
        const url = "/user/profileModal?field=date";
        const myModal = new bootstrap.Modal($("#profileModal"), {});

        $("#modalContentProfile").load(url);
        myModal.show();
    });

    $("#modifyPassword").click(function () {
        const url = "/user/profileModal?field=password";
        const myModal = new bootstrap.Modal($("#profileModal"), {});

        $("#modalContentProfile").load(url);
        myModal.show();
    });
});