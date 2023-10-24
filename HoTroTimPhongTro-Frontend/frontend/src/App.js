import React from 'react';
import "./App.css"
import {BrowserRouter,Routes,Route} from "react-router-dom";
import { LoginPage,RegisterPage,HostMotelPage,HomePage,MotelDetailPage } from './Routes1';
import { createContext, useReducer } from "react";
import MyUserReducer from "./components/reducer/MyUserReducer";
import cookie from "react-cookies";
import Header from "./components/Layout/Header";
import Footer from './components/Layout/Footer';

export const MyUserContext = createContext();


const App = () => {
  const [user, dispatch] = useReducer(MyUserReducer, cookie.load("user") || null);
 
  return (
    <MyUserContext.Provider value={[user, dispatch]}>
   
      <BrowserRouter>
        <Header/>
          <Routes>   
              <Route path='/login' element={<LoginPage/>} /> 
              <Route path='/register' element={<RegisterPage/>} /> 
              <Route path='/Host/Motel' element={<HostMotelPage/>} />
              <Route path='/Home' element={<HomePage/>} />
              <Route path='/motels/:motelId' element ={<MotelDetailPage/>}/>
          </Routes>
          <Footer/>
      </BrowserRouter>
   
    </MyUserContext.Provider>
  )
}
export default App;