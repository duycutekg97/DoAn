import { useContext, React, useState } from "react";
import styles from "../../styles/styles";
import { AiOutlineEye, AiOutlineEyeInvisible } from "react-icons/ai";
import { Link } from "react-router-dom";
import { useNavigate, Navigate } from "react-router-dom";
import Apis, { authApi, endpoints } from "../../configs/Api";
import cookie from "react-cookies";
import { MyUserContext } from "../../App";
const Login = () => {
  const [user, dispatch] = useContext(MyUserContext);
  const [username, setUserName] = useState("");
  const [password, setPassword] = useState("");
  const [visible, setVisible] = useState(false);
  const nav = useNavigate();
  const login = (evt) => {
    evt.preventDefault();

    const process = async () => {
      try {
        let res = await Apis.post(endpoints["login"], {
          username: username,
          password: password,
        });
        cookie.save("token", res.data);
        console.info(res.data);

        let { data } = await authApi().get(endpoints["current-user"]);
        cookie.save("user", data);

        nav("/home");
        dispatch({
          type: "login",
          payload: data,
        });
      } catch (err) {
        console.error(err);
      }
    };

    process();
    if (user !== null) return <Navigate to="/home" />;
  };

  return (
    <div className="min-h-screen bg-cyan-100 flex flex-col justify-center py-12 sm:px-6 lg:px-8">
      <div className="sm:mx-auto sm:w-full sm:max-w-md">
        <h2 className="mt-6 text-center text-3xl font-extrabold text-gray-900">
          Đăng Nhập Vào Tài Khoản
        </h2>
      </div>
      <div className="mt-8 sm:mx-auto sm:w-full sm:max-w-md">
        <div className="bg-white py-8 px-4 shadow sm:rounded-lg sm:px-10">
          <form className="space-y-6" onSubmit={login}>
            <div>
              <label
                htmlFor="userName"
                className="block text-sm font-medium text-gray-700"
              >
                Tài Khoản
              </label>
              <div className="mt-1">
                <input
                  type="text"
                  name="username"
                  autoComplete="email"
                  required
                  value={username}
                  onChange={(e) => setUserName(e.target.value)}
                  className="appearance-none block w-full px-3 py-2 border border-gray-300 rounded-md shadow-sm placeholder-gray-400 focus:outline-none focus:ring-blue-300 focus:border-blue-600 sm:text-sm"
                />
              </div>
            </div>
            <div>
              <label
                htmlFor="password"
                className="block text-sm font-medium text-gray-700"
              >
                Mật Khẩu
              </label>
              <div className="mt-1 relative">
                <input
                  type={visible ? "text" : "password"}
                  name="password"
                  autoComplete="current-password"
                  required
                  value={password}
                  onChange={(e) => setPassword(e.target.value)}
                  className="appearance-none block w-full px-3 py-2 border border-gray-300 rounded-md shadow-sm placeholder-gray-400 focus:outline-none focus:ring-blue-500 focus:border-blue-500 sm:text-sm"
                />
                {visible ? (
                  <AiOutlineEye
                    className="absolute right-2 top-2 cursor-pointer"
                    size={25}
                    onClick={() => setVisible(false)}
                  />
                ) : (
                  <AiOutlineEyeInvisible
                    className="absolute right-2 top-2 cursor-pointer"
                    size={25}
                    onClick={() => setVisible(true)}
                  />
                )}
              </div>
            </div>

            <div>
              <button
                type="submit"
                className="group relative w-full h-[40px] flex justify-center py-2 px-4 border border-transparent text-sm font-medium rounded-md text-white bg-blue-600 hover:bg-blue-900"
              >
                Xác Nhận
              </button>
            </div>
            <div className={`${styles.noramlFlex} w-full`}>
              <h4>Bạn chưa có tài khoản?</h4>
              <Link to="/register/" className="text-blue-600 pl-2">
                Đăng ký
              </Link>
            </div>
          </form>
        </div>
      </div>
    </div>
  );
};

export default Login;
