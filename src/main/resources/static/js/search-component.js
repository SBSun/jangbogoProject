const searchForm = document.querySelector('.navbar__searchbar form');
const searchContent = document.querySelector('#navbar__search_input');
/*const itemCode = {
  apple: 305,
  beef: 58,
  cabbage: [26, 307],
  chicken: [18, 275, 283],
  cucumber: 311,
  eggs: [171, 320],
  godeung: [266, 267, 268],
  jogi: [136, 144, 259, 260],
  lettuce: [23, 310],
  myeong: [152, 264, 265],
  onion: 309,
  pear: 306,
  pork: [52, 99, 202],
  pumpkin: 199,
  radish: [25, 133, 274, 308],
  squid: [254, 256],
}; */

function onSearch(event) {
  event.preventDefault();
  sessionStorage.setItem('searchContent', searchContent.value);
  location.replace('/member/search');
}
function searchInit() {
  const content = sessionStorage.getItem('searchContent');
  fetch(`/member/search?content=${content}`)
    .then(res => res.json())
    .then(res => console.log(res))
    .then(data => console.log(data));
}

searchForm.addEventListener('submit', onSearch);
