import { createGlobalStyle } from 'styled-components';
import reset from 'styled-reset';

const styled = { createGlobalStyle };

const GlobalStyle = styled.createGlobalStyle`
  ${reset}

  :root {
    --red: #d63031;
    --blue: #0984e3;
    --yellow: #fdcb6e;
    --green: #00b894;
    --light-green: #55efc4;
    --black: #2d3436;
    --gray: #b2bec3;
    --light-gray: #dfe6e9;
  }

  body {
    word-break: keep-all;
    font-family: Pretendard, -apple-system, BlinkMacSystemFont, Bazier Square,
      Noto Sans KR, Segoe UI, Apple SD Gothic Neo, Roboto, Helvetica Neue, Arial,
      sans-serif, Apple Color Emoji, Segoe UI Emoji, Segoe UI Symbol,
      Noto Color Emoji;
    letter-spacing: -0.3px;
    color: var(--black);
    -webkit-font-smoothing: antialiased;
    -moz-osx-font-smoothing: grayscale;
  }

  @media (min-width: 415px) {
    body {
      width: 412px;
      margin: auto;
    }
    body > div#root > header,
    body > div#root > footer {
      width: 412px;
      margin: auto;
    }
  }
`;

export default GlobalStyle;
