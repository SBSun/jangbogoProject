import React from 'react';
import Header from '../components/Header';
import Location from '../components/Location';
import Navigation from '../components/Navigation';

const SignUp = ({ isVisible, handleLocateVisible }) => {
  return (
    <>
      <Header
        modify={'DEFAULT_BLOCK'}
        title={'회원가입'}
        handleLocateVisible={handleLocateVisible}
      />
      <Location isVisible={isVisible} />
      <div>Sign Up</div>
      <Navigation />
    </>
  );
};

export default SignUp;
