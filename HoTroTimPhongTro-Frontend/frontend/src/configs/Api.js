import axios from "axios";
import cookie from "react-cookies";

const SERVER_CONTEXT = "/quanlyphongtro";
const SERVER = "http://localhost:8080";

export const endpoints = {
    "login": `${SERVER_CONTEXT}/api/login/`,
    "current-user": `${SERVER_CONTEXT}/api/current-user/`,
    "register": `${SERVER_CONTEXT}/api/register/`,
    "motelimages":`${SERVER_CONTEXT}/api/motel/images/`,
    "motels":`${SERVER_CONTEXT}/api/motel/`,
    "details": (motelId) =>`${SERVER_CONTEXT}/api/details/motel/${motelId}/"`,
    "detail": (motelId) => `${SERVER_CONTEXT}/api/motel/${motelId}`

}

export const authApi = () => {
    return axios.create({
        baseURL: SERVER,
        headers: {
            "Authorization":  cookie.load("token")
        }
    })
}

export default axios.create({
    baseURL: SERVER
})