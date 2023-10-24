import React,{ useContext, useEffect, useState } from "react";
import logo from "../../logomotel.png";
import { MyUserContext } from '../../App';
import cookie from "react-cookies"



const Header = () => {
    const [user, dispatch] = useContext(MyUserContext);
    const logout = () => {
        dispatch({
            "type": "logout"
        })
    }
    return (
        <nav class="bg-white border-gray-200 px-4 lg:px-6 py-2.5 dark:bg-cyan-700">
        <div class="flex flex-wrap justify-between items-center mx-auto max-w-screen-xl">
            <a href="http://localhost:3000/home/" class="flex items-center">
                <img src={logo} class="mr-3 h-6 sm:h-9" alt="MotelHelpness" />
                <span class="self-center text-xl font-semibold whitespace-nowrap dark:text-white">MotelHelpness</span>
            </a>

            {user === null ? (
                <div class="flex items-center lg:order-2">
                    <a href="http://localhost:3000/login/" class="text-gray-800 dark:text-white hover:bg-gray-50 focus:ring-4 focus:ring-gray-300 font-medium rounded-lg text-sm px-4 lg:px-5 py-2 lg:py-2.5 mr-2 dark:hover:bg-gray-700 focus:outline-none dark:focus:ring-gray-800">Đăng Nhập</a>
                </div> ):(
                <div class="flex items-center lg:order-2" >
                    <a href="http://localhost:3000/login/" class="text-gray-800 dark:text-white hover:bg-gray-50 focus:ring-4 focus:ring-gray-300 font-medium rounded-lg text-sm px-4 lg:px-5 py-2 lg:py-2.5 mr-2 dark:hover:bg-gray-700 focus:outline-none dark:focus:ring-gray-800" >Chào {user.username}</a>
                    <button class= "text-gray-800 dark:text-white hover:bg-gray-50 focus:ring-4 focus:ring-gray-300 font-medium rounded-lg text-sm px-4 lg:px-5 py-2 lg:py-2.5 mr-2 dark:hover:bg-gray-700 focus:outline-none dark:focus:ring-gray-800 " onClick={logout}  >Đăng Xuất</button>
                </div>)} 
            <div class="hidden justify-between items-center w-full lg:flex lg:w-auto lg:order-1" id="mobile-menu-2">
                <ul class="flex flex-col mt-4 font-medium lg:flex-row lg:space-x-8 lg:mt-0">
                    <li>
                        <a href="#" class="block py-2 pr-4 pl-3 text-gray-700 border-b border-gray-100 hover:bg-gray-50 lg:hover:bg-transparent lg:border-0 lg:hover:text-primary-700 lg:p-0 dark:text-gray-400 lg:dark:hover:text-white dark:hover:bg-gray-700 dark:hover:text-white lg:dark:hover:bg-transparent dark:border-gray-700">Trang Chủ</a>
                    </li>
                    <li>
                        <a href="#" class="block py-2 pr-4 pl-3 text-gray-700 border-b border-gray-100 hover:bg-gray-50 lg:hover:bg-transparent lg:border-0 lg:hover:text-primary-700 lg:p-0 dark:text-gray-400 lg:dark:hover:text-white dark:hover:bg-gray-700 dark:hover:text-white lg:dark:hover:bg-transparent dark:border-gray-700">Tìm Phòng Trọ</a>
                    </li>
                    <li>
                        <a href="#" class="block py-2 pr-4 pl-3 text-gray-700 border-b border-gray-100 hover:bg-gray-50 lg:hover:bg-transparent lg:border-0 lg:hover:text-primary-700 lg:p-0 dark:text-gray-400 lg:dark:hover:text-white dark:hover:bg-gray-700 dark:hover:text-white lg:dark:hover:bg-transparent dark:border-gray-700">Tin Tức</a>
                    </li>
                    <li>
                        <a href="#" class="block py-2 pr-4 pl-3 text-gray-700 border-b border-gray-100 hover:bg-gray-50 lg:hover:bg-transparent lg:border-0 lg:hover:text-primary-700 lg:p-0 dark:text-gray-400 lg:dark:hover:text-white dark:hover:bg-gray-700 dark:hover:text-white lg:dark:hover:bg-transparent dark:border-gray-700">Liên hệ</a>
                    </li>
                </ul>
            </div>
        </div>
    </nav>
    );
    
  }
  
  export default Header