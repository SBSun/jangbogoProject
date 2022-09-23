const guCode = sessionStorage.getItem('guCode');
const marketCode = sessionStorage.getItem('marketSerial');

function initMarket() {
  const img = document.querySelector('.market-img');
  const name = document.querySelector('.market-name');
  const like = document.querySelector('.market-like');
  fetch(`/marketsInGu?guCode=${guCode}`)
    .then(res => res.json())
    .then(res => {
      for (let i = 0; i < res.length; i++) {
        if (res[i].marketSerialNum === marketCode) {
          name.innerText = res[i].marketName;
        }
      }
    });
}
initMarket();
