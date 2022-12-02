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
  let serialCode = [{}];
  let itemImg = 'item';
  fetch(`/findItems/lowestPriceInGu?guCode=${guCode}`, {
    method: 'GET',
  })
    .then(res => res.json())
    .then(res => {
      for (let i = 0; i < res.length; i++) {
        switch (res[i].itemSerialNum) {
          case 305:
          case 50:
          case 28:
            itemImg = 'apple';
            break;
          case 58:
          case 131:
            itemImg = 'beef';
            break;
          case 26:
          case 307:
          case 125:
            itemImg = 'cabbage';
            break;
          case 18:
          case 138:
          case 275:
          case 283:
            itemImg = 'chicken';
            break;
          case 311:
          case 22:
            itemImg = 'cucumber';
            break;
          case 171:
          case 320:
          case 17:
            itemImg = 'eggs';
            break;
          case 266:
          case 267:
          case 268:
          case 269:
            itemImg = 'godeung';
            break;
          case 136:
          case 144:
          case 259:
          case 260:
          case 303:
          case 313:
          case 314:
            itemImg = 'jogi';
            break;
          case 23:
          case 310:
            itemImg = 'lettuce';
            break;
          case 152:
          case 264:
          case 265:
          case 302:
            itemImg = 'myeong';
            break;
          case 24:
          case 309:
          case 272:
            itemImg = 'onion';
            break;
          case 306:
          case 27:
          case 276:
            itemImg = 'pear';
            break;
          case 52:
          case 99:
          case 202:
            itemImg = 'pork';
            break;
          case 119:
          case 312:
          case 277:
            itemImg = 'pumpkin';
            break;
          case 25:
          case 133:
          case 274:
          case 308:
          case 282:
            itemImg = 'radish';
            break;
          case 254:
          case 256:
            itemImg = 'squid';
            break;
          default:
            itemImg = 'none';
        }

        const div = document.createElement('div');
        div.className = 'item';
        const img = document.createElement('img');
        img.src = `../files/items/${itemImg}.png`;
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
        favorite.id = i;
        favorite.value = res[i].serialNum;
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
        serialCode[i] = { id: i, serial: res[i].serialNum };
      }
      sessionStorage.setItem('serial', JSON.stringify(serialCode));
      const itemBtns = Array.from(document.querySelectorAll('.itemBtn'));
      itemBtns.forEach(itemBtn => {
        itemBtn.addEventListener('click', createCallDibs);
      });
    });
}
function onLoadMarket() {
  const list = document.querySelector('.marketList');
  const guCode = sessionStorage.getItem('guCode');
  let marketImg = 'tradition';
  let marketCode = [{}];
  fetch(`/marketsInGu?guCode=${guCode}`)
    .then(res => res.json())
    .then(res => {
      for (let i = 0; i < res.length; i++) {
        if (res[i].marketName.includes('홈플러스')) {
          marketImg = 'homeplus';
        } else if (res[i].marketName.includes('이마트')) {
          marketImg = 'emart';
        } else if (res[i].marketName.includes('롯데백화점')) {
          marketImg = 'lotte';
        } else if (res[i].marketName.includes('롯데슈퍼')) {
          marketImg = 'lottesuper';
        } else if (res[i].marketName.includes('하나로')) {
          marketImg = 'hanaro';
        } else if (res[i].marketName.includes('현대백화점')) {
          marketImg = 'hyundai';
        } else if (res[i].marketName.includes('신세계')) {
          marketImg = 'sinsekai';
        } else {
          marketImg = 'tradition';
        }

        const div = document.createElement('div');
        div.className = 'market';
        div.dataset.value = res[i].marketSerialNum;
        const a = document.createElement('a');
        a.href = '#';
        a.className = 'marketLink';
        a.dataset.value = res[i].marketSerialNum;
        const img = document.createElement('img');
        img.src = `../files/market/${marketImg}.png`;
        img.dataset.value = res[i].marketSerialNum;
        const name = document.createElement('div');
        name.className = 'marketName';
        name.dataset.value = res[i].marketSerialNum;
        name.innerText = res[i].marketName;

        list.appendChild(div);
        div.appendChild(a);
        a.appendChild(img);
        a.appendChild(name);
      }
      const marketLinks = Array.from(document.querySelectorAll('.marketLink'));
      marketLinks.forEach(market => {
        market.addEventListener('click', clickMarket);
      });
    });
}
function clickMarket(e) {
  e.preventDefault();
  console.log(e.target.dataset.value);
  sessionStorage.setItem('marketSerial', e.target.dataset.value);
  location.replace('../review/market');
}

locationBtn.addEventListener('click', onLocationFilter);
locations.forEach(location => {
  location.addEventListener('click', onLocationClick);
});

setLocation();
onLoadItem();
onLoadMarket();
