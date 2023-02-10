import client from './client';

export const signUp = async ({ email, password, name, address }) => {
  const res = await client.post(`/user/signUpUser`, {
    email: email,
    password: password,
    name: name,
    address: address,
  });
  return res;
};

export const checkEmail = async ({ email }) => {
  const res = await client.get(`/user/checkId?id=${email}`);
  console.log(res.data);
  return res.data;
};

export const login = ({ email, password }) =>
  client.post(`/user/login`, { email, password });
