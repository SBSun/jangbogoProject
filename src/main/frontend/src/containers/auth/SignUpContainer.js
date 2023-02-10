import React, { useReducer } from 'react';
import { useDispatch, useSelector } from 'react-redux';
import SignUp from '../../components/auth/SignUp';
import { signUp } from '../../lib/api/auth';
import { postRegister } from '../../modules/auth';

function reducer(state, action) {
  return {
    ...state,
    [action.name]: action.value,
  };
}

const SignUpContainer = () => {
  const [state, dispatch] = useReducer(reducer, {
    email: '',
    password: '',
    passwordConfirm: '',
    name: '',
    address: '',
  });
  const { email, password, passwordConfirm, name, address } = state;

  const { emailValue, passwordValue, nameValue, addressValue } = useSelector(
    ({ auth }) => ({
      emailValue: auth.register.email,
      passwordValue: auth.register.password,
      nameValue: auth.register.name,
      addressValue: auth.register.address,
    })
  );
  const dispatchRedux = useDispatch();

  const handleInputs = e => {
    dispatch(e.target);
  };
  const onCheckEmail = () => {
    console.log('Checking Email');
  };
  const onSubmit = e => {
    e.preventDefault();
    dispatchRedux(
      postRegister({
        email: email,
        password: password,
        passwordConfirm: passwordConfirm,
        name: name,
        address: address,
      })
    );
    console.log(
      `email : ${emailValue}, password : ${passwordValue}, name : ${nameValue}, address : ${addressValue}`
    );
    const promise = signUp(emailValue, passwordValue, nameValue, addressValue);
    const fetchData = () => {
      promise.then(res => console.log(res));
    };
    fetchData();
  };

  return (
    <SignUp
      email={email}
      password={password}
      passwordConfirm={passwordConfirm}
      name={name}
      address={address}
      handleInputs={handleInputs}
      onCheckEmail={onCheckEmail}
      onSubmit={onSubmit}
    />
  );
};

export default SignUpContainer;
