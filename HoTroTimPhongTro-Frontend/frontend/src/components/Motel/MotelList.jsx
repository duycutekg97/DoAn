// // import React from 'react'

// // const MotelList = () => {
// //   return (
// //     <div>
// //     <div > <iframe src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d15676.350294185033!2d106.61734189999999!3d10.8046046!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x31752b9535b60699%3A0x4737f3be8bd41d5b!2zw4ZPTiBNQUxMIFTDom4gUGjDug!5e0!3m2!1svi!2s!4v1694854022183!5m2!1svi!2s" width="600" height="450" ></iframe></div></div>

// //        //    {ifra = String(motel.map.replace("width=\"600\" height=\"450\" style=\"border:0;\" allowfullscreen=\"\" loading=\"lazy\" referrerpolicy=\"no-referrer-when-downgrade","").replace("\<iframe src=\"","\"").replace("\"\>\<\/iframe\>","\""))};
// //     //     <iframe src={ifra} width="600px" height="450px"></iframe>


// //     // <div>
// //     {evaluate.map((e) => {
// //       {
// //         return (
// //           <div>
// //             <article class="p-6 text-base bg-white rounded-lg dark:bg-gray-900">
// //               <div class="flex items-center">
// //                 <p class="inline-flex items-center mr-3 text-sm text-gray-900 dark:text-white font-semibold">
// //                   <img
// //                     class="mr-2 w-6 h-6 rounded-full"
// //                     src={e.fkevaluteuserIdRespondent.image}
// //                     alt="Michael Gough"
// //                   />{" "}
// //                   {e.fkevaluteuserIdRespondent.firstname}{" "}
// //                   {e.fkevaluteuserIdRespondent.lastname}
// //                 </p>
// //                 <p class="text-sm text-gray-600 dark:text-gray-400">
// //                   <time
// //                     pubdate
// //                     datetime="2023"
// //                     title="October , 2023"
// //                   >
// //                     Oct , 2023
// //                   </time>
// //                 </p>
// //               </div>
// //               <p class="text-gray-500 dark:text-gray-400">
// //                 {e.comment}
// //               </p>
// //               <div class="flex items-center mt-4 space-x-4">
// //                 <button
// //                   type="button"
// //                   class="flex items-center text-sm text-gray-500 hover:underline dark:text-gray-400 font-medium"
// //                 >
// //                   <svg
// //                     class="mr-1.5 w-3.5 h-3.5"
// //                     aria-hidden="true"
// //                     xmlns="http://www.w3.org/2000/svg"
// //                     fill="none"
// //                     viewBox="0 0 20 18"
// //                   >
// //                     <path
// //                       stroke="currentColor"
// //                       stroke-linecap="round"
// //                       stroke-linejoin="round"
// //                       stroke-width="2"
// //                       d="M5 5h5M5 8h2m6-3h2m-5 3h6m2-7H2a1 1 0 0 0-1 1v9a1 1 0 0 0 1 1h3v5l5-5h8a1 1 0 0 0 1-1V2a1 1 0 0 0-1-1Z"
// //                     />
// //                   </svg>
// //                   Reply
// //                 </button>
// //               </div>
// //             </article>
// //           </div>
// //         );
// //       }
// //     })}
// //     //   <div class="bg-cyan-100 flex flex-col px-40 inline-block">
// //     //   {motelimage.map((p) => {
// //     //     //{motel.map (m => {
// //     //     //console.log(m.id);
// //     //     //console.log(p.fkmotelimage_motelId)
// //     //     // if(p.fkmotelimage_motelId =  m.id)
// //     //     //console.log(p.fkmotelimagemotelId);
// //     //     {
// //     //       let motelid = p.fkmotelimagemotelId.id;
// //     //       let url = `/motels/${motelid}`;
// //     //       return (
// //     //         <div class="flex flex-wrap text-stone-700 bg-gray-200 font-semibold">
// //     //           <img
// //     //             class="mt-20 mb-10 h-auto max-w-xs border-double border-2 border-sky-500"
// //     //             src={p.image}
// //     //             alt=""
// //     //           ></img>
// //     //           <div class="mt-20 mx-4 text-lg border-double hover:border-black hover:border-2">
// //     //             <div>{p.fkmotelimagemotelId.title}</div>
// //     //             <div>{p.fkmotelimagemotelId.price}</div>
// //     //             <div>
// //     //               {p.fkmotelimagemotelId.address}{" "}
// //     //               {p.fkmotelimagemotelId.district}{" "}
// //     //               {p.fkmotelimagemotelId.cityprovince}
// //     //             </div>
// //     //             <Link
// //     //               to={url}
// //     //               class="btn btn-success ml-20 bg-yellow-100"
// //     //               variant="success"
// //     //             >
// //     //               Xem chi tiết
// //     //             </Link>
// //     //           </div>
// //     //         </div>
// //     //       );
// //     //     }
// //     //     // })}
// //     //   })}
// //     // </div>
// //     // </div>
// //   )
// // }

// // export default MotelList







// {evaluate.map((e) => {
//   {
//     return (
//       <div>
//         <article class="p-6 text-base bg-white rounded-lg dark:bg-gray-900">
//           <div class="flex items-center">
//             <p class="inline-flex items-center mr-3 text-sm text-gray-900 dark:text-white font-semibold">
//               <img
//                 class="mr-2 w-6 h-6 rounded-full"
//                 src={e.fkevaluteuserIdRespondent.image}
//                 alt="Michael Gough"
//               />{" "}
//               {e.fkevaluteuserIdRespondent.firstname}{" "}
//               {e.fkevaluteuserIdRespondent.lastname}
//             </p>
//             <p class="text-sm text-gray-600 dark:text-gray-400">
//               <time
//                 pubdate
//                 datetime="2023"
//                 title="October , 2023"
//               >
//                 Oct , 2023
//               </time>
//             </p>
//           </div>
//           <p class="text-gray-500 dark:text-gray-400">
//             {e.comment}
//           </p>
//           <div class="flex items-center mt-4 space-x-4">
//             <button
//               type="button"
//               class="flex items-center text-sm text-gray-500 hover:underline dark:text-gray-400 font-medium"
//             >
//               <svg
//                 class="mr-1.5 w-3.5 h-3.5"
//                 aria-hidden="true"
//                 xmlns="http://www.w3.org/2000/svg"
//                 fill="none"
//                 viewBox="0 0 20 18"
//               >
//                 <path
//                   stroke="currentColor"
//                   stroke-linecap="round"
//                   stroke-linejoin="round"
//                   stroke-width="2"
//                   d="M5 5h5M5 8h2m6-3h2m-5 3h6m2-7H2a1 1 0 0 0-1 1v9a1 1 0 0 0 1 1h3v5l5-5h8a1 1 0 0 0 1-1V2a1 1 0 0 0-1-1Z"
//                 />
//               </svg>
//               Reply
//             </button>
//           </div>
//         </article>
//       </div>
//     );
//   }
// })}