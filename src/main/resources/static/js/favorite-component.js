function favoriteComponent() {
  const itemBtns = Array.from(document.querySelectorAll('.itemBtn'));

  const ACTIVE_KEY = 'active';

  itemBtns.forEach(itemBtn =>
    itemBtn.addEventListener('click', function () {
      if (sessionStorage.getItem('token') !== null) {
        if (itemBtn.innerText === '찜 목록에 추가') {
          itemBtn.classList.toggle(ACTIVE_KEY);
          itemBtn.innerText = '찜 목록에서 제거';
          alert('찜 목록에 추가되었습니다.');
        } else {
          itemBtn.classList.toggle(ACTIVE_KEY);
          itemBtn.innerText = '찜 목록에 추가';
          alert('찜 목록에서 제거되었습니다.');
        }
      } else {
        alert('로그인을 먼저 해주세요.');
        location.replace('../member/login');
      }
    })
  );
}

favoriteComponent();
