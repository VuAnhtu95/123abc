package com.example.demo.controller;

import com.example.demo.model.User;
import com.example.demo.services.product.IProductService;
import com.example.demo.services.user.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;

@Controller
@RequestMapping
public class UserController {

    @Autowired
    private IProductService productService;

    @Autowired
    private IUserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/")
    public ModelAndView index() {
        return new ModelAndView("/index");
    }

    @GetMapping("/user")
    public ModelAndView user(Principal principal) {
        // Get authenticated user name from Principal
        ModelAndView modelAndView = new ModelAndView("/user");
        modelAndView.addObject("products", productService.findAll());
        System.out.println(principal.getName());
        return modelAndView;

    }

    @GetMapping("/login")
    public String login() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || authentication instanceof AnonymousAuthenticationToken){
            return "login";
        }
        return "redirect:/";
    }

//    @PostMapping("/login")
//    public ModelAndView login(SecurityProperties.User user) {
//        ModelAndView modelAndView;
//        if (userService.checkLogin(user)) {
//            modelAndView = new ModelAndView("/user/homepage");
//            modelAndView.addObject("user", user);
//            return modelAndView;
//        }
//        modelAndView = new ModelAndView("/login");
//        modelAndView.addObject(MESSAGE, "username or password incorrect");
//        return modelAndView;
//    }

    @GetMapping("/admin")
    public ModelAndView admin() {
        // Get authenticated user name from SecurityContext
        SecurityContext context = SecurityContextHolder.getContext();
        System.out.println(context.getAuthentication().getName());
        ModelAndView modelAndView = new ModelAndView("/admin");
        modelAndView.addObject("products", productService.findAll());
        return modelAndView;
    }

    @GetMapping("/signup")
    public ModelAndView showSignUpForm(){
        ModelAndView modelAndView = new ModelAndView("/signup");
        modelAndView.addObject("newUser", new User());
        return modelAndView;
    }

    @PostMapping("/signup")
    public String SignUp(@ModelAttribute User user){
        user.setEnabled(true);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userService.save(user);
        return "redirect:/login";
    }
}