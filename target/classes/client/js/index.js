function resetLoginForm() {
    const loginForm = $('#loginForm');
    loginForm.submit(event => {
        event.preventDefault();
    $.ajax({
        url: '/user/game',
        type: 'POST',
        data: loginForm.serialize(),
        success: response => {
        if (response.startsWith('Error:')) {
        alert(response);
    } else {
        Cookies.set("sessionToken", response);
        window.location.href = "/client/game.html";
    }
}
});
});
}

function pageLoad() {
    resetLoginForm();
}
