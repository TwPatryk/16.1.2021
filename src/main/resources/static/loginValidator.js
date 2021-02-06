function validate() {
    var login = document.forms["loginForm"]["login"].value;
    var login = document.forms["loginForm"]["pass"].value;

    if(login == "") {
        return false;
    }

    if(pass == "") {
        return false;
    }
}