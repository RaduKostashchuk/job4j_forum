function validate() {
    const name = $('#nameInput').val();
    const password = $('#passwordInput').val();
    const confirm = $('#confirmInput').val();
    let alertMessage = "";
    if (name === "") {
        alertMessage += "Необходимо указать имя пользователя\n";
    }
    if (password === "") {
        alertMessage += "Необходимо указать пароль\n";
    }
    if (password !== confirm) {
        alertMessage += "Пароли не совпадают";
    }
    if (alertMessage !== "") {
        alert(alertMessage);
        return false;
    }
    return true;
}