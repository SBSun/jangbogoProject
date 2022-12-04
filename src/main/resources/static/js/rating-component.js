const likeBtn = document.querySelector('#like');
const unlikeBtn = document.querySelector('#unlike');
const ratingText = document.querySelector('.rating-area p');

const ACTIVE_LIKE = 'active-like';
const ACTIVE_UNLIKE = 'active-unlike';

let like_unlike = null;

function clickLike() {
  likeBtn.classList.add(ACTIVE_LIKE);
  unlikeBtn.classList.remove(ACTIVE_UNLIKE);
  ratingText.classList.remove(ACTIVE_UNLIKE);
  ratingText.classList.add(ACTIVE_LIKE);
  ratingText.innerText = '좋아요!';
  return (like_unlike = true);
}
function clickUnlike() {
  likeBtn.classList.remove(ACTIVE_LIKE);
  unlikeBtn.classList.add(ACTIVE_UNLIKE);
  ratingText.classList.remove(ACTIVE_LIKE);
  ratingText.classList.add(ACTIVE_UNLIKE);
  ratingText.innerText = '별로에요.';
  return (like_unlike = false);
}

likeBtn.addEventListener('click', clickLike);
unlikeBtn.addEventListener('click', clickUnlike);
