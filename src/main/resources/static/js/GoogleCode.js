document.addEventListener("DOMContentLoaded", function () {

    let urlParams = new URLSearchParams(window.location.search);
    let authorizationCode = urlParams.get("code");
    let state = urlParams.get("state");  // 이렇게 하면 state 값을 가져올 수 있습니다.
    console.log("Authorization Code:", authorizationCode);

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
            $.ajax({
                type:"GET",
                url: "https://www.googleapis.com/oauth2/v2/userinfo",
                headers:{
                    "Authorization": "Bearer " + accessToken
                },
                success: function(response) {
                    console.log("Google User Info:", response);
                    // response에 name, email, picture 등 정보가 포함됨
                },
                error: function(error) {
                    console.error("Error fetching user info:", error);
                }
            })
        }
    })
})
