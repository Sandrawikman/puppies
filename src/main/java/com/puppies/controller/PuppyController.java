package com.puppies.controller;

import com.puppies.domain.Puppy;
import com.puppies.service.PuppyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class PuppyController {

    private PuppyService puppyService;

    @Autowired
    public PuppyController(PuppyService puppyService) {
        this.puppyService = puppyService;
    }

    @PostMapping("/puppy")
    public ModelAndView createPuppy(
            @RequestParam String puppyName,
            @RequestParam String gender,
            @RequestParam int litterId,
            HttpSession session) {
        Puppy puppy = puppyService.createPuppy(litterId, puppyName, gender);
        return new ModelAndView("redirect:puppies").addObject(puppy);
    }

    @GetMapping("/puppy/{id}")
    public ModelAndView getPuppy(
            @PathVariable int id,
            HttpSession session) {
        Puppy puppy = puppyService.readPuppy(id);
        return new ModelAndView("").addObject(puppy);
    }

    @GetMapping("/puppies")
    public ModelAndView getPuppiesByLitterId(
//            @RequestParam int litterId,
            HttpSession session) {

        List<Puppy> puppies = puppyService.readPuppiesByLitterId((int) session.getAttribute("litterId"));
        return new ModelAndView("puppies").addObject("puppyList", puppies);
    }
}
