package com.example.demo.controllers;

import com.example.demo.models.Admin;
import com.example.demo.repositories.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping(path = "/demo")
public class AdminController {
    @Autowired
    private AdminRepository adminRepository ;

    @RequestMapping("/show")
    public String showForm(Model model){
        model.addAttribute("admin",new Admin());
        return "inp";
    }

    @GetMapping("/add")
    public @ResponseBody String addAuthor(@RequestParam("firstname") String firstName,
                                          @RequestParam("lastname") String lastName,
                                          @RequestParam("Phone") String phone){
        Admin admin = new Admin(firstName, lastName, phone) ;
        adminRepository.save(admin);
        return "saved" ;
    }

    @GetMapping("/all")
    public @ResponseBody Iterable<Admin> allAdmins(){
        return adminRepository.findAll() ;
    }

    @GetMapping("/all2")
    public String allAdmins2(Model model){
        List<Admin> admins = (List<Admin>) adminRepository.findAll() ;
        model.addAttribute("admins", admins) ;
        return "admins" ;
    }

    @RequestMapping(value = "/deleteContact",method = RequestMethod.GET)
    public ModelAndView deleteContact(@RequestParam("id") long idd){
        adminRepository.deleteById(idd);
        return new ModelAndView("redirect:/demo/all2");
    }
}
