const category = Array.from(document.querySelectorAll('.category-btn'));

function moveCategory(e) {
  e.preventDefault();
  const id = parseInt(e.target.id);
  console.log(id, e.target);
  if (sessionStorage.getItem('category')) {
    sessionStorage.removeItem('category');
    sessionStorage.setItem('category', category[id].dataset.value);
  } else {
    sessionStorage.setItem('category', category[id].dataset.value);
  }
  location.replace('/category');
}
function categoryInit() {
  const content = sessionStorage.getItem('category');
  const guCode = sessionStorage.getItem('guCode');
  const title = document.querySelector('.cate_title');
  title.innerText = content;
  const list = document.querySelector('.cate_itemlist');
  let serialCode = [{}];
  fetch(`/findItems/categoryInGu?guCode=${guCode}&branch=${content}`)
    .then(res => res.json())
    .then(res => {
      for (let i = 0; i < res.length; i++) {
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
        serialCode[i] = { id: i, serial: res[i].serialNum };
      }
      sessionStorage.setItem('serial', JSON.stringify(serialCode));
      const itemBtns = Array.from(document.querySelectorAll('.itemBtn'));
      itemBtns.forEach(itemBtn => {
        itemBtn.addEventListener('click', createCallDibs);
      });
    });
}

category.forEach(cate => {
  cate.addEventListener('click', moveCategory);
});
