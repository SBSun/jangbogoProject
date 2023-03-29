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
    font-family: 'Noto Sans KR';
    color: var(--black);
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
