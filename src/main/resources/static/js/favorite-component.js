const userMail = sessionStorage.getItem('email');
const serialCode = JSON.parse(sessionStorage.getItem('serial'));

const ACTIVE_KEY = 'active';

function createCallDibs(e) {
  if (sessionStorage.getItem('token') !== null) {
    const id = parseInt(e.target.id);
    const value = parseInt(e.target.value);
    if (value === serialCode[id].serial) {
      const serialNum = serialCode[id].serial;
      fetch('/createCallDibs', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify({
          email: userMail,
          serialNum: serialNum,
        }),
      })
        .then(res => res.json())
        .then(res => {
          e.target.classList.add(ACTIVE_KEY);
          e.target.innerText = '찜 목록에 추가됨';
          alert(res.res);
        });
    }
  } else {
    alert('로그인을 먼저 해주세요.');
    location.replace('../member/login');
  }
}
function readCallDibs() {
  if (sessionStorage.getItem('token') !== null) {
    const list = document.querySelector('.item-list');
    fetch(`/findItems/callDibs?email=${userMail}`)
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
          img.src = `../assets/items/${itemImg}.png`;
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
          favorite.className = 'favorBtn';
          favorite.id = i;
          favorite.value = res[i].serialNum;
          favorite.innerText = '찜 목록에서 제거';

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
        const favorBtns = Array.from(document.querySelectorAll('.favorBtn'));
        favorBtns.forEach(favorBtn => {
          favorBtn.addEventListener('click', deleteCallDibs);
        });
      });
  } else {
    alert('양식 오류');
    location.replace('/');
  }
}
function deleteCallDibs(e) {
  if (sessionStorage.getItem('token') !== null) {
    const value = parseInt(e.target.value);
    fetch('/deleteCallDibs', {
      method: 'DELETE',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify({
        email: userMail,
        serialNum: value,
      }),
    })
      .then(res => res.json())
      .then(res => {
        alert('찜 목록에서 삭제되었습니다.');
        location.reload();
      });
  }
}
