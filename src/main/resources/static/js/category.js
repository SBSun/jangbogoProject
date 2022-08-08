function findPass() {
    var login = document.getElementsByClassName('login__form')[0];
    var pass = document.getElementsByClassName('login__findpass')[0];

    login.style.display = 'none';
    pass.style.display = 'block';
}

function backLogin() {
    var login = document.getElementsByClassName('login__form')[0];
    var pass = document.getElementsByClassName('login__findpass')[0];

    login.style.display = 'block';
    pass.style.display = 'none';
}

function menuOn() {
    var sidebtn = document.getElementById("navbar__sidebtn");
    var searbtn = document.getElementById("navbar__searchbtn");
    var backgro = document.getElementsByClassName('item__board')[0];

    searbtn.checked = false;

    backgro.style.filter = 'none';
}

function searchOn() {
    var sidebtn = document.getElementById("navbar__sidebtn");
    var searbtn = document.getElementById("navbar__searchbtn");
    var backgro = document.getElementsByClassName('item__board')[0];

    sidebtn.checked = false;

    if (searbtn.checked) {
        backgro.style.filter = 'blur(10px)';
    } else {
        backgro.style.filter = 'none';
    }
    
}

function checkOn() {
    var checkOn = document.getElementById("cheOn");
    var checkOff = document.getElementById("cheOff");

    checkOn.style.display = 'block';
    checkOff.style.display = 'none';
}

function checkOff() {
    var checkOn = document.getElementById("cheOn");
    var checkOff = document.getElementById("cheOff");

    checkOn.style.display = 'none';
    checkOff.style.display = 'block';
}