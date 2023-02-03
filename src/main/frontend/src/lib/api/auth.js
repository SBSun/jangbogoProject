import client from './client';

export const login = ({ email, password }) =>
  client.post(`/user/login`, { email, password });

export const signUp = ({ email, password }) =>
  client.post(`/user/signUpUser`, { email, password });
