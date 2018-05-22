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
            @RequestParam int litterId,
            @RequestParam String name,
            @RequestParam String gender) {
        Puppy puppy = puppyService.createPuppy(litterId, name, gender);
        return new ModelAndView("").addObject(puppy);
    }

    @GetMapping("/puppy/{id}")
    public ModelAndView getPuppy(@PathVariable int id) {
        Puppy puppy = puppyService.readPuppy(id);
        return new ModelAndView("").addObject(puppy);
    }

    @GetMapping("/puppies")
    public ModelAndView getPuppiesByLitterId(@RequestParam int litterId) {
        List<Puppy> puppies = puppyService.readPuppiesByLitterId(litterId);
        return new ModelAndView("index").addObject(puppies);
    }
}
