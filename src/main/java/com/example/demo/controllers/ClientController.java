package com.example.demo.controllers;


import com.example.demo.models.Client;
import com.example.demo.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping(path = "/client")
public class ClientController {
    @Autowired
    private ClientRepository clientRepository ;
    private long a;

    @RequestMapping("/add")
    public String showForm(Model model){
        model.addAttribute("publisher",new Client());
        return "client_add_form";
    }

    @RequestMapping("/addd")
    public String showFormm(Model model) {
        model.addAttribute("client", new Client());
        return "client_update";
    }
    @PostMapping("/add")
    public String addClient(@ModelAttribute Client client){
        clientRepository.save(client) ;
        return "redirect:/pub/all" ;
    }

    @GetMapping("/all2")
    public @ResponseBody
    Iterable<Client> allClient(){
        return clientRepository.findAll() ;
    }


    @GetMapping("/all")
    public String allClient2(Model model){
        List<Client> client = (List<Client>) clientRepository.findAll();
        model.addAttribute("clients", client) ;
        return "client" ;
    }

    @PostMapping("/adds")
    public String addsClient(@ModelAttribute Client client){
        Client client1 = new Client();
        client1.setId(a);
        client1.setName(client.getName());
        client1.setEmail(client.getEmail());
        client1.setPhone(client.getPhone());
        client1.setAddress(client.getAddress());
        clientRepository.save(client1) ;
        return "redirect:/pub/all" ;
    }
    @RequestMapping(value = "/update",method = RequestMethod.GET)
    public ModelAndView updateClients(Model model, @RequestParam("id") long id){
        a=id;
        Optional<Client> client= (Optional <Client> ) clientRepository.findById(id);
        model.addAttribute("clientt",client);
        return new ModelAndView("client_update");
    }



    @RequestMapping(value = "/deletePublisher", method = RequestMethod.GET)
    public ModelAndView deleteContact (@RequestParam("id")long idd){
        clientRepository.deleteById(idd);
        return new ModelAndView("redirect:/pub/all");
    }
}
