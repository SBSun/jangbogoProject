import { createAction, handleActions } from 'redux-actions';

const POST_LOGIN = 'auth/POST_LOGIN';
const POST_LOGOUT = 'auth/POST_LOGOUT';

export const postLogin = createAction(POST_LOGIN);
export const postLogout = createAction(POST_LOGOUT);

const initialState = {
  accessToken: '',
  email: '',
  name: '',
  loginType: '',
  isLogin: false,
};

const auth = handleActions(
  {
    [POST_LOGIN]: (state, action) => ({
      ...action.payload,
      isLogin: true,
    }),
    [POST_LOGOUT]: (state, action) => ({
      accessToken: '',
      email: '',
      name: '',
      loginType: '',
      isLogin: false,
    }),
  },
  initialState
);

export default auth;
