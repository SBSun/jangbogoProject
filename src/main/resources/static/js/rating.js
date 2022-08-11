const likeBtn = document.querySelector('#like');
const unlikeBtn = document.querySelector('#unlike');
const ratingText = document.querySelector('.rating-area p');

const ACTIVE_LIKE = 'active-like';
const ACTIVE_UNLIKE = 'active-unlike';

function clickLike(event) {
  likeBtn.classList.add(ACTIVE_LIKE);
  unlikeBtn.classList.remove(ACTIVE_UNLIKE);
  ratingText.classList.remove(ACTIVE_UNLIKE);
  ratingText.classList.add(ACTIVE_LIKE);
  ratingText.innerText = '좋아요!';
}

function clickUnlike(event) {
  likeBtn.classList.remove(ACTIVE_LIKE);
  unlikeBtn.classList.add(ACTIVE_UNLIKE);
  ratingText.classList.remove(ACTIVE_LIKE);
  ratingText.classList.add(ACTIVE_UNLIKE);
  ratingText.innerText = '별로에요.';
}

likeBtn.addEventListener('click', clickLike);
unlikeBtn.addEventListener('click', clickUnlike);
