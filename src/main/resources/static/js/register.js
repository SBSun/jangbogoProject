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
  location.replace('member/login');
}
function onEmailCheck() {
  const email = emailInput.value;
  const email_regex = /^[A-Za-z0-9_\.\-]+@[A-Za-z0-9\-]+\.[A-Za-z0-9\-]+/;

  if (email === '') {
    alert.log('이메일을 입력해주세요.');
    return;
  }

  if (email_regex.test(email)) {
    $.ajax({
      async: true,
      type: 'GET',
      dataType: 'json',
      data: { email: email },
      url: '/member/emailCheck',

      success: function (data) {
        if (data.result === 1) {
          alert('존재하는 이메일입니다.');
        } else if (data.result === 0) {
          alert('사용가능한 이메일입니다.');
          isCheckedEmail = true;
          $('#register__form_submit').attr('type', 'submit');
        }
      },
      error: function (jqXHR, textStatus, errorThrown) {
        alert(errorThrown + ' ' + textStatus);
      },
    });
  } else {
    alert('이메일 형식으로 작성해주세요.');
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
  if (
    emailInput.value === '' ||
    passInput.value === '' ||
    checkPassInput.value === '' ||
    userName.value === '' ||
    userAddress.value === '' ||
    userTel.value === ''
  ) {
    alert('필수 정보들을 전부 입력해주세요.');
  } else {
    fetch('member/register', {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify({
        email: emailInput.value,
        password: passInput.value,
        name: userName.value,
        address: userAddress.value,
        tel: userTel.value,
      }),
    })
      .then(res => res.json())
      .then(res => console.log(res));
  }
}
onPasswordCheck();
setInterval(onPasswordCheck, 500);
emailCheck.addEventListener('click', onEmailCheck);
submitBtn.addEventListener('click', onSubmitBtnClick);
