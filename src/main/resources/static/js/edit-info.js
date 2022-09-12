const email = document.querySelector('#login-1');
const emailSpan = document.querySelector('#email-span');
const setEmail = document.querySelector('#email-btn');
const emailForm = document.querySelector('#login-2');
const cancleEmail = document.querySelector('#email-cancle');

const setPassword = document.querySelector('#edit-password');
const passwordForm = document.querySelector('#password div');
const modifyPass = document.querySelector('#modify-password');
const modifyPassCheck = document.querySelector('#modify-password-check');
const canclePassword = document.querySelector('#password-cancle');
const changePassword = document.querySelector('#change-password');

const modifyName = document.querySelector('#modify-name');
const modifyTel = document.querySelector('#modify-tel');
const modifyAddress = document.querySelector('#modify-address');
const changeInfo = document.querySelector('#save-info');

const HIDDEN_CLASSNAME = 'hidden';

function onLoadUserInfo() {
  emailSpan.innerText = sessionStorage.getItem('email');
}
function onChangePass() {
  if (modifyPass.value === modifyPassCheck.value) {
    fetch('/member/updatePassword', {
      method: 'PUT',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify({
        email: sessionStorage.getItem('email'),
        password: modifyPass.value,
      }),
    })
      .then(res => res.json())
      .then(res => {
        alert('비밀번호가 변경되었습니다.');
        location.replace('/mypage');
      });
  } else {
    alert('비밀번호가 다릅니다. 다시 입력해주세요.');
    modifyPass.innerText = '';
    modifyPassCheck.innerText = '';
  }
}
function onChangeUserInfo() {
  fetch('/member/updateOtherInfo', {
    method: 'PUT',
    headers: {
      'Content-Type': 'application/json',
    },
    body: JSON.stringify({
      email: sessionStorage.getItem('email'),
      name: modifyName.value,
      address: modifyAddress.value,
      tel: modifyTel.value,
    }),
  })
    .then(res => res.json())
    .then(res => {
      alert('사용자 정보가 변경되었습니다.');
      location.replace('/member/mypage');
    });
}

setEmail.addEventListener('click', function () {
  email.classList.add(HIDDEN_CLASSNAME);
  emailForm.classList.remove(HIDDEN_CLASSNAME);
});

cancleEmail.addEventListener('click', function () {
  email.classList.remove(HIDDEN_CLASSNAME);
  emailForm.classList.add(HIDDEN_CLASSNAME);
});

setPassword.addEventListener('click', function () {
  setPassword.classList.add(HIDDEN_CLASSNAME);
  passwordForm.classList.remove(HIDDEN_CLASSNAME);
});

canclePassword.addEventListener('click', function () {
  setPassword.classList.remove(HIDDEN_CLASSNAME);
  passwordForm.classList.add(HIDDEN_CLASSNAME);
});
changePassword.addEventListener('click', onChangePass);
changeInfo.addEventListener('click', onChangeUserInfo);
