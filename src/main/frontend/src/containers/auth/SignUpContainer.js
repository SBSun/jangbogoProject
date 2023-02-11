import React, { useReducer, useState } from 'react';
import { useDispatch } from 'react-redux';
import { useNavigate } from 'react-router-dom';
import SignUp from '../../components/auth/SignUp';
import { checkEmail, signUp } from '../../lib/api/auth';
import { postRegister } from '../../modules/auth';

function reducer(state, action) {
  return {
    ...state,
    [action.name]: action.value,
  };
}

const SignUpContainer = () => {
  const [state, dispatch] = useReducer(reducer, {
    id: '',
    password: '',
    passwordConfirm: '',
    name: '',
    address: '',
  });
  const { id, password, passwordConfirm, name, address } = state;

  const [emailReg, emailRegConfirm] = useState(false);
  const [isEmailCheck, setIsEmail] = useState(null);
  const [passReg, passRegComfirm] = useState(false);
  const [isPassConfirm, setIsConfirm] = useState(false);

  const navigate = useNavigate();
  const reduxDispatch = useDispatch();

  const handleInputs = e => {
    const emailRegex = /[\w\-\.]+\@[\w\-\.]+/;
    const passwordRegex = new RegExp('[A-Za-z0-9]{6,12}');

    dispatch(e.target);

    if (emailRegex.test(id)) {
      emailRegConfirm(true);
    }
    if (passwordRegex.test(password)) {
      passRegComfirm(true);
    }
    if (password === passwordConfirm) {
      setIsConfirm(true);
    }
  };
  const onCheckEmail = () => {
    const promise = checkEmail({ id });
    const fetchData = () => {
      promise.then(res => setIsEmail(res));
    };
    fetchData();
  };
  const onSubmit = e => {
    e.preventDefault();
    reduxDispatch(
      postRegister({
        id: id,
        password: password,
        name: name,
        address: address,
      })
    );

    const promise = signUp({ id, password, name, address });
    console.log(promise);
    const fetchData = () => {
      promise
        .then(res => {
          console.log(res.data);
          alert('가입되었습니다.');
          navigate('/', true);
        })
        .catch(error => {
          console.log(error);
          alert('다시 시도해주세요.');
          return;
        });
    };
    fetchData();
  };

  return (
    <SignUp
      id={id}
      password={password}
      passwordConfirm={passwordConfirm}
      name={name}
      address={address}
      handleInputs={handleInputs}
      emailReg={emailReg}
      isEmailCheck={isEmailCheck}
      onCheckEmail={onCheckEmail}
      passReg={passReg}
      isPassConfirm={isPassConfirm}
      onSubmit={onSubmit}
    />
  );
};

export default SignUpContainer;
