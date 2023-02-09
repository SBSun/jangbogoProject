import { createAction, handleActions } from 'redux-actions';

const POST_REGISTER = 'auth/POST_REGISTER';

export const postRegister = createAction(POST_REGISTER);

const initialState = {
  register: {
    email: '',
    password: '',
    paswordConfirm: '',
    name: '',
    address: '',
  },
};

const auth = handleActions(
  {
    [POST_REGISTER]: (state, action) => ({
      register: {
        email: state.email,
        password: state.password,
        paswordConfirm: state.passwordConfirm,
        name: state.name,
        address: state.address,
      },
    }),
  },
  initialState
);

export default auth;
