import React from 'react';
import Header from '../components/Header';
import SelectLocation from '../components/SelectLocation';
import Navigation from '../components/Navigation';

const SignUp = () => {
  return (
    <>
      <Header modify={'DEFAULT_BLOCK'} title={'회원가입'} />
      <SelectLocation />
      <Navigation />
    </>
  );
};

export default SignUp;
