import React, { useState } from 'react';
import { useDispatch, useSelector } from 'react-redux';
import SignUp from '../../components/auth/SignUp';
import { signUp, checkEmail } from '../../lib/api/auth';
import { postRegister } from '../../modules/auth';

const SignUpContainer = () => {
  const { emailValue, passwordValue, nameValue, addressValue } = useSelector(
    ({ auth }) => ({
      emailValue: auth.register.email,
      passwordValue: auth.register.password,
      nameValue: auth.register.name,
      addressValue: auth.register.address,
    })
  );
  const dispatch = useDispatch();

  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');
  const [passwordConfirm, setPasswordConfirm] = useState('');
  const [name, setName] = useState('');
  const [address, setAddress] = useState('');

  const handleEmail = e => {
    setEmail(e.target.value);
    console.log(email);
  };
  const handlePassword = e => {
    setPassword(e.target.value);
    console.log(password);
  };
  const handlePasswordConfirm = e => {
    setPasswordConfirm(e.target.value);
    console.log(passwordConfirm);
  };
  const handleName = e => {
    setName(e.target.value);
    console.log(name);
  };
  const handleAddress = e => {
    setAddress(e.target.value);
    console.log(address);
  };

  const onCheckEmail = () => {
    const promise = checkEmail({ email });
    const fetchData = () => {
      promise.then(res => {
        const isCheck = res;
        return isCheck;
      });
    };
    const isCheck = fetchData();
    console.log(isCheck);
    if (!isCheck) {
      setEmail('');
      return alert('중복된 이메일입니다.');
    }
    return alert('사용 가능한 이메일입니다.');
  };
  const onSubmit = e => {
    e.preventDefault();
    dispatch(
      postRegister({
        email: email,
        password: password,
        passwordConfirm: passwordConfirm,
        name: name,
        address: address,
      })
    );
    if (password !== passwordConfirm) {
      return alert('비밀번호가 다릅니다.');
    }
    console.log({ emailValue, passwordValue, nameValue, addressValue });
    signUp({ emailValue, passwordValue, nameValue, addressValue });
  };

  return (
    <SignUp
      email={email}
      password={password}
      passwordConfirm={passwordConfirm}
      name={name}
      address={address}
      handleEmail={handleEmail}
      handlePassword={handlePassword}
      handlePasswordConfirm={handlePasswordConfirm}
      handleName={handleName}
      handleAddress={handleAddress}
      onCheckEmail={onCheckEmail}
      onSubmit={onSubmit}
    />
  );
};

export default SignUpContainer;
