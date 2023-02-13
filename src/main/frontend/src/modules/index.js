import { combineReducers } from 'redux';
import auth from './auth';
import location from './location';

const rootReducer = combineReducers({
  auth,
  location,
});

export default rootReducer;
