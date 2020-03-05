function login(event) {
  $.ajax({
    url: "/login",
    type: "POST",
    data: '{"username": "' + $('#username').val() + '",' +
        ' "password": "' + $("#password").val() + '"}',
    contentType: "application/json; charset=utf-8",
    dataType: "json",
    success: function (response) {
      $('#code').val(response['authCode']);
    }
  })
  event.preventDefault();
}
