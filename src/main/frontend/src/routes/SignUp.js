import React from 'react';
import Header from '../components/Header';
import SelectLocation from '../components/SelectLocation';
import Navigation from '../components/Navigation';

const SignUp = ({ isVisible, handleLocateVisible }) => {
  return (
    <>
      <Header
        modify={'DEFAULT_BLOCK'}
        title={'회원가입'}
        handleLocateVisible={handleLocateVisible}
      />
      <SelectLocation isVisible={isVisible} />
      <div>Sign Up</div>
      <Navigation />
    </>
  );
};

export default SignUp;
