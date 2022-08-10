const itemBtn = document.querySelectorAll('.itemBtn');

function onFavoriteClick() {
  if (itemBtn.innerText === '찜 목록에 추가') {
    itemBtn.classList.toggle('active');
    itemBtn.innerText = '찜 목록에서 제거';
  } else {
    itemBtn.classList.toggle('active');
    itemBtn.innerText = '찜 목록에 추가';
  }
}

itemBtn.addEventListener('click', onFavoriteClick);
