import client from './client';

export const checkEmail = async ({ email }) => {
  const res = await client.get(`/user/checkEmail?email=${email}`);
  return res.data;
};

export const signUp = async ({ email, password, name, address }) => {
  const res = await client.post(`/user/signUpUser`, {
    email: email,
    password: password,
    name: name,
    address: address,
  });
  return res;
};

export const login = async ({ email, password }) => {
  return await client.post(`/user/login`, { email, password }).then(res => {
    client.defaults.headers.common[
      'Authorization'
    ] = `Bearer ${res.data.tokenInfo.accessToken}`;
    return res.data;
  });
};
