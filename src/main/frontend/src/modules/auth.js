import { createAction, handleActions } from 'redux-actions';

const POST_REGISTER = 'auth/POST_REGISTER';
const POST_LOGIN = 'auth/POST_LOGIN';

export const postRegister = createAction(POST_REGISTER);
export const postLogin = createAction(POST_LOGIN);

const initialState = {
  register: {
    id: '',
    password: '',
    name: '',
    address: '',
  },
  login: {
    id: '',
    password: '',
    accessToken: '',
    isLogin: false,
  },
};

const auth = handleActions(
  {
    [POST_REGISTER]: (state, action) => ({
      register: action.payload,
    }),
    [POST_LOGIN]: (state, action) => ({
      login: action.payload,
    }),
  },
  initialState
);

export default auth;
