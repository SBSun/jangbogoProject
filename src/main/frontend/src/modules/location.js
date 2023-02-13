import { createAction, handleActions } from 'redux-actions';

const SET_LOCATION = 'location/SET_LOCATION';
const SET_IS_VISIBLE = 'location/SET_IS_VISIBLE';

export const setLocation = createAction(SET_LOCATION);
export const setIsVisible = createAction(SET_IS_VISIBLE);

const initialState = {
  id: '',
  name: '',
  isVisible: true,
};

const location = handleActions(
  {
    [SET_LOCATION]: (state, action) => ({
      id: action.payload.id,
      name: action.payload.name,
      isVisible: false,
    }),
    [SET_IS_VISIBLE]: (state, action) => ({
      ...state,
      isVisible: action.payload,
    }),
  },
  initialState
);

export default location;
