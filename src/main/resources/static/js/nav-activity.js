'use strict';

// 메뉴 옵션
function menuOn() {
    let searchBtn = document.getElementById("navbar__searchbtn");
    let background = document.getElementsByClassName('main-board')[0];

    searchBtn.checked = false;

    background.style.filter = 'none';
}

function searchOn() {
    let sideBtn = document.getElementById("navbar__sidebtn");
    let searchBtn = document.getElementById("navbar__searchbtn");
    let background = document.getElementsByClassName('main-board')[0];

    sideBtn.checked = false;

    if (searchBtn.checked) {
        background.style.filter = 'blur(10px)';
    } else {
        background.style.filter = 'none';
    }
}