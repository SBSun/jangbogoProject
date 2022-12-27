import React from 'react';
import styles from './Nav.module.css';

const Nav = () => {
  return (
    <nav>
      <ul>
        <li>Icon & Logo</li>
        <li>
          <a href='#' id='loginBtn'>
            Log in
          </a>
        </li>
        <li>
          <a href='#'>
            <i className='fa-solid fa-magnifying-glass'></i>
          </a>
        </li>
      </ul>
    </nav>
  );
};

export default Nav;
