import React, { useReducer, useState } from 'react';
import { useDispatch } from 'react-redux';
import { useNavigate } from 'react-router-dom';
import SignUp from '../../components/auth/SignUp';
import { checkEmail, signUp } from '../../lib/api/auth';
import { postRegister } from '../../modules/auth';

function reducer(state, action) {
  switch (action.name) {
    case 'EMAIL': {
      return {
        ...state,
        email: action.value,
      };
    }
    case 'PASSWORD': {
      return {
        ...state,
        password: action.value,
      };
    }
    case 'PASSWORD_CONFIRM': {
      return {
        ...state,
        passwordConfirm: action.value,
      };
    }
    case 'NAME': {
      return {
        ...state,
        name: action.value,
      };
    }
    default: {
      return state;
    }
  }
}

const SignUpContainer = () => {
  const [state, dispatch] = useReducer(reducer, {
    email: '',
    password: '',
    passwordConfirm: '',
    name: '',
  });
  const { email, password, passwordConfirm, name } = state;

  const [validations, setValidations] = useState({
    duplicate: {
      email: false,
      password: false,
    },
    regExp: {
      email: false,
      password: false,
    },
    isComfirm: {
      form: false,
    },
  });

  const navigate = useNavigate();
  const storeDispatch = useDispatch();

  const handleInputs = e => {
    dispatch(e.target);
  };
  const onCheckEmail = () => {
    if (email === '') {
      setValidations(() => ({
        ...validations,
        duplicate: {
          ...validations.duplicate,
          email: false,
        },
      }));
      return alert('이메일을 입력해주세요.');
    }
    if (validations.duplicate.email) {
      alert('사용 가능한 이메일입니다.');
    } else {
      alert('중복된 이메일입니다.');
    }

    const promise = checkEmail({ email });
    const fetchData = async () => {
      await promise.then(data => {
        setValidations(() => ({
          ...validations,
          duplicate: {
            ...validations.duplicate,
            email: data,
          },
        }));
      });
    };
    fetchData();
  };
  const onSubmit = e => {
    e.preventDefault();
    storeDispatch(
      postRegister({
        email: email,
        password: password,
        name: name,
      })
    );

    if (email === '') {
      return alert('이메일을 입력해주세요.');
    } else if (password === '' || passwordConfirm === '') {
      return alert('비밀번호를 입력해주세요.');
    } else if (name === '') {
      return alert('이름을 입력해주세요.');
    }

    if (password !== passwordConfirm) {
      return alert('비밀번호가 다릅니다.');
    }

    const promise = signUp(email, password, name);
    const fetchData = () => {
      promise
        .then(res => {
          console.log(res.data);
          alert('가입되었습니다.');
          navigate('/', { replace: true });
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
      email={email}
      password={password}
      passwordConfirm={passwordConfirm}
      name={name}
      validations={validations}
      handleInputs={handleInputs}
      onCheckEmail={onCheckEmail}
      onSubmit={onSubmit}
    />
  );
};

export default SignUpContainer;
