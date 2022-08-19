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
  const userEmail = emailInput.value;
  const userPassword = passInput.value;
  if (userEmail !== '' && userPassword !== '') {
    fetch('member/login', {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify({
        email: userEmail,
        password: userPassword,
      }),
    })
      .then(res => res.json())
      .then(res => {
        if (res.token) {
          localStorage.setItem('token', res.token);
        } else {
          window.alert(res.msg);
        }
      });
  } else {
    alert('이메일 혹은 비밀번호를 입력해주세요.');
    emailInput.value = '';
    passInput.value = '';
    emailInput.focus();
  }
}
function onSendMailBtnClick(event) {
  event.preventDefault();
  const userMail = sendMailInput.value;
  if (userMail !== '') {
    console.log(userMail);
  } else {
    alert('이메일을 입력해주세요.');
    sendMailInput.focus();
  }
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
