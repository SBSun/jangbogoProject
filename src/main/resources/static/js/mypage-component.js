const logoutBtn = document.getElementById('logout-btn');

function onLogoutClick() {
  sessionStorage.removeItem('token');
  location.replace('/');
}

logoutBtn.addEventListener('click', onLogoutClick);
