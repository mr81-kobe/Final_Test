package com.example.finalproto.controller;

import com.example.finalproto.Repository.UserRepository;
import com.example.finalproto.Service.ChatService;
import com.example.finalproto.Service.UserService;
import com.example.finalproto.Service.UserServiceImpl;
import com.example.finalproto.dto.userDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class MainController {
public static int i=0;
@Autowired
UserService service;
@Autowired
ChatService chatService;

@GetMapping("/chat")
public String chat(@RequestParam("id") String id, Model model, HttpServletRequest request, HttpServletResponse response){
    model.addAttribute("id", id);
    HttpSession session = request.getSession();
    model.addAttribute("userID", (String)session.getAttribute("userID"));
    model.addAttribute("list",chatService.get_chatList((String)session.getAttribute("userID"),id));


    return "chat";
}

@GetMapping("/")
public String index(){

    return "index";
}
@GetMapping("/loginform")
public  String loginform(){

    return "loginform";
}
    @GetMapping("/registform")
    public  String registform(){

        return "registform";
    }

    @GetMapping("/matching")
    public String matching(){

    return "matching";
    }

    @GetMapping("/regist")
    public String regist(userDTO user){
        service.regist(user);

        System.out.println(user.toString());

    return "/loginform";
    }
    @GetMapping("/login")
    public String login(userDTO user , HttpServletRequest request, Model model){
        String log=service.login(user) ? user.getId() : null;
        HttpSession session =request.getSession();

        if(log==null){
            model.addAttribute("message", "로그인 실패");
            return "/loginform";
        }else{
            session.setAttribute("userID",log );

            return "/index";
        }


    }
    @GetMapping("/chatList")
    public void chatList(Model model){
    List<String> list=service.getList();
    model.addAttribute("clientList",list);

    }




}
