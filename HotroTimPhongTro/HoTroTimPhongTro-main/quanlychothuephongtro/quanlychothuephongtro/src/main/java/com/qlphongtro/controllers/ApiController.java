/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.qlphongtro.controllers;

import com.qlphongtro.components.JwtService;
import com.qlphongtro.pojo.Evaluate;
import com.qlphongtro.pojo.Findmotel;
import com.qlphongtro.pojo.Motel;
import com.qlphongtro.pojo.Motelimage;
import com.qlphongtro.pojo.User;
import com.qlphongtro.service.EvaluateService;
import com.qlphongtro.service.FindMotelService;
import com.qlphongtro.service.MotelImageService;
import com.qlphongtro.service.MotelService;
import com.qlphongtro.service.UserService;
import java.security.Principal;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author HOME
 */
@RestController
@RequestMapping("/api")
public class ApiController {

    @Autowired
    private MotelService motelService;
    @Autowired
    private MotelImageService motelImageService;
    @Autowired
    private UserService userService;
    @Autowired
    private FindMotelService findMotelService;
    @Autowired
    private EvaluateService evalService;
    @Autowired
    private JwtService jwtService;

    //Danh sách nhà trọ. Tải danh sách theo user đăng nhập (Nếu là chủ trọ thì chỉ tải Nhà trọ của chủ trọ)
    @RequestMapping("/motel/")
    @CrossOrigin
    public ResponseEntity<List<Motel>> list(@RequestParam Map<String, String> params, Principal user) {
        if (user != null) {
//            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//            User u = this.userService.getUserByUsername(authentication.getName());
            return new ResponseEntity<>(this.motelService.getListMotel(params, this.userService.getUserByUsername(user.getName())), HttpStatus.OK);
        }
        return new ResponseEntity<>(this.motelService.getListMotel(params), HttpStatus.OK);
    }

    //Thông tin nhà trọ
    @RequestMapping(path = "/details/motel/{motelId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin
    public ResponseEntity<Motel> motelDetails(@PathVariable(value = "motelId") int id) {
        return new ResponseEntity<>(this.motelService.getMotelById(id), HttpStatus.OK);
    }

    @RequestMapping(path = "/motel/{motelId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin
    public ResponseEntity<Motel> motelDetail(@PathVariable(value = "motelId") int id) {
        return new ResponseEntity<>(this.motelService.getMotelById(id), HttpStatus.OK);
    }
    //Danh sách hình ảnh nhà trọS
    @RequestMapping("/motel/images/")
    @CrossOrigin
    public ResponseEntity<List<Motelimage>> listMotelImages(@RequestParam Map<String, String> params, Principal u) {
        return new ResponseEntity<>(this.motelImageService.getImage(), HttpStatus.OK);
    }

    //Danh sách hình ảnh theo nhà trọ
    @RequestMapping(path = "/motel/{motelId}/images/", produces = MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin
    public ResponseEntity<List<Motelimage>> listMotelImagesByMotel(@PathVariable(value = "motelId") int id) {
        return new ResponseEntity<>(this.motelImageService.getImageByMotelId(id), HttpStatus.OK);
    }

    //Danh sách đánh giá theo nhà trọ
    @RequestMapping(path = "/motel/{motelId}/evaluate/", produces = MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin
    public ResponseEntity<List<Evaluate>> listMotelEvaluateByMotel(@PathVariable(value = "motelId") int id) {
        return new ResponseEntity<>(this.evalService.getEvaluate(this.motelService.getMotelById(id)), HttpStatus.OK);
    }

    //Danh sách đánh giá dùng cho Phản hồi bình luận (Lồng vào Danh sách đánh giá theo nhà trọ)
    @RequestMapping(path = "/motel/evaluate/", produces = MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin
    public ResponseEntity<List<Evaluate>> listMotelEvaluate() {
        return new ResponseEntity<>(this.evalService.getEvaluate(), HttpStatus.OK);
    }

    //Danh sách bài đăng Tìm nhà trọ
    @RequestMapping("/FindAccommodation/")
    @CrossOrigin
    public ResponseEntity<List<Findmotel>> listFindAccommodation(@RequestParam Map<String, String> params, Principal u) {
        return new ResponseEntity<>(this.findMotelService.getFindmotel(params), HttpStatus.OK);
    }

    //Danh sách bài đăng Tìm nhà trọ dùng cho Phản hồi bình luận (Lồng vào Danh sách bài đăng Tìm nhà trọ)
    @RequestMapping("/FindAccommodation/reply/")
    @CrossOrigin
    public ResponseEntity<List<Findmotel>> listFindAccommodationReply() {
        return new ResponseEntity<>(this.findMotelService.getFindmotel(), HttpStatus.OK);
    
    }
     @PostMapping(path = "/register/", 
            consumes = {MediaType.MULTIPART_FORM_DATA_VALUE}, 
            produces = {MediaType.APPLICATION_JSON_VALUE})
    @CrossOrigin
    public ResponseEntity<User> addUser(@RequestParam Map<String, String> params, @RequestPart MultipartFile avatar) {
        User user = this.userService.addUser(params, avatar);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    @PostMapping("/login/")
    @CrossOrigin
    public ResponseEntity<String> login(@RequestBody User user) {
        if (this.userService.authUser(user.getUsername(), user.getPassword()) == true) {
            String token = this.jwtService.generateTokenLogin(user.getUsername());

            return new ResponseEntity<>(token, HttpStatus.OK);
        }

        return new ResponseEntity<>("error", HttpStatus.BAD_REQUEST);
    }

    @GetMapping(path = "/current-user/", produces = MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin
    public ResponseEntity<User> details(Principal user) {
        User u = this.userService.getUserByUsername(user.getName());
        return new ResponseEntity<>(u, HttpStatus.OK);
    }
    //Thêm đánh giá,phải gửi kèm token
    @PostMapping(path = "/add/evaluate/{motelId}/", 
            produces = {MediaType.APPLICATION_JSON_VALUE})
    @CrossOrigin
    public ResponseEntity<Evaluate> addEvaluate(@RequestBody Evaluate evaluate
                        ,@PathVariable(value = "motelId") int id) {
        
        Evaluate e = this.evalService.addEvaluate(evaluate, this.motelService.getMotelById(id));
        return new ResponseEntity<>(e, HttpStatus.CREATED);
    }
    
    //Thêm phản hồi đánh giá
    @PostMapping(path = "/add/evaluate/{motelId}/addreply/{evaluateId}/reply/{evaluateReplyId}", 
            produces = {MediaType.APPLICATION_JSON_VALUE})
    @CrossOrigin
    public ResponseEntity<Evaluate> addEvaluateReply(@RequestBody Evaluate evaluate
                        ,@PathVariable(value = "motelId") int id,
                        @PathVariable(value = "evaluateId") int evaluateId,
                        @PathVariable(value = "evaluateReplyId") int evaluateReplyId) {
        
        Evaluate e = this.evalService.addEvaluate(evaluate, this.motelService.getMotelById(id)
                                                    ,this.evalService.getEvaluateById(evaluateId)
                                                    ,this.evalService.getEvaluateById(evaluateReplyId));
        return new ResponseEntity<>(e, HttpStatus.CREATED);
    }
}
