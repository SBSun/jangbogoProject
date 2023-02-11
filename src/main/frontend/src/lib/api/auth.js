import client from './client';

export const signUp = async ({ id, password, name, address }) => {
  console.log(id, password, name, address);
  const res = await client.post(`/user/signUpUser`, {
    id: id,
    password: password,
    name: name,
    address: address,
  });
  return res;
};

export const checkEmail = async ({ id }) => {
  const res = await client.get(`/user/checkId?id=${id}`);
  console.log(res.data);
  return res.data;
};

export const login = ({ id, password }) =>
  client.post(`/user/login`, { id, password });
