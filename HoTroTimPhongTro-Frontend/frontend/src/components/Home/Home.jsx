import React, { useEffect, useState } from "react";
import Apis, { endpoints } from "../../configs/Api";
import { Link } from "react-router-dom";
import axios from "axios";

const Home = () => {
  const [motelimage, setmotelimage] = useState(null);
  const [motel, setmotel] = useState(null);
  const [evaluate, setEvaluate] = useState(null);
  const [image, setImage] = useState(null);
  const [province, setProvince] = useState([]);
  const [provincecode, setProvincecode] = useState("");
  const [district, setDistrict] = useState([]);
  const [district2, setDistrict2] = useState([]);
  const [address, setAddress] = useState([]);
  const [price, setPrice] = useState([]);
  const [acreage, setAcreage] = useState([]);
  const [toPrice, setToPrice] = useState([]);
  useEffect(() => {
    const loadMotelImages = async () => {
       let e = endpoints["motelimages"];
      // let f = endpoints["motels"];
      // let h = endpoints["listImageInMotel"];
     
      if(motelimage===null){
       let res = await Apis.get(e);
      setmotelimage(res.data);

      }
      // let ress = await Apis.get(f);
      // if (motel===null){
      //   let m = axios.get("http://localhost:8080/quanlyphongtro/motel/images/").then(resss => {setmotelimage(resss.data)}).catch((err) => console.log(err));
      // }
     
      // setmotelimage(ress.data);
      // setmotel(ress.data);
    };
    loadMotelImages();
    console.log(motelimage);
    
  

  });
  useEffect(() => {
    let x = axios
      .get("https://provinces.open-api.vn/api/p/")
      .then((res) => {
        setProvince(res.data);
      })
      .catch((err) => console.log(err));
  }, []);
  const handleprovince = (event) => {
    const getprovincecode = event.target.value;
    setProvincecode(getprovincecode);
    event.preventDefault();
  };
  useEffect(() => {
    let y = axios
      .get(`https://provinces.open-api.vn/api/p/${provincecode}`)
      .then((res) => {
        setDistrict(res.data);
      })
      .catch((err) => console.log(err));
  }, [provincecode])

  
  if (motelimage === null) return <div>haha</div>;
  return (
  <div>
  <div class="bg-cyan-100 flex flex-col px-40 inline-block">

  <div class="bg-gray-200 text-center text-2xl font-semibold px-40 pt-10">
          Tìm phòng trọ
        </div>
        <div class=" bg-gray-200 text-xl font-semibold px-40 pt-5">
          <form>
            <div class="grid md:grid-cols-4 md:gap-6">
              <div class="relative z-0 w-6/12 mb-6 group">
                <input
                  type="text"
                  name="acreage"
                  value={acreage}
                  onChange={(e) => setAcreage(e.target.value)}
                  class="block py-2.5 px-0 w-full text-sm text-gray-900 bg-transparent border-0 border-b-2 border-gray-300 appearance-none dark:text-white dark:border-gray-600 dark:focus:border-blue-500 focus:outline-none focus:ring-0 focus:border-blue-600 peer"
                  placeholder=" "
                  required
                />
                <label
                  for="acreage"
                  class="peer-focus:font-medium absolute text-sm text-gray-500 dark:text-gray-400 duration-300 transform -translate-y-6 scale-75 top-3 -z-10 origin-[0] peer-focus:left-0 peer-focus:text-blue-600 peer-focus:dark:text-blue-500 peer-placeholder-shown:scale-100 peer-placeholder-shown:translate-y-0 peer-focus:scale-75 peer-focus:-translate-y-6"
                >
                  Diện Tích
                </label>
              </div>
              <div class="relative z-0 w-6/12 mb-6 group">
                <input
                  type="number"
                  name="number"
                  value={price}
                  onChange={(e) => setPrice(e.target.value)}
                  class="block py-2.5 px-0 w-full text-sm text-gray-900 bg-transparent border-0 border-b-2 border-gray-300 appearance-none dark:text-white dark:border-gray-600 dark:focus:border-blue-500 focus:outline-none focus:ring-0 focus:border-blue-600 peer"
                  placeholder=" "
                  required
                />
                <label
                  for="number"
                  class="peer-focus:font-medium absolute text-sm text-gray-500 dark:text-gray-400 duration-300 transform -translate-y-6 scale-75 top-3 -z-10 origin-[0] peer-focus:left-0 peer-focus:text-blue-600 peer-focus:dark:text-blue-500 peer-placeholder-shown:scale-100 peer-placeholder-shown:translate-y-0 peer-focus:scale-75 peer-focus:-translate-y-6"
                >
                  Giá Tiền Từ:
                </label>
              </div>
              <div class="relative z-0 w-6/12 mb-6 group">
                <input
                  type="number"
                  name="number"
                  class="block py-2.5 px-0 w-full text-sm text-gray-900 bg-transparent border-0 border-b-2 border-gray-300 appearance-none dark:text-white dark:border-gray-600 dark:focus:border-blue-500 focus:outline-none focus:ring-0 focus:border-blue-600 peer"
                  placeholder=" "
                  required
                />
                <label
                  for="number"
                  class="peer-focus:font-medium absolute text-sm text-gray-500 dark:text-gray-400 duration-300 transform -translate-y-6 scale-75 top-3 -z-10 origin-[0] peer-focus:left-0 peer-focus:text-blue-600 peer-focus:dark:text-blue-500 peer-placeholder-shown:scale-100 peer-placeholder-shown:translate-y-0 peer-focus:scale-75 peer-focus:-translate-y-6"
                >
                  Đến Giá Tiền:
                </label>
              </div>
              <div class="relative z-0 w-6/12 mb-6 group">
                <input
                  type="number"
                  name="prince"
                  value={toPrice}
                  onChange={(e) => setToPrice(e.target.value)}
                  class="block py-2.5 px-0 w-full text-sm text-gray-900 bg-transparent border-0 border-b-2 border-gray-300 appearance-none dark:text-white dark:border-gray-600 dark:focus:border-blue-500 focus:outline-none focus:ring-0 focus:border-blue-600 peer"
                  placeholder=" "
                  required
                />
                <label
                  for="prince"
                  class="peer-focus:font-medium absolute text-sm text-gray-500 dark:text-gray-400 duration-300 transform -translate-y-6 scale-75 top-3 -z-10 origin-[0] peer-focus:left-0 peer-focus:text-blue-600 peer-focus:dark:text-blue-500 peer-placeholder-shown:scale-100 peer-placeholder-shown:translate-y-0 peer-focus:scale-75 peer-focus:-translate-y-6"
                >
                  Số lượng
                </label>
              </div>
            </div>
            <div class="relative z-0 w-full mb-6 group">
              <input
                type="text"
                name="address"
                value={address}
                onChange={(e) => setAddress(e.target.value)}
                class="block py-2.5 px-0 w-full text-sm text-gray-900 bg-transparent border-0 border-b-2 border-gray-300 appearance-none dark:text-white dark:border-gray-600 dark:focus:border-blue-500 focus:outline-none focus:ring-0 focus:border-blue-600 peer"
                placeholder=" "
                required
              />
              <label
                for="address"
                class="peer-focus:font-medium absolute text-sm text-gray-500 dark:text-gray-400 duration-300 transform -translate-y-6 scale-75 top-3 -z-10 origin-[0] peer-focus:left-0 peer-focus:text-blue-600 peer-focus:dark:text-blue-500 peer-placeholder-shown:scale-100 peer-placeholder-shown:translate-y-0 peer-focus:scale-75 peer-focus:-translate-y-6"
              >
                Địa chỉ cho thuê
              </label>
            </div>
            <select
              id="province"
              class="w-5/12 mr-20"
              onChange={(e) => handleprovince(e)}
            >
              <option>Chọn Thành Phố/ Tỉnh</option>
              {province.map((item) => (
                <option key={item.code} value={item.code}>
                  {item.name}
                </option>
              ))}
            </select>
            <select
              id="district"
              class="w-5/12 ml-10 "
              onChange={(e) => setDistrict2(e.target.value)}
            >
              <option>Chọn quận/huyện</option>
              {district.map((d, index) => (
                <option key={index} value={d.code}>
                  {d.name}
                </option>
              ))}
            </select>

            <button
              type="submit"
              className="group relative w-11/12 h-[40px] mt-7 flex justify-center ml-3 py-2 pl-5 border border-transparent text-sm font-medium rounded-md text-white bg-cyan-700 hover:bg-blue-300"
            >
              Tìm Phòng
            </button>
          </form>
        </div>
  {motelimage.map((p) => {
    //{motel.map (m => {
    //console.log(m.id);
    //console.log(p.fkmotelimage_motelId)
    // if(p.fkmotelimage_motelId =  m.id)
    // console.log({p.fkmotelimagemotelId});
    {
      let motelid = p.fkmotelimagemotelId.id;
      let url = `/motels/${motelid}`;
      return (
        <div class="flex flex-wrap text-stone-700 bg-gray-200 font-semibold">
          <img
            class="mt-20 mb-10 h-auto max-w-xs border-double border-2 border-sky-500"
            src={p.image}
            alt=""
          ></img>
          <div class="mt-20 mx-4 text-lg border-double hover:border-black hover:border-2">
            <div>{p.fkmotelimagemotelId.title}</div>
            <div>{p.fkmotelimagemotelId.price}</div>
            <div>
              {p.fkmotelimagemotelId.address}{" "}
              {p.fkmotelimagemotelId.district}{" "}
              {p.fkmotelimagemotelId.cityprovince}
            </div>
            <Link
              to={url}
              class="btn btn-success ml-20 bg-yellow-100"
              variant="success"
            >
              Xem chi tiết
            </Link>
          </div>
        </div>
      );
    }
    // })}
  })}
  </div>
  </div>
  )
};

export default Home;
