import client from './client';

// 이메일 중복 확인 API
export const checkEmail = async ({ email }) => {
  const res = await client.get(`/user/checkEmail?email=${email}`);
  return res.data;
};

// 회원가입 API
export const signUp = async (email, password, name) => {
  const res = await client.post(`/user/signUpUser`, {
    email,
    password,
    name,
  });
  return res;
};

// 로그인 토큰 헤더 설정 API
export const setAuthorizationToken = token => {
  if (token) {
    client.defaults.headers.common['Authorization'] = `Bearer ${token}`;
  } else {
    delete client.defaults.headers.common['Authorization'];
  }
};

// 로그인 API
export const login = async ({ email, password }) => {
  return await client.post(`/user/login`, { email, password }).then(res => {
    console.log(res.data);
    setAuthorizationToken(res.data.tokenInfo.accessToken);
    return res.data;
  });
};

// 로그아웃 API
export const logout = async (accessToken, refreshToken) => {
  return await client
    .post(`/user/logout`, { accessToken, refreshToken })
    .then(res => {
      console.log(res);
      client.defaults.headers.common['Authorization'] = `Bearer ${accessToken}`;
      return res;
    });
};
