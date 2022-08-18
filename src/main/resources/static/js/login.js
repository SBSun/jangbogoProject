const loginForm = document.querySelector('#login__form');
const goFindPass = document.querySelector('.login__form_findPass');
const findPassword = document.querySelector('#findPassword');
const goLoginForm = document.querySelector('.findpass_cancle');
const emailInput = document.querySelector('#login__email');
const passInput = document.querySelector('#login__password');
const findBtn = document.querySelector('.login__form_findPass');
const sendMailInput = document.querySelector('#findpass__email');
const loginBtn = document.querySelector('.login__form_loginBtn');
const sendMailBtn = document.querySelector('.findpass_ok');
const cancleFindMailBtn = document.querySelector('.findpass_cancle');

const HIDDEN_CLASSNAME = 'hidden';

function onLoginBtnClick(event) {
  event.preventDefault();
  fetch('/member/login', {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json',
    },
    body: JSON.stringify({
      email: emailInput.value,
      password: passInput.value,
    }),
  })
    .then(res => res.json())
    .then(data => console.log(data));
}
function onSendMailBtnClick(event) {
  event.preventDefault();
  const userMail = sendMailInput.value;
  console.log(userMail);
}
function onFindBtnClick() {
  emailInput.value = null;
  passInput.value = null;
}
function onCancleFindMailBtnClick() {
  sendMailInput.value = null;
}

goFindPass.addEventListener('click', function () {
  loginForm.classList.add(HIDDEN_CLASSNAME);
  findPassword.classList.remove(HIDDEN_CLASSNAME);
});
goLoginForm.addEventListener('click', function () {
  loginForm.classList.remove(HIDDEN_CLASSNAME);
  findPassword.classList.add(HIDDEN_CLASSNAME);
});
loginBtn.addEventListener('click', onLoginBtnClick);
findBtn.addEventListener('click', onFindBtnClick);
sendMailBtn.addEventListener('click', onSendMailBtnClick);
cancleFindMailBtn.addEventListener('click', onCancleFindMailBtnClick);
