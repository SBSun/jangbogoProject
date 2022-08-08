'use strict';

// 정렬 기준
{
    let subMenu = document.getElementsByClassName('dropdown_subMenu')[0];
    let sortBtn = document.getElementById('sortBtn');

    sortBtn.addEventListener('click', clickSort);

    function clickSort() {
        if (subMenu.style.display == 'block') {
            subMenu.style.display = 'none';
        } else {
            subMenu.style.display = 'block';
        }
    }

    let favorit = document.getElementById('sort_favorit');
    let low = document.getElementById('sort_low');
    let high = document.getElementById('sort_high');

    favorit.addEventListener('click', setFavor);
    low.addEventListener('click', setLow);
    high.addEventListener('click', setHigh);

    function setFavor() {
        sortBtn.innerText = '찜 많은 순';
        subMenu.style.display = 'none';
    }
    function setLow() {
        sortBtn.innerText = '낮은 가격순';
        subMenu.style.display = 'none';
    }
    function setHigh() {
        sortBtn.innerText = '높은 가격순';
        subMenu.style.display = 'none';
    }
}