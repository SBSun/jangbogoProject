'use strict'

const backBtn = document.querySelector('#navbar-cancle');

function goBackPage() {
    history.back();
}

backBtn.addEventListener('click', goBackPage);