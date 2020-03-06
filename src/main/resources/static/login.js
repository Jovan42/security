$( document ).ready(function() {
  $('#url').text(getParameterByName('redirectUrl'))
});

function login(event) {
  $.ajax({
    url: "/login?redirectUrl=" + getParameterByName('redirectUrl'),
    type: "POST",
    data: '{"username": "' + $('#username').val() + '",' +
        ' "password": "' + $("#password").val() + '"}',
    contentType: "application/json; charset=utf-8",
    dataType: "json",
    success: function (response) {
      $('#code').val(response['authCode']);

      $.ajax({
        url: getParameterByName('redirectUrl'),
        type: "POST",
        data: '{"authCode": "' + response['authCode'] + '",' +
            ' "state": "' + response['state'] + '"}',
        contentType: "application/json; charset=utf-8",
        dataType: "json",
        success: function (response) {
          $('#code').val(response['authCode']);
        }
      })
    }
  })
  event.preventDefault();
}

function getParameterByName(name, url) {
  if (!url) url = window.location.href;
  name = name.replace(/[\[\]]/g, '\\$&');
  let regex = new RegExp('[?&]' + name + '(=([^&#]*)|&|#|$)'),
      results = regex.exec(url);
  if (!results) return null;
  if (!results[2]) return '';
  return decodeURIComponent(results[2].replace(/\+/g, ' '));
}
