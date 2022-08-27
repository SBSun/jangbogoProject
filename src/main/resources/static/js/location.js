const nav = document.querySelector('.navbar');
const background = document.querySelector('.main-board');
const locationBoard = document.querySelector('.setLocation');
const locationBtn = document.querySelector('.home__locationBtn');
const locationTitle = document.querySelector('#locationTitle');
const locations = Array.from(document.querySelectorAll('.location'));

function margin() {
  const item = document.querySelectorAll('.item');

  for (let i = 1; i < item.length; i++) {
    item[i].style.marginLeft = '20px';
  }
}
function onLocationFilter() {
  locationBoard.style.display = 'block';
  nav.style.filter = 'blur(5px)';
  background.style.filter = 'blur(5px)';
  nav.style.pointerEvents = 'none';
  background.style.pointerEvents = 'none';
}
function offLocationFilter() {
  locationBoard.style.display = 'none';
  nav.style.filter = 'none';
  background.style.filter = 'none';
  nav.style.pointerEvents = 'visible';
  background.style.pointerEvents = 'visible';
}
function onLocationClick(event) {
  const id = parseInt(event.target.id);
  locationTitle.innerText = `서울시 ${locations[id].innerText}`;
  if (sessionStorage.getItem('location')) {
    sessionStorage.removeItem('location');
    sessionStorage.setItem('location', id);
    sessionStorage.setItem('guCode', locations[id].dataset.value);
  } else {
    sessionStorage.setItem('location', id);
    sessionStorage.setItem('guCode', locations[id].dataset.value);
  }
  offLocationFilter();
  location.reload();
}
function setLocation() {
  if (sessionStorage.getItem('location')) {
    const id = sessionStorage.getItem('location');
    locationTitle.innerText = `서울시 ${locations[id].innerText}`;
  } else {
    onLocationFilter();
  }
}
function onLoadItem() {
  const list = document.querySelector('.itemList');
  const guCode = sessionStorage.getItem('guCode');
  fetch(`/lowestPriceInGu?guCode=${guCode}`, {
    method: 'GET',
  })
    .then(res => res.json())
    .then(res => {
      for (let i = 0; i < res.length; i++) {
        console.log(res[i].itemSerialNum);
        switch (res[i].itemSerialNum) {
          case 305:
            itemImg = 'apple';
            break;
          case 58:
            itemImg = 'beef';
            break;
          case 26:
          case 307:
            itemImg = 'cabbage';
            break;
          case 18:
          case 275:
          case 283:
            itemImg = 'chicken';
            break;
          case 311:
            itemImg = 'cucumber';
            break;
          case 171:
          case 320:
            itemImg = 'eggs';
            break;
          case 266:
          case 267:
          case 268:
            itemImg = 'godeung';
            break;
          case 136:
          case 144:
          case 259:
          case 260:
            itemImg = 'jogi';
            break;
          case 23:
          case 310:
            itemImg = 'lettuce';
            break;
          case 152:
          case 264:
          case 265:
            itemImg = 'myeong';
            break;
          case 309:
            itemImg = 'onion';
            break;
          case 306:
            itemImg = 'pear';
            break;
          case 52:
          case 99:
          case 202:
            itemImg = 'pork';
            break;
          case 119:
            itemImg = 'pumpkin';
            break;
          case 25:
          case 133:
          case 274:
          case 308:
            itemImg = 'radish';
            break;
          case 254:
          case 256:
            itemImg = 'squid';
            break;
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
        favorite.innerText = '찜 목록에 추가';

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

        margin();
      }
    });
}

locationBtn.addEventListener('click', onLocationFilter);
locations.forEach(location => {
  location.addEventListener('click', onLocationClick);
});

setLocation();
onLoadItem();
