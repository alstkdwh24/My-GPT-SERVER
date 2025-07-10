document.addEventListener("DOMContentLoaded", function () {

    let urlParams = new URLSearchParams(window.location.search);
    let authorizationCode = urlParams.get("code");
    let state = urlParams.get("state");  // 이렇게 하면 state 값을 가져올 수 있습니다.
    console.log("Authorization Code:", authorizationCode);
    window.console.log = function(message) {
        // 원래 log 동작 유지하면서...
        window.flutter_inappwebview.callHandler('consoleLogHandler', message);
        console.info("[console.log override]:", message);
    };
    $.ajax({
        type: "POST",
        url: "/api/google/getGoogleAccessToken",
        contentType: "application/x-www-form-urlencoded; charset=utf-8",
        data: {
            code: authorizationCode,
            state: state
        },
        success:function(response) {
            let accessToken =response.access_token;
            console.log("Access Token:", accessToken);
        }
    })
})
