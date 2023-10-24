import React, { useContext, useEffect, useState } from "react";
import { MyUserContext } from "../../App";
import { useParams } from "react-router-dom";
import Apis, { authApi, endpoints } from "../../configs/Api";
import axios from "axios";

const MotelDetail = () => {
  const [user] = useContext(MyUserContext);
  const { motelId } = useParams();
  const [motel, setMotel] = useState(null);
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

  let ifra =
    "https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d15676.350294185033!2d106.61734189999999!3d10.8046046!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x31752b9535b60699%3A0x4737f3be8bd41d5b!2zw4ZPTiBNQUxMIFTDom4gUGjDug!5e0!3m2!1svi!2s!4v1694854022183!5m2!1svi!2s";
  let ifra2;
  useEffect(() => {
    const loadMotel = async () => {
      if (motel === null) {
        //let {data} = await Apis.get(endpoints['detail'](motelId)) ;
        //setMotel(data);
        let x = axios
          .get(`http://localhost:8080/quanlyphongtro/api/motel/${motelId}`)
          .then((res) => {
            setMotel(res.data);
          })
          .catch((err) => console.log(err));
      }
     
      if (image === null) {
        let y = axios
          .get(
            `http://localhost:8080/quanlyphongtro/api/motel/${motelId}/images/`
          )
          .then((ress) => {
            setImage(ress.data);
          })
          .catch((err) => console.log(err));
      }
      
    };
  

    loadMotel();
  });
  useEffect(() => {
    const loadEvaluate = async () => {
        if (evaluate === null) {
            let cmt = axios
              .get(
                `http://localhost:8080/quanlyphongtro/api/motel/${motelId}/evaluate/`
              )
              .then((comment) => {
                setEvaluate(comment.data);
              })
              .catch((err) => console.log(err));
          }
    };
    loadEvaluate();
  })

  const setIfra = async (i) => {
    ifra = i;
  };



  if (image === null || motel === null) return <div>haha</div>;
  return (
    <>
      <div class="block">
       
        <div class=" grid grid-cols-2 md:grid-cols-3 gap-3 p-8 max-w-full md:max-w-full place-items-center text-stone-700 bg-gray-200">
          {image.map((i) => {
            {
              //console.log(i.image);
              return (
                <div class="text-center">
                  <img src={i.image} alt=""></img>
                </div>
              );
            }
          })}
        </div>
        <div class="text-stone-700 bg-gray-200 px-20">
          <h2 class="text-2xl font-bold text-gray-800  mb-2">
            Phòng Trọ Chi Tiết
          </h2>
          <div class="text-gray-600  text-xl mb-4">Tên : {motel.name} </div>
          <div>
            {" "}
            Địa Chỉ Phòng Trọ : Đường {motel.address},Quận/Huyện :{" "}
            {motel.district},Thành Phố/Tỉnh: {motel.cityprovince}
          </div>
          <div> Chi Tiết: {motel.title}</div>
          <div class="flex flex-row items-center">
            {" "}
            Diện Tích Phòng : {motel.acreage} m<sup>2</sup>, Số lượng người ở :{" "}
            {motel.numberofresidents}{" "}
            <svg
              class="text-indigo-600"
              fill="currentColor"
              height="16px"
              aria-hidden="true"
              role="img"
              focusable="false"
              viewBox="0 0 24 24"
              xmlns="http://www.w3.org/2000/svg"
            >
              <path
                fill="currentColor"
                d="M12 12c2.21 0 4-1.79 4-4s-1.79-4-4-4-4 1.79-4 4 1.79 4 4 4zm0 2c-2.67 0-8 1.34-8 4v2h16v-2c0-2.66-5.33-4-8-4z"
              ></path>
              <path d="M0 0h24v24H0z" fill="none"></path>
            </svg>
          </div>
          <div> Mô tả nhà trọ {motel.description}</div>
          <div> Giá Cho Thuê : {motel.price} vnđ</div>
          <div class="invisible max-h-10">
            {(ifra = motel.map)}
            {
              (ifra2 = ifra
                .replace(
                  'width="600" height="450" style="border:0;" allowfullscreen="" loading="lazy" referrerpolicy="no-referrer-when-downgrade">',
                  ""
                )
                .replace('<iframe src="', '"')
                .replace("</iframe>", "")
                .replace('"h', "h"))
            }
            ;<div>{ifra2}</div>
          </div>
          <div class="flex flex-wrap font-semibold pb-8">
            <iframe src={ifra2} height="400px" width="600px"></iframe>
            <div>
              <div className="ml-8 mt-2 flex items-center">
                <span className="inline-block h-24 w-24 rounded-full overflow-hidden">
                  <img
                    src={motel.fkmoteluserId.image}
                    alt="avatar"
                    className="h-full w-full object-cover rounded-full"
                  />
                </span>
                <div class="ml-4"> {motel.fkmoteluserId.firstname}</div>
                <div class="ml-2"> {motel.fkmoteluserId.lastname}</div>
              </div>
              <div class="ml-8">Số Điện Thoại Liên Lạc : {motel.phone}</div>
              <div class="ml-8">
                Địa chỉ liên lạc : {motel.fkmoteluserId.email}{" "}
              </div>
            </div>
          </div>
          <section class="bg-white dark:bg-gray-300 py-8 lg:py-16 antialiased">
            <div class="max-w-2xl mx-auto ">
              <form class="mb-6">
                <div class="py-2 px-4 mb-4 bg-white rounded-lg rounded-t-lg border border-gray-200 dark:bg-cyan-700 dark:border-white">
                  <label for="comment" class="sr-only">
                    Your comment
                  </label>
                  <textarea
                    id="comment"
                    rows="6"
                    class="px-0 w-full text-sm text-gray-900 border-0 focus:ring-0 focus:outline-none dark:text-gray-800 dark:placeholder-gray-400 dark:bg-white"
                    placeholder="Write a comment..."
                    required
                  ></textarea>
                </div>
                <button
                  type="submit"
                  class="inline-flex items-center py-2.5 px-4 text-xs font-medium text-center text-white bg-primary-700 rounded-lg focus:ring-4 focus:ring-primary-200 dark:focus:ring-primary-900 hover:bg-primary-800"
                >
                  Post comment
                </button>
              </form>
             
            </div>
          </section>
        </div>
      </div>
    </>
  );
};

export default MotelDetail;
