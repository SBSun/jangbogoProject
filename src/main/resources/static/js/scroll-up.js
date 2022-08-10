const scrollBtn = document.getElementById('scroll-up');

function onScrollClick() {
  window.scrollTo(0, 0);
}

window.addEventListener('scroll', event => {
  let scrollY = this.scrollY;

  const HIDDEN_CLASSNAME = 'hidden';

  if (scrollY > 20) {
    scrollBtn.classList.remove(HIDDEN_CLASSNAME);
  } else {
    scrollBtn.classList.add(HIDDEN_CLASSNAME);
  }
});
scrollBtn.addEventListener('click', onScrollClick);
