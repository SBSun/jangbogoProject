import React, { useCallback } from 'react';
import { useSelector, useDispatch } from 'react-redux';
import { postRegister } from '../../modules/auth';
import SignUp from '../../components/auth/SignUp';

const SignUpContainer = () => {
  const { email, password, passwordConfirm, name, address } = useSelector(
    ({ register }) => ({
      email: register.email,
      password: register.password,
      passwordConfirm: register.passwordConfirm,
      name: register.name,
      address: register.address,
    })
  );
  const dispatch = useDispatch();
  const onSubmit = useCallback(
    e => {
      e.preventDefault();
      dispatch(postRegister());
    },
    [dispatch]
  );
  return (
    <SignUp
      email={email}
      password={password}
      passwordConfirm={passwordConfirm}
      name={name}
      address={address}
      onSubmit={onSubmit}
    />
  );
};

export default SignUpContainer;
