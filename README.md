# 장보고 - Jangbogo

![Logo](https://user-images.githubusercontent.com/100837398/225301964-ad397e9c-2de5-467a-be9f-356f7698fef6.jpg)

장보고는 공공데이터를 활용하여 제작한 서울시 식자재 최저가 비교 웹앱입니다. <br />
프론트엔드 개발자 1명, 백엔드 개발자 1명, 총 2명이서 진행한 프로젝트입니다. <br /> <br />

## Project Stack

### Client

- React
- React Router v6
- Redux
- Styled-components

### Server

- Spring Boot
- MySQL
- AWS

## Project UI

![flow](https://github.com/SBSun/jangbogoProject/assets/100837398/1f4b3e01-98c1-4717-a1cb-47bd2d025b3e)

## File Tree

### Front-end
```
src
 ┣ components
 ┃ ┣ auth
 ┃ ┃ ┣ LogIn.js
 ┃ ┃ ┣ MyPage.js
 ┃ ┃ ┗ SignUp.js
 ┃ ┗ common
 ┃ ┃ ┣ Button.js
 ┃ ┃ ┣ CommodityList.js
 ┃ ┃ ┣ Header.js
 ┃ ┃ ┣ MarketList.js
 ┃ ┃ ┣ Navigation.js
 ┃ ┃ ┣ ReviewList.js
 ┃ ┃ ┗ SelectLocation.js
 ┣ containers
 ┃ ┣ auth
 ┃ ┃ ┣ LoginContainer.js
 ┃ ┃ ┣ MyPageContainer.js
 ┃ ┃ ┣ RedirectContainer.js
 ┃ ┃ ┗ SignUpContainer.js
 ┃ ┗ SelectLoactionContainer.js
 ┣ lib
 ┃ ┗ api
 ┃ ┃ ┣ auth.js
 ┃ ┃ ┣ client.js
 ┃ ┃ ┣ commodity.js
 ┃ ┃ ┣ etc.js
 ┃ ┃ ┗ review.js
 ┣ modules
 ┃ ┣ auth.js
 ┃ ┣ index.js
 ┃ ┗ location.js
 ┣ pages
 ┃ ┣ AccountSettingPage.js
 ┃ ┣ CatagoryDetailPage.js
 ┃ ┣ CategoryPage.js
 ┃ ┣ HomePage.js
 ┃ ┣ LoginPage.js
 ┃ ┣ MarketDetailPage.js
 ┃ ┣ MyPagePage.js
 ┃ ┣ RedirectPage.js
 ┃ ┣ ReviewEditPage.js
 ┃ ┣ ReviewWritePage.js
 ┃ ┣ SearchDetailPage.js
 ┃ ┣ SearchPage.js
 ┃ ┗ SignUpPage.js
 ┣ .DS_Store
 ┣ App.js
 ┣ GlobalStyle.js
 ┗ index.js
```
## Reference

[공공데이터](http://data.seoul.go.kr/dataList/OA-1170/S/1/datasetView.do)

</div>
