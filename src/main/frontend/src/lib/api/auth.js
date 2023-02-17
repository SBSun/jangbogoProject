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
  const res = await client.post(`/user/login`, { email, password });
  console.log(res);
  return res;
};
