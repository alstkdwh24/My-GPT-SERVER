document.addEventListener("DOMContentLoaded", function () {

    let access_token;
    let urlParams = new URLSearchParams(window.location.search);
    let authorizationCode = urlParams.get("code");
    console.log("Authorization Code:", authorizationCode);
})
