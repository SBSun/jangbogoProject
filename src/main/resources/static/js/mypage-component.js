const logoutBtn = document.getElementById('logout-btn');
const h3 = document.getElementById('user-name');

function onInitInfo() {
  const email = sessionStorage.getItem('email');
  fetch(`getLoginMemberName?email=${email}`)
    .then(res => res.json())
    .then(res => {
      const username = document.createElement('span');
      username.innerText = `${res}님,`;
      h3.appendChild(username);
    });
}
function onLogoutClick() {
  sessionStorage.removeItem('email');
  sessionStorage.removeItem('token');
  location.replace('/');
}

logoutBtn.addEventListener('click', onLogoutClick);
