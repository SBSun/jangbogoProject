function InitStatus() {
  const signTag = document.querySelector('a.sign-btn');
  const icon = document.createElement('i');
  if (sessionStorage.getItem('token') !== null) {
    icon.className = 'fa-solid fa-user';
    signTag.appendChild(icon);
    signTag.href = '../member/mypage';
  } else {
    icon.className = 'fa-solid fa-arrow-right-to-bracket';
    signTag.appendChild(icon);
    signTag.href = '../member/login';
  }
}
function moveMypage() {
  if (sessionStorage.getItem('token') !== null) {
    location.replace('../member/mypage');
  } else {
    alert('로그인을 먼저해주세요');
    location.replace('../member/login');
  }
}
function moveFavorite() {
  if (sessionStorage.getItem('token') !== null) {
    location.replace('../member/favorite');
  } else {
    alert('로그인을 먼저해주세요');
    location.replace('../member/login');
  }
}
function moveWriteReview() {
  if (sessionStorage.getItem('token') !== null) {
    location.replace('../review/write');
  } else {
    alert('로그인을 먼저해주세요');
    location.replace('../member/login');
  }
}
InitStatus();
