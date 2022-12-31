import React from 'react';
import styles from './Loading.module.css';
import logo from '../assets/Logo.svg';

const Loading = () => {
  return (
    <div className={styles.loading_container}>
      <img src={logo} width={160} height={160} />
    </div>
  );
};

export default Loading;
