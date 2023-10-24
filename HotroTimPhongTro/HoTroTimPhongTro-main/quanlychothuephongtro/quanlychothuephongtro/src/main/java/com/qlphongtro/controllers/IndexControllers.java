/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.qlphongtro.controllers;

import com.qlphongtro.pojo.Evaluate;
import com.qlphongtro.pojo.Findmotel;
import com.qlphongtro.pojo.Follower;
import com.qlphongtro.pojo.Motel;
import com.qlphongtro.pojo.User;

import com.qlphongtro.service.EvaluateService;
import com.qlphongtro.service.FindMotelService;
import com.qlphongtro.service.FollowerService;
import com.qlphongtro.service.Impl.StaticSession;
import com.qlphongtro.service.MotelImageService;
import com.qlphongtro.service.MotelService;
import com.qlphongtro.service.RoleUserService;
import com.qlphongtro.service.UserService;
import java.security.Principal;
import java.util.Map;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 *
 * @author HOME
 */
@Controller
@ControllerAdvice
@PropertySource("classpath:configs.properties")
public class IndexControllers {

    @Autowired
    private UserService userService;
    @Autowired
    private FindMotelService findMotelService;
    @Autowired
    private MotelService motelService;
    @Autowired
    private MotelImageService motelImageService;
    @Autowired
    private RoleUserService roleService;
    @Autowired
    private FollowerService followerService;
    @Autowired
    private Environment env;
    @Autowired
    private EvaluateService evalService;

    @ModelAttribute
    public void commonAttr(Model model, Principal u) {
        if (u != null) {
            model.addAttribute("principalInfo", this.userService.getUserByUsername(u.getName()));
        }

    }

    @GetMapping(path = {"/", "/trang-chu/"})
    public String index(Model model, @RequestParam Map<String, String> params) {
        model.addAttribute("msg", "Welcome");
        return "index";
    }

    @GetMapping("/index/Motel/")
    public String indexMotel(Model model, @RequestParam Map<String, String> params, Principal u) {
//        model.addAttribute("cityList", this.cityService.getCity());
//        model.addAttribute("districtList", this.districtService.getDistrict());
//        model.addAttribute("wardList", this.wardService.getWard());

        if (u != null) {

            model.addAttribute("listMotels", this.motelService.getListMotel(params, this.userService.getUserByUsername(u.getName())));
            model.addAttribute("listMotelImages", this.motelImageService.getImage());

            int pageSize = Integer.parseInt(this.env.getProperty("PAGE_SIZE"));
            long count = this.motelService.countMotel(this.userService.getUserByUsername(u.getName()));
            model.addAttribute("counter", Math.ceil(count * 1.0 / pageSize));
        } else {
            model.addAttribute("listMotels", this.motelService.getListMotel(params));
            model.addAttribute("listMotelImages", this.motelImageService.getImage());

            int pageSize = Integer.parseInt(this.env.getProperty("PAGE_SIZE"));
            long count = this.motelService.countMotel();
            model.addAttribute("counter", Math.ceil(count * 1.0 / pageSize));
        }
        return "indexMotel";
    }

    @GetMapping("/index/User/")
    public String listUser(Model model, @RequestParam Map<String, String> params) {
        model.addAttribute("roleList", this.roleService.getLoaiNguoiDung());
        model.addAttribute("listUsers", this.userService.getListUser(params));
        int pageSize = Integer.parseInt(this.env.getProperty("PAGE_SIZE"));
        long count = this.userService.countUser();
        model.addAttribute("counter", Math.ceil(count * 1.0 / pageSize));

        return "indexuser";
    }

    @GetMapping("/detailMotel/{id}")
    public String detailMotel(Model model, @PathVariable(value = "id") int id, Principal u) {
        Motel m = this.motelService.getMotelById(id);
        if (m.getApply().equals(false)) {
            if (u != null) {
                if (this.userService.getUserByUsername(u.getName()).getFkuserroleuserId().getName().equalsIgnoreCase("ROLE_ADMIN")
                        || this.userService.getUserByUsername(u.getName()).getFkuserroleuserId().getName().equalsIgnoreCase("ROLE_HOST")
                        && m.getFkmoteluserId().equals(this.userService.getUserByUsername(u.getName()))) {
                    model.addAttribute("motelImage", this.motelImageService.getImageByMotelId(id));
                    model.addAttribute("motelDetail", m);
                    model.addAttribute("EvaluatelAdd", new Evaluate());
                    model.addAttribute("EvaluatelList", this.evalService.getEvaluate(this.motelService.getMotelById(id)));
                    model.addAttribute("ReplyList", this.evalService.getEvaluate());

//        int pageSize = Integer.parseInt(this.env.getProperty("PAGE_SIZE"));
                    long count = this.evalService.countEvaluate(this.motelService.getMotelById(id));
                    model.addAttribute("counter", count);
                    return "motelDetail";
                }
            }
        } else {
            if (u != null) {
                if (this.followerService.checkFollow(this.userService.getUserByUsername(u.getName()), m.getFkmoteluserId()) > 0) {
                    model.addAttribute("follow", this.followerService.getFollowerByRenterAndHost(this.userService.getUserByUsername(u.getName()), m.getFkmoteluserId()));
                }
            }

            model.addAttribute("motelImage", this.motelImageService.getImageByMotelId(id));
            model.addAttribute("motelDetail", m);
            model.addAttribute("EvaluatelAdd", new Evaluate());
            model.addAttribute("EvaluatelList", this.evalService.getEvaluate(this.motelService.getMotelById(id)));
            model.addAttribute("ReplyList", this.evalService.getEvaluate());

//        int pageSize = Integer.parseInt(this.env.getProperty("PAGE_SIZE"));
            long count = this.evalService.countEvaluate(this.motelService.getMotelById(id));
            model.addAttribute("counter", count);
            return "motelDetail";
        }
        return "errPage";
    }

    @PostMapping("/detailMotel/{motelId}/evaluate")
    public String addEvaluate(@PathVariable(value = "motelId") int motelId, @ModelAttribute(value = "EvaluatelAdd") @Valid Evaluate evaluate,
            Principal u,
            Model model,
            BindingResult rs
    ) {
        if (!rs.hasErrors()) {
            Motel motel = this.motelService.getMotelById(motelId);
            if (evalService.addOrUpdateEvaluate(evaluate, motel, u) == true) {
                return "redirect:/detailMotel/" + motelId;
            } else {
                model.addAttribute("msgErrShow", StaticSession.msgError);
                return "redirect:/detailMotel/" + motelId;
            }
        }
        return "motelDetail";
    }

    @PostMapping("/detailMotel/{motelId}/evaluate/{evaluateId}/reply/{evaluateReplyId}")
    public String addEvaluateReply(@PathVariable(value = "motelId") int motelId,
            @PathVariable(value = "evaluateId") int evaluateId,
            @PathVariable(value = "evaluateReplyId") int evaluateReplyId,
            @ModelAttribute(value = "EvaluatelAdd") @Valid Evaluate evaluate,
            Principal u,
            Model model,
            BindingResult rs
    ) {
        if (!rs.hasErrors()) {
            Motel motel = this.motelService.getMotelById(motelId);
            Evaluate eId = this.evalService.getEvaluateById(evaluateId);
            Evaluate eReplyId = this.evalService.getEvaluateById(evaluateReplyId);
            if (evalService.addOrUpdateEvaluate(evaluate, motel, eId, eReplyId, u) == true) {
                return "redirect:/detailMotel/" + motelId;
            } else {
                model.addAttribute("msgErrShow", StaticSession.msgError);
                return "redirect:/detailMotel/" + motelId;
            }
        }
        return "motelDetail";
    }

    @GetMapping("/indexFindAccommodation/")
    public String indexFindAccommodation(Model model, @RequestParam Map<String, String> params
    ) {
        model.addAttribute("FindAccommodationAdd", new Findmotel());
        model.addAttribute("findMotelList", this.findMotelService.getFindmotel(params));
        model.addAttribute("findMotelListReply", this.findMotelService.getFindmotel());

        int pageSize = Integer.parseInt(this.env.getProperty("PAGE_SIZE"));
        long count = this.findMotelService.countFindmotel();
        model.addAttribute("counter", Math.ceil(count * 1.0 / pageSize));

        return "indexFindAccommodation";
    }

    @PostMapping("/indexFindAccommodation/post/")
    public String add(@ModelAttribute(value = "FindAccommodationAdd")
            @Valid Findmotel findmotel, Model model,@RequestParam Map<String, String> params,
            Principal u,
            BindingResult rs
    ) 
    {
        
        if (!rs.hasErrors()) {
            if (findMotelService.addOrUpdateFindmotel(findmotel, u) == true) {
                return "redirect:/indexFindAccommodation/";
            }
            else{
                 return "redirect:/indexFindAccommodation/";
            }
        }
        return "indexFindAccommodation";
    }

    @PostMapping("/indexFindAccommodation/post/{findmotelId}/findmotelId/{findmotelIdReply}/findmotelIdReply")
    public String addFindAccommodationReply(@PathVariable(value = "findmotelId") int findmotelId,
            @PathVariable(value = "findmotelIdReply") int findmotelIdReply,
            @ModelAttribute(value = "FindAccommodationAdd") @Valid Findmotel findmotel,
            Model model,
            Principal u,
            BindingResult rs
    ) {
        if (!rs.hasErrors()) {
            Findmotel finddmotelId1 = this.findMotelService.getFindmotelById(findmotelId);
            Findmotel finddmotelIdReply1 = this.findMotelService.getFindmotelById(findmotelIdReply);
            if (findMotelService.addOrUpdateFindmotel(findmotel, finddmotelId1, finddmotelIdReply1, u) == true) {
                return "redirect:/indexFindAccommodation/";
            }
            else{
                 return "redirect:/indexFindAccommodation/";
            }
        }
        return "indexFindAccommodation";
    }

    @PostMapping("/follow/{renterId}/renter/{hostId}/host/following")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void follow(@PathVariable(value = "renterId") int renterId,
            @PathVariable(value = "hostId") int hostId) {
        User userRenter = this.userService.getUserById(renterId);
        User userHost = this.userService.getUserById(hostId);
        this.followerService.following(userRenter, userHost);
    }

    @PostMapping("/follow/{renterId}/renter/{hostId}/host/unfollow")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void unFollow(@PathVariable(value = "renterId") int renterId,
            @PathVariable(value = "hostId") int hostId) {
        User userRenter = this.userService.getUserById(renterId);
        User userHost = this.userService.getUserById(hostId);
        this.followerService.Unfollow(userRenter, userHost);
    }

    @GetMapping("/details/users/{id}")
    public String details(Model model, @PathVariable(value = "id") int id, Principal u) {
        if (u != null) {
            if (this.followerService.checkFollow(this.userService.getUserByUsername(u.getName()), this.userService.getUserById(id)) > 0) {
                model.addAttribute("follow", this.followerService.getFollowerByRenterAndHost(this.userService.getUserByUsername(u.getName()), this.userService.getUserById(id)));
            }
        }
        model.addAttribute("host", this.userService.getUserById(id));
        model.addAttribute("numberFollower", this.followerService.countFollowerByHost(this.userService.getUserById(id)));
        return "userDetails";
    }
}
