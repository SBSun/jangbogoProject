const email = document.querySelector('#login-1');
const setEmail = document.querySelector('#email-btn');
const emailForm = document.querySelector('#login-2');
const cancleEmail = document.querySelector('#email-cancle');

const setPassword = document.querySelector('#edit-password');
const passwordForm = document.querySelector('#password div');
const canclePassword = document.querySelector('#password-cancle');

const HIDDEN_CLASSNAME = 'hidden';

setEmail.addEventListener('click', function() {
    email.classList.add(HIDDEN_CLASSNAME);
    emailForm.classList.remove(HIDDEN_CLASSNAME);
});

cancleEmail.addEventListener('click', function() {
    email.classList.remove(HIDDEN_CLASSNAME);
    emailForm.classList.add(HIDDEN_CLASSNAME);
});

setPassword.addEventListener('click', function() {
    setPassword.classList.add(HIDDEN_CLASSNAME);
    passwordForm.classList.remove(HIDDEN_CLASSNAME);
});

canclePassword.addEventListener('click', function() {
    setPassword.classList.remove(HIDDEN_CLASSNAME);
    passwordForm.classList.add(HIDDEN_CLASSNAME);
});

