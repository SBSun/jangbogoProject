import React, { useCallback, useEffect, useState } from 'react';
import { useNavigate } from 'react-router-dom';
import SignUp from '../../components/auth/SignUp';
import { checkEmail, signUp } from '../../lib/api/auth';

const SignUpContainer = () => {
  const [form, setForm] = useState({
    email: '',
    password: '',
    passwordConfirm: '',
    name: '',
  });

  const [validate, setValidate] = useState({
    email: false,
    emailConfirm: false,
    password: false,
    passwordConfirm: false,
    name: false,
  });

  const { email, password, passwordConfirm, name } = form;

  const navigate = useNavigate();

  const handleInput = useCallback(
    e => {
      switch (e.target.name) {
        case 'EMAIL': {
          setForm(form => ({
            ...form,
            email: e.target.value,
          }));
          setValidate(validate => ({
            ...validate,
            emailConfirm: false,
          }));
          return form;
        }
        case 'PASSWORD': {
          setForm(form => ({ ...form, password: e.target.value }));
          return form;
        }
        case 'PASSWORD_CONFIRM': {
          setForm(form => ({ ...form, passwordConfirm: e.target.value }));
          return form;
        }
        case 'NAME': {
          setForm(form => ({ ...form, name: e.target.value }));
          return form;
        }
        default: {
          return form;
        }
      }
    },
    // eslint-disable-next-line react-hooks/exhaustive-deps
    [email, password, passwordConfirm, name]
  );

  // useState가 비동기이므로 useEffect에서 유효성 검사 진행
  useEffect(() => {
    // prettier-ignore
    // eslint-disable-next-line no-useless-escape
    const EMAIL_REGEX = '^[a-zA-Z0-9+-\_.]+@[a-zA-Z0-9-]+\.[a-zA-Z0-9-.]+$';

    // prettier-ignore
    // eslint-disable-next-line no-useless-escape
    const PASSWORD_REGEX = '^[\da-zA-Z0-9!@#]{8,}$';

    console.log(validate);

    if (email.match(EMAIL_REGEX)) {
      setValidate(validate => ({ ...validate, email: true }));
    } else {
      setValidate(validate => ({ ...validate, email: false }));
    }

    if (password.match(PASSWORD_REGEX)) {
      setValidate(validate => ({ ...validate, password: true }));
    } else {
      setValidate(validate => ({ ...validate, password: false }));
    }

    if (password !== '') {
      setValidate(validate => ({
        ...validate,
        passwordConfirm: password === passwordConfirm,
      }));
    }

    setValidate(validate => ({ ...validate, name: name !== '' }));

    // 모든 유효성 검사에 통과하면
    if (
      validate.email &&
      validate.emailConfirm &&
      validate.password &&
      validate.passwordConfirm &&
      validate.name
    ) {
      setValidate(validate => ({ ...validate, isConfirm: true }));
    }
    // eslint-disable-next-line react-hooks/exhaustive-deps
  }, [email, password, passwordConfirm, name]);

  const onCheckEmail = useCallback(() => {
    const promise = checkEmail(email);
    const fetchData = () => {
      promise
        .then(data => {
          console.log(data);
          setValidate(validate => ({ ...validate, emailConfirm: data }));
          data
            ? alert('사용 가능한 이메일입니다')
            : alert('중복된 이메일입니다.');
        })
        .catch(e => console.log(e));
    };
    fetchData();
  }, [email]);

  const onSubmit = useCallback(
    e => {
      e.preventDefault();

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

      switch (true) {
        case !validate.email: {
          return alert('이메일를 입력해주세요.');
        }
        case !validate.emailConfirm: {
          return alert('이메일 중복 확인을 해주세요.');
        }
        case !validate.password: {
          return alert('비밀번호를 해주세요.');
        }
        case !validate.passwordConfirm: {
          return alert('비밀번호가 서로 다릅니다.');
        }
        case !validate.name: {
          return alert('이름을 입력해주세요.');
        }
        default: {
          return fetchData();
        }
      }
    },
    // eslint-disable-next-line react-hooks/exhaustive-deps
    [
      validate.email,
      validate.emailConfirm,
      validate.password,
      validate.passwordConfirm,
      validate.name,
    ]
  );

  return (
    <SignUp
      email={email}
      password={password}
      passwordConfirm={passwordConfirm}
      name={name}
      handleInput={handleInput}
      validate={validate}
      onCheckEmail={onCheckEmail}
      onSubmit={onSubmit}
    />
  );
};

export default SignUpContainer;
