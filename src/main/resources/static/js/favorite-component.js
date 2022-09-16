function favoriteComponent() {
  const itemBtns = Array.from(document.querySelectorAll('.itemBtn'));
  const userMail = sessionStorage.getItem('email');
  const serialCode = JSON.parse(sessionStorage.getItem('serial'));

  const ACTIVE_KEY = 'active';

  function createCallDibs(e) {
    if (sessionStorage.getItem('token') !== null) {
      const id = parseInt(e.target.id);
      if (id === serialCode[id].id) {
        const serialNum = serialCode[id].serial;
        fetch('/createCallDibs', {
          method: 'POST',
          headers: {
            'Content-Type': 'application/json',
          },
          body: JSON.stringify({
            email: userMail,
            serialNum: serialNum,
          }),
        })
          .then(res => {
            res.json();
            e.target.classList.add(ACTIVE_KEY);
            e.target.innerText = '찜 목록에서 제거';
          })
          .then(res => console.log(res));
      }
    } else {
      alert('로그인을 먼저 해주세요.');
      location.replace('../member/login');
    }
  }
  function deleteCallDibs(e) {
    if (sessionStorage.getItem('token') !== null) {
      const id = parseInt(e.target.id);
      if (id === serialCode[id].id) {
        const serialNum = serialCode[id].serial;
        fetch('/deleteCallDibs', {
          method: 'DELETE',
          headers: {
            'Content-Type': 'application/json',
          },
          body: JSON.stringify({
            email: userMail,
            serialNum: serialNum,
          }),
        })
          .then(res => {
            res.json();
            e.target.classList.remove(ACTIVE_KEY);
            e.target.innerText = '찜 목록에 추가';
          })
          .then(res => console.log(res));
      }
    }
  }
  itemBtns.forEach(itemBtn => {
    itemBtn.addEventListener('click', createCallDibs);
  });
}
