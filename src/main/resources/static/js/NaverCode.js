document.addEventListener("DOMContentLoaded", function () {

    let access_token;
    let urlParams = new URLSearchParams(window.location.search);
    let authorizationCode = urlParams.get("code");
    console.log("Authorization Code:", authorizationCode);
    try {
        $.ajax({
            type: "POST",
            url: "/api/naver/naverCode",
            contentType: "application/x-www-form-urlencoded; charset=utf-8",
            data: {
                code: authorizationCode
            },
            success: function (response) {

                access_token = response.access_token;                // 필요한 경우 추가적인 처리
                $.ajax({
                    type:"GET",
                    url:"/api/naver/naverUserInfo",
                    data: {
                        access_token: access_token, // 네이버 로그인에서 받은 access_token
                    },
                    success: function (response) {
                        console.log(response);
                        // 필요한 경우 추가적인 처리
                        // 예: 사용자 정보를 세션 스토리지에 저장하거나 페이지를 리다이렉트
                    },
                })
                // 예: 사용자 정보를 세션 스토리지에 저장하거나 페이지를 리다이렉트
            },
        })
    }catch (e) {

    } finally {

    }
})
