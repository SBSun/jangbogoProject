import React from 'react';
import styles from './Loading.module.css';
import logo from '../assets/Logo.svg';

const Loading = () => {
  return (
    <img src={logo} width={160} height={160} className={styles.loading_logo} />
  );
};

export default Loading;
