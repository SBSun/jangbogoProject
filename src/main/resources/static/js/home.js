'use strict';

// 메뉴 옵션
function menuOn() {
    let searbtn = document.getElementById("navbar__searchbtn");
    let backgro = document.getElementsByClassName('home__board')[0];
    let locBoard = document.getElementsByClassName('setLocation')[0];

    searbtn.checked = false;

    locBoard.style.display = 'none';
    backgro.style.filter = 'none';
}

function searchOn() {
    let sidebtn = document.getElementById("navbar__sidebtn");
    let searbtn = document.getElementById("navbar__searchbtn");
    let backgro = document.getElementsByClassName('home__board')[0];
    let locBoard = document.getElementsByClassName('setLocation')[0];

    sidebtn.checked = false;
    locBoard.style.display = 'none';

    if (searbtn.checked) {
        backgro.style.filter = 'blur(10px)';
    } else {
        backgro.style.filter = 'none';
    }

}

let banner = {
    rollId: null,
    interval: 2000,

    //롤링 배너 초기화
    rollInit: function (newinterval) {
        if(parseInt(newinterval) > 0){
            this.interval = newinterval;
        }
        //현재 배너
        let firstitem = document.querySelector('.home__banner_list li');
        if(firstitem){
            firstitem.classList.add('currentroll');
        }
        //다음 배너
        let seconditem = document.querySelectorAll('.home__banner_list li')[1];
        if(seconditem){
            seconditem.classList.add('nextroll');
        }
        //이전 배너
        document.querySelector('.home__banner_list li:last-child').classList.add('prevroll');
        this.rollId = setInterval(this.rollNext, this.interval);//롤링 인터벌 호출
    },

    //다음 배너 롤링
    rollNext: function () {
        if(document.querySelector('.prevroll')){
            document.querySelector('.prevroll').classList.remove('prevroll');
        }
        if(document.querySelector('.currentroll')){
            document.querySelector('.currentroll').classList.add('prevroll');
            document.querySelector('.currentroll').classList.remove('currentroll');
        }
        if(document.querySelector('.nextroll')){
            document.querySelector('.nextroll').classList.add('currentroll');
            document.querySelector('.nextroll').classList.remove('nextroll');
        }
    //다음 이미지 있으면 다음 롤링 이미지로 선택, 없으면 첫번째 이미지를 롤링 이미지로 지정
        if(document.querySelector('.currentroll').nextElementSibling){
            document.querySelector('.currentroll').nextElementSibling.classList.add('nextroll');
        }else{
            document.querySelector('.home__banner_list li').classList.add('nextroll');
        }
    }
}

document.addEventListener('DOMContentLoaded', function(){
    banner.rollInit(4000); // 배너 롤링
});

/* 추천 품목 마진 넣기 */
function margin() {
    let item = document.querySelectorAll('.item');

    for (let i = 1; i < item.length; i++) {
        item[i].style.marginLeft = '20px';
    }
}

{
    let back = document.getElementsByClassName('home__board')[0];
    let locBoard = document.getElementsByClassName('setLocation')[0];
    let locBtn = document.getElementsByClassName('home__locationBtn')[0];
    let locTitle = document.getElementById('locationTitle');
    let nav = document.getElementsByClassName('navbar')[0];

    let loc1 = document.getElementById('loc1');
    let loc2 = document.getElementById('loc2');
    let loc3 = document.getElementById('loc3');
    let loc4 = document.getElementById('loc4');
    let loc5 = document.getElementById('loc5');
    let loc6 = document.getElementById('loc6');
    let loc7 = document.getElementById('loc7');
    let loc8 = document.getElementById('loc8');
    let loc9 = document.getElementById('loc9');
    let loc10 = document.getElementById('loc10');
    let loc11 = document.getElementById('loc11');
    let loc12 = document.getElementById('loc12');
    let loc13 = document.getElementById('loc13');
    let loc14 = document.getElementById('loc14');
    let loc15 = document.getElementById('loc15');
    let loc16 = document.getElementById('loc16');
    let loc17 = document.getElementById('loc17');
    let loc18 = document.getElementById('loc18');
    let loc19 = document.getElementById('loc19');
    let loc20 = document.getElementById('loc20');
    let loc21 = document.getElementById('loc21');
    let loc22 = document.getElementById('loc22');
    let loc23 = document.getElementById('loc23');
    let loc24 = document.getElementById('loc24');
    let loc25 = document.getElementById('loc25');

    locBtn.addEventListener('click', openLoc);
    loc1.addEventListener('click', setLoc1);
    loc2.addEventListener('click', setLoc2);
    loc3.addEventListener('click', setLoc3);
    loc4.addEventListener('click', setLoc4);
    loc5.addEventListener('click', setLoc5);
    loc6.addEventListener('click', setLoc6);
    loc7.addEventListener('click', setLoc7);
    loc8.addEventListener('click', setLoc8);
    loc9.addEventListener('click', setLoc9);
    loc10.addEventListener('click', setLoc10);
    loc11.addEventListener('click', setLoc11);
    loc12.addEventListener('click', setLoc12);
    loc13.addEventListener('click', setLoc13);
    loc14.addEventListener('click', setLoc14);
    loc15.addEventListener('click', setLoc15);
    loc16.addEventListener('click', setLoc16);
    loc17.addEventListener('click', setLoc17);
    loc18.addEventListener('click', setLoc18);
    loc19.addEventListener('click', setLoc19);
    loc20.addEventListener('click', setLoc20);
    loc21.addEventListener('click', setLoc21);
    loc22.addEventListener('click', setLoc22);
    loc23.addEventListener('click', setLoc23);
    loc24.addEventListener('click', setLoc24);
    loc25.addEventListener('click', setLoc25);

    function openLoc() {
        locBoard.style.display = 'block';
        back.style.filter = 'blur(5px)';
        nav.style.filter = 'blur(5px)';
    }
    function setLoc1() {
        locTitle.innerText = '서울시 강남구 >';
        locBoard.style.display = 'none';
        back.style.filter = 'none';
        nav.style.filter = 'none';
    }
    function setLoc2() {
        locTitle.innerText = '서울시 강동구 >';
        locBoard.style.display = 'none';
        back.style.filter = 'none';
        nav.style.filter = 'none';
    }
    function setLoc3() {
        locTitle.innerText = '서울시 강북구 >';
        locBoard.style.display = 'none';
        back.style.filter = 'none';
        nav.style.filter = 'none';
    }
    function setLoc4() {
        locTitle.innerText = '서울시 강서구 >';
        locBoard.style.display = 'none';
        back.style.filter = 'none';
        nav.style.filter = 'none';
    }
    function setLoc5() {
        locTitle.innerText = '서울시 관악구 >';
        locBoard.style.display = 'none';
        back.style.filter = 'none';
        nav.style.filter = 'none';
    }
    function setLoc6() {
        locTitle.innerText = '서울시 광진구 >';
        locBoard.style.display = 'none';
        back.style.filter = 'none';
        nav.style.filter = 'none';
    }
    function setLoc7() {
        locTitle.innerText = '서울시 구로구 >';
        locBoard.style.display = 'none';
        back.style.filter = 'none';
        nav.style.filter = 'none';
    }
    function setLoc8() {
        locTitle.innerText = '서울시 금천구 >';
        locBoard.style.display = 'none';
        back.style.filter = 'none';
        nav.style.filter = 'none';
    }
    function setLoc9() {
        locTitle.innerText = '서울시 노원구 >';
        locBoard.style.display = 'none';
        back.style.filter = 'none';
        nav.style.filter = 'none';
    }
    function setLoc10() {
        locTitle.innerText = '서울시 도봉구 >';
        locBoard.style.display = 'none';
        back.style.filter = 'none';
        nav.style.filter = 'none';
    }
    function setLoc11() {
        locTitle.innerText = '서울시 동대문구 >';
        locBoard.style.display = 'none';
        back.style.filter = 'none';
        nav.style.filter = 'none';
    }
    function setLoc12() {
        locTitle.innerText = '서울시 동작구 >';
        locBoard.style.display = 'none';
        back.style.filter = 'none';
        nav.style.filter = 'none';
    }
    function setLoc13() {
        locTitle.innerText = '서울시 마포구 >';
        locBoard.style.display = 'none';
        back.style.filter = 'none';
        nav.style.filter = 'none';
    }
    function setLoc14() {
        locTitle.innerText = '서울시 서대문구 >';
        locBoard.style.display = 'none';
        back.style.filter = 'none';
        nav.style.filter = 'none';
    }
    function setLoc15() {
        locTitle.innerText = '서울시 서초구 >';
        locBoard.style.display = 'none';
        back.style.filter = 'none';
        nav.style.filter = 'none';
    }
    function setLoc16() {
        locTitle.innerText = '서울시 성동구 >';
        locBoard.style.display = 'none';
        back.style.filter = 'none';
        nav.style.filter = 'none';
    }
    function setLoc17() {
        locTitle.innerText = '서울시 성북구 >';
        locBoard.style.display = 'none';
        back.style.filter = 'none';
        nav.style.filter = 'none';
    }
    function setLoc18() {
        locTitle.innerText = '서울시 송파구 >';
        locBoard.style.display = 'none';
        back.style.filter = 'none';
        nav.style.filter = 'none';
    }
    function setLoc19() {
        locTitle.innerText = '서울시 양천구 >';
        locBoard.style.display = 'none';
        back.style.filter = 'none';
        nav.style.filter = 'none';
    }
    function setLoc20() {
        locTitle.innerText = '서울시 영등포구 >';
        locBoard.style.display = 'none';
        back.style.filter = 'none';
        nav.style.filter = 'none';
    }
    function setLoc21() {
        locTitle.innerText = '서울시 용산구 >';
        locBoard.style.display = 'none';
        back.style.filter = 'none';
        nav.style.filter = 'none';
    }
    function setLoc22() {
        locTitle.innerText = '서울시 은평구 >';
        locBoard.style.display = 'none';
        back.style.filter = 'none';
        nav.style.filter = 'none';
    }
    function setLoc23() {
        locTitle.innerText = '서울시 종로구 >';
        locBoard.style.display = 'none';
        back.style.filter = 'none';
        nav.style.filter = 'none';
    }
    function setLoc24() {
        locTitle.innerText = '서울시 중구 >';
        locBoard.style.display = 'none';
        back.style.filter = 'none';
        nav.style.filter = 'none';
    }
    function setLoc25() {
        locTitle.innerText = '서울시 중랑구 >';
        locBoard.style.display = 'none';
        back.style.filter = 'none';
        nav.style.filter = 'none';
    }
}
