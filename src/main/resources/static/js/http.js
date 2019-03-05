function get(url, params) {
    $.ajax({
        type: "GET",
        url: url,
        async:true,
        contentType: "application/json",
        beforeSend: function (request) {
            request.setRequestHeader("Authorization", "Bearer " + localStorage.getItem("access_token"));
        },
        success: function (result) {
            console.log("ajax请求：" + result);
            $("#container-applist").html(result);
        },
        error: function (request, status, errorThrown) {
            console.log(status + errorThrown);
        }
    })
}