const subMenu = document.querySelector('.dropdown_subMenu');
const sortBtn = document.getElementById('sortBtn');

const favorit = document.getElementById('sort_favorit');
const low = document.getElementById('sort_low');
const high = document.getElementById('sort_high');

function clickSort() {
  if (subMenu.style.display == 'block') {
    subMenu.style.display = 'none';
  } else {
    subMenu.style.display = 'block';
  }
}
function setLow() {
  sortBtn.innerText = '낮은 가격순';
  subMenu.style.display = 'none';
}
function setHigh() {
  sortBtn.innerText = '높은 가격순';
  subMenu.style.display = 'none';
}

sortBtn.addEventListener('click', clickSort);
low.addEventListener('click', setLow);
high.addEventListener('click', setHigh);
