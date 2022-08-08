'use strict';

window.addEventListener('scroll', (event) => {
    let scrollY = this.scrollY;
    const scrollBtn = document.querySelector('#scroll-up');

    const HIDDEN_CLASSNAME = 'hidden';

    if (scrollY > 20) {
        scrollBtn.classList.remove(HIDDEN_CLASSNAME);
    } else {
        scrollBtn.classList.add(HIDDEN_CLASSNAME);
    }
});