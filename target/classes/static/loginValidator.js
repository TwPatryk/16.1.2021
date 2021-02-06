function validate() {
    var login = document.forms["loginForm"]["login"].value;
    var pass = document.forms["loginForm"]["pass"].value;

    var flag = true;

    if(login == "") {
        document.forms["loginForm"]["login"].style.background = "#eec4c4";
        flag = false;
    }

    if(pass == "") {
        document.forms["loginForm"]["pass"].style.background = "#eec4c4";
        flag = false;
    }

    return flag;
}