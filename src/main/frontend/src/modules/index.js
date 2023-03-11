import { combineReducers } from 'redux';
import auth from './auth';
import location from './location';
import { persistReducer } from 'redux-persist';
import sessionStorage from 'redux-persist/es/storage/session';

const persistConfig = {
  key: 'root',
  storage: sessionStorage,
  whitelist: ['auth'],
};

const rootReducer = combineReducers({
  auth,
  location,
});

export default persistReducer(persistConfig, rootReducer);
