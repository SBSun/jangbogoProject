const searchForm = document.querySelector('.navbar__searchbar form');
const searchContent = document.querySelector('#navbar__search_input');
/* const itemCode = {
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
  if (sessionStorage.getItem('searchContent')) {
    sessionStorage.removeItem('searchContent', searchContent.value);
    sessionStorage.setItem('searchContent', searchContent.value);
  } else {
    sessionStorage.setItem('searchContent', searchContent.value);
  }
  location.replace('/member/search');
}
function searchInit() {
  const content = sessionStorage.getItem('searchContent');
  const title = document.querySelector('.search__title');
  let itemImg = 'img url';
  const list = document.querySelector('.search_itemlist');
  title.innerText = `검색 결과 "${content}"`;
  fetch(`/member/searchContent?content=${content}`)
    .then(res => res.json())
    .then(res => {
      for (let i = 0; i < res.length; i++) {
        console.log(res[i].itemSerialNum);
        if (res[i].itemSerialNum === 305) {
          itemImg = 'apple';
        } else if (res[i].itemSerialNum === 58) {
          itemImg = 'beef';
        } else if (res[i].itemSerialNum === 26 || 307) {
          itemImg = 'cabbage';
        } else if (res[i].itemSerialNum === 18 || 275 || 283) {
          itemImg = 'chicken';
        } else if (res[i].itemSerialNum === 311) {
          itemImg = 'cucumber';
        } else if (res[i].itemSerialNum === 171 || 320) {
          itemImg = 'eggs';
        } else if (res[i].itemSerialNum === 266 || 267 || 268) {
          itemImg = 'godeung';
        } else if (res[i].itemSerialNum === 136 || 144 || 259 || 260) {
          itemImg = 'jogi';
        } else if (res[i].itemSerialNum === 23 || 310) {
          itemImg = 'lettuce';
        } else if (res[i].itemSerialNum === 152 || 264 || 265) {
          itemImg = 'myeong';
        } else if (res[i].itemSerialNum === 309) {
          itemImg = 'onion';
        } else if (res[i].itemSerialNum === 306) {
          itemImg = 'pear';
        } else if (res[i].itemSerialNum === 52 || 99 || 202) {
          itemImg = 'pork';
        } else if (res[i].itemSerialNum === 199) {
          itemImg = 'pumpkin';
        } else if (res[i].itemSerialNum === 25 || 133 || 274 || 308) {
          itemImg = 'radish';
        } else if (res[i].itemSerialNum === 254 || 256) {
          itemImg = 'squid';
        }

        const div = document.createElement('div');
        div.className = 'item';
        const img = document.createElement('img');
        img.src = `../File/items/${itemImg}.png`;
        const info = document.createElement('div');
        info.className = 'itemInfo';
        const marketName = document.createElement('span');
        marketName.className = 'marketName';
        marketName.innerText = res[i].marketName;
        const dl = document.createElement('dl');
        const itemName = document.createElement('dt');
        itemName.innerText = res[i].itemName;
        const itemUnit = document.createElement('dd');
        itemUnit.innerText = res[i].itemUnit;
        const priceArea = document.createElement('div');
        priceArea.className = 'price_area';
        const itemPrice = document.createElement('span');
        itemPrice.className = 'itemPrice';
        itemPrice.innerText = `${res[i].itemPrice}원`;
        const favorite = document.createElement('button');
        favorite.className = 'itemBtn';
        favorite.innerText = '찜 목록애 추가';

        list.appendChild(div);
        div.appendChild(img);
        div.appendChild(info);
        info.appendChild(marketName);
        info.appendChild(dl);
        dl.appendChild(itemName);
        dl.appendChild(itemUnit);
        info.appendChild(priceArea);
        priceArea.appendChild(itemPrice);
        div.appendChild(favorite);
      }
    });
}

searchForm.addEventListener('submit', onSearch);
