package com.example.demo.controllers;

import com.example.demo.models.Otchet;
import com.example.demo.repositories.OtchetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping(path = "/otchet")
public class OtchetController {
    @Autowired
    private OtchetRepository otchetRepository;
    private long a;

    @GetMapping("/main")
    public String allotchet(Model model){
        List<Otchet> otchets = (List<Otchet>) otchetRepository.findAll();
        model.addAttribute("otchets",otchets);
        return "otchets";
    }

    @RequestMapping("/addd")
    public String showFormmm(Model model){
        model.addAttribute("otchett",new Otchet());
        return "otchetupdate";
    }

    @GetMapping("/add")
    public String otchetForm(Model model){
        model.addAttribute("otchet",new Otchet());
        return "otchet_add_form";
    }

    @GetMapping("/main2")
    public @ResponseBody
    Iterable<Otchet> allOtchets(){
        return otchetRepository.findAll() ;
    }

    @PostMapping("/add")
    public String add(@ModelAttribute Otchet jurnal){
        otchetRepository.save(jurnal);
        return "redirect:/jurnal/main";
    }

    @PostMapping("/adds")
    public String addsOtchets(@ModelAttribute Otchet otchet){
        Otchet otchet1 = new Otchet();
        otchet1.setId(a);
        otchet1.setOtchetName(otchet.getOtchetName());
        otchet1.setYear(otchet.getYear());
        otchetRepository.save(otchet) ;

        return "redirect:/book/main" ;
    }

    @RequestMapping(value = "/updateOtchet",method = RequestMethod.GET)
    public ModelAndView updateAuthors(Model model, @RequestParam("id") long id){
        a=id;
        Optional<Otchet>  otchet = (Optional <Otchet> ) otchetRepository.findById(id);
        model.addAttribute(" otchet", otchet);
        return new ModelAndView(" otchetupdate");
    }

    @RequestMapping(value = "/deleteOtchet",method = RequestMethod.GET)
    public ModelAndView deleteContact(@RequestParam("id") long idd){
        otchetRepository.deleteById(idd);
        return new ModelAndView("redirect:/book/main");
    }
}
