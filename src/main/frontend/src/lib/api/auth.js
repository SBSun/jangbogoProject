import client from './client';

export const checkEmail = async ({ id }) => {
  const res = await client.get(`/user/checkId?id=${id}`);
  return res.data;
};

export const signUp = async ({ id, password, name, address }) => {
  const res = await client.post(`/user/signUpUser`, {
    id: id,
    password: password,
    name: name,
    address: address,
  });
  return res;
};

export const login = async ({ id, password }) => {
  const res = await client.post(`/user/login`, { id, password });
  console.log(res);
  return res;
};
