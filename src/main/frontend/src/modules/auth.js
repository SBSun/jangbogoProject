import { createAction, handleActions } from 'redux-actions';

const POST_REGISTER = 'auth/POST_REGISTER';

export const postRegister = createAction(POST_REGISTER);

const initialState = {
  register: {
    id: '',
    password: '',
    name: '',
    address: '',
  },
};

const auth = handleActions(
  {
    [POST_REGISTER]: (state, action) => ({
      register: action.payload,
    }),
  },
  initialState
);

export default auth;
