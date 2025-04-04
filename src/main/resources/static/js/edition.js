$(document).ready(function () {
    function showModal() {
        const myModal = new bootstrap.Modal($("#editionModal"), {});
        myModal.show();
    }

    $("#formAddGroup").on('submit', function (e) {
        e.preventDefault();
        const url = "/loader";
        const self = this;

        $("#modalContentEdition").load(url, function () {
            showModal();
            self.submit();
        });
    });

    $("#formDeleteGroup").submit(function (e) {
        e.preventDefault();
        const url = "/loader";
        const self = this;

        $("#modalContentEdition").load(url, function () {
            showModal();
            self.submit();
        });
    });

    $("#formAddSpecific").submit(function (e) {
        e.preventDefault();
        const url = "/loader";
        const self = this;

        $("#modalContentEdition").load(url, function () {
            showModal();
            self.submit();
        });
    });

    $("#formDeleteSpecific").submit(function (e) {
        e.preventDefault();
        const url = "/loader";
        const self = this;

        $("#modalContentEdition").load(url, function () {
            showModal();
            self.submit();
        });
    });
});