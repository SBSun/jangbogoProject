import client from './client';

// 이메일 중복 확인 API
export const checkEmail = async email => {
  return await client.get(`/users/check?email=${email}`).then(res => {
    return res.data;
  });
};

// 회원가입 API
export const signUp = async (email, password, name) => {
  return await client.post(`/users`, {
    email,
    password,
    name,
  });
};

// 로그인 API
export const login = async (email, password) => {
  return await client.post(`/users/login`, { email, password }).then(res => {
    return res.data;
  });
};

// 로그인한 유저 정보 반환
export const getUserInfo = async () => {
  return await client.get(`/users/loginUser`).then(res => {
    return res.data;
  });
};

// 로그인 토큰 헤더 설정 API
export const setAuthorizationToken = token => {
  if (token) {
    client.defaults.headers.common['Authorization'] = `Bearer ${token}`;
  } else {
    delete client.defaults.headers.common['Authorization'];
  }
};

// 사용자 정보 수정 API
export const editUserInfo = async (password, name) => {
  return await client.patch(`/users`, { password, name });
};

// 로그아웃 API
export const logout = async (accessToken, refreshToken) => {
  return await client.post(`/users/logout`, { accessToken, refreshToken });
};

// 계정 삭제 API
export const deleteAccount = async () => {
  return await client.delete(`/users`);
};
