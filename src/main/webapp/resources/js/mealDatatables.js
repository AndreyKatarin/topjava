var ajaxUrl = "ajax/meals/";
var datatableApi;

$(function () {
    datatableApi = $("#datatable").DataTable({
        "paging": false,
        "info": true,
        "columns": [
            {
                "data": "dateTime"
            },
            {
                "data": "description"
            },
            {
                "data": "calories"
            },
            {
                "defaultContent": "Edit",
                "orderable": false
            },
            {
                "defaultContent": "Delete",
                "orderable": false
            }
        ],
        "order": [
            [
                0,
                "asc"
            ]
        ]
    });
    makeEditable();
});

function clearFilter() {
    $('input[type=date], input[type=time]').val('');
    updateTable();
}

function filterTable() {
    var form = $("#filter");
    $.ajax({
        type: 'POST',
        url: ajaxUrl + 'getBetween',
        data: form.serialize(),
        success: function (data) {
            datatableApi.clear().rows.add(data).draw();
            successNoty("Filtered");
        }
    });
}
