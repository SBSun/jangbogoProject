'use strict';

const emailInput = document.querySelector('#register__email');
const emailCheck = document.querySelector('#register__email-check');
const passInput = document.querySelector('#register__password');
const passMessage = document.querySelector('#password-message');
const checkPassInput = document.querySelector('#register__passwordCheck');
const checkPassMessage = document.querySelector('#password-check-message');
const userName = document.querySelector('#register__form_name');
const userAddress = document.querySelector('#register__form_address');
const userTel = document.querySelector('#register__form_tel');
const alertMail = document.querySelector('#alert_service_email');
const alertTel = document.querySelector('#alert_service_tel');
const submitBtn = document.querySelector('#register__form_submit');

const HIDDEN_CLASSNAME = 'hidden';

function backLogin() {
  location.replace('login.html');
}
function onEmailCheck() {
  if (emailInput.value === '') {
    alert('이메일을 입력해주세요');
  }
}
function onPasswordCheck() {
  if (passInput.value === '') {
    passMessage.classList.remove(HIDDEN_CLASSNAME);
  } else {
    passMessage.classList.add(HIDDEN_CLASSNAME);
  }
  if (passInput.value === checkPassInput.value) {
    checkPassMessage.classList.add(HIDDEN_CLASSNAME);
  } else {
    checkPassMessage.classList.remove(HIDDEN_CLASSNAME);
  }
}
function onSubmitBtnClick(event) {
  event.preventDefault();
  console.log(
    emailInput.value,
    passInput.value,
    checkPassInput.value,
    userName.value,
    userAddress.value,
    userTel.value,
    alertMail.checked,
    alertTel.checked
  );
}
onPasswordCheck();
setInterval(onPasswordCheck, 500);
emailCheck.addEventListener('click', onEmailCheck);
submitBtn.addEventListener('click', onSubmitBtnClick);
