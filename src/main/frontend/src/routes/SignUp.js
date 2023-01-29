import React from 'react';
import Header from '../components/Header';
import Navigation from '../components/Navigation';

const SignUp = () => {
  return (
    <>
      <Header modify={1} title={'회원가입'} />
      <div>Sign Up</div>
      <Navigation />
    </>
  );
};

export default SignUp;
