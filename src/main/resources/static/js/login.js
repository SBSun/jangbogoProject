'use strict';

// 비밀번호 찾기
function findPass() {
    let login = document.getElementsByClassName('login__form')[0];
    let pass = document.getElementsByClassName('login__findpass')[0];

    login.style.display = 'none';
    pass.style.display = 'block';
}

// 비밀번호 찾기 => 로그인 폼
function backLogin() {
    let login = document.getElementsByClassName('login__form')[0];
    let pass = document.getElementsByClassName('login__findpass')[0];

    login.style.display = 'block';
    pass.style.display = 'none';
}


// 메뉴 옵션
function menuOn() {
    let searbtn = document.getElementById("navbar__searchbtn");
    let backgro = document.getElementsByClassName('login__board')[0];

    searbtn.checked = false;
    backgro.style.filter = 'none';
}

function searchOn() {
    let sidebtn = document.getElementById("navbar__sidebtn");
    let searbtn = document.getElementById("navbar__searchbtn");
    let backgro = document.getElementsByClassName('login__board')[0];

    sidebtn.checked = false;

    if (searbtn.checked) {
        backgro.style.filter = 'blur(10px)';
    } else {
        backgro.style.filter = 'none';
    }
    
}