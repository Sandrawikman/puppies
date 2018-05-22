package com.puppies.controller;

import com.puppies.domain.Litter;
import com.puppies.service.LitterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.sql.Date;
import java.util.List;

@Controller
public class LitterController {

    private LitterService litterService;

    @Autowired
    public LitterController(LitterService litterService) {
        this.litterService = litterService;
    }

    @PostMapping("/litter")
    public ModelAndView createLitter(
            @RequestParam int userId,
            @RequestParam String name,
            @RequestParam Date dateOfBirth) {
        Litter litter = litterService.createLitter(userId, name, dateOfBirth);
        return new ModelAndView("").addObject(litter);
    }

    @GetMapping("/liter/{id}")
    public ModelAndView getLitter(@PathVariable int id) {
        Litter litter = litterService.readLitter(id);
        return new ModelAndView("").addObject(litter);
    }

    @GetMapping("/litters")
    public ModelAndView getLitterByUserId(@RequestParam int userId) {
        List<Litter> litters = litterService.readLitterByUserId(userId);
        return new ModelAndView("index").addObject(litters);
    }



}
