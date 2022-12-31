import React from 'react';
import styles from './Nav.module.css';
import logo from '../assets/Logo.svg';

const Nav = () => {
  return (
    <nav>
      <ul>
        <li>
          <img
            src={logo}
            width={100}
            height={38}
            className={styles.nav_logo}
          ></img>
        </li>
        <li>
          <a href='#' className={styles.nav_search}>
            <i className='fa-solid fa-magnifying-glass'></i>
          </a>
        </li>
        <li>
          <a href='#' className={styles.nav_login}>
            Log in
          </a>
        </li>
      </ul>
    </nav>
  );
};

export default Nav;
