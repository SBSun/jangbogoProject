import React from 'react';
import styles from './Nav.module.css';
import logo from '../assets/Logo.svg';

const Nav = () => {
  return (
    <nav>
      <ul>
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
        <li>
          <a href='#' className='nav_loginBtn'>
            Log in
          </a>
        </li>
      </ul>
    </nav>
  );
};

export default Nav;
