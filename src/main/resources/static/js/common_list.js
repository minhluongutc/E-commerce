function showDeleteConfirmModal(link, entityName) {
    categoryId = link.attr("entityId");
    $("#yesButton").attr("href", link.attr("href"));
    $("#confirmText").text("Are you sure to delete this" + entityName + "ID " + categoryId + "?");
    $("#confirmModal").modal();
}

function clearFilter() {
    window.location = moduleURL;
}