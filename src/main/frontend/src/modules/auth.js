import { createAction, handleActions } from 'redux-actions';

const POST_REGISTER = 'auth/POST_REGISTER';
const POST_LOGIN = 'auth/POST_LOGIN';

export const postRegister = createAction(POST_REGISTER, input => ({
  register: {
    email: input.email,
    password: input.password,
    paswordConfirm: input.paswordConfirm,
    name: input.name,
    address: input.address,
  },
}));
export const postLogin = createAction(POST_LOGIN, input => ({
  login: { email: input.email, password: input.password },
}));

const initialState = {
  register: {
    email: '',
    password: '',
    paswordConfirm: '',
    name: '',
    address: '',
  },
  login: {
    email: '',
    password: '',
  },
};

const auth = handleActions(
  {
    [POST_REGISTER]: (state, action) => ({
      email: state.email,
      password: state.password,
      paswordConfirm: state.passwordConfirm,
      name: state.name,
      address: state.address,
    }),
    [POST_LOGIN]: (state, action) => ({
      email: state.email,
      password: state.password,
    }),
  },
  initialState
);

export default auth;
