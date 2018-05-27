package com.puppies.controller;
import com.puppies.domain.Litter;
import com.puppies.service.LitterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
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
            HttpSession session,
            @RequestParam String litterName,
            @RequestParam Date dateOfBirth) {
        int litterId = litterService.createLitter((int) session.getAttribute("userId"), litterName, dateOfBirth);
        session.setAttribute("litterId", litterId);
        return new ModelAndView("redirect:loggedin");       // TODO: SKA ÄNDRAS till en redirect:litter eller nått
    }

    @GetMapping("/loggedin")
    public ModelAndView newLitterList(
            HttpSession session) {
        List<Litter> litterList = litterService.getLitterList((int) session.getAttribute("userId"));
        return new ModelAndView("loggedin").addObject("litterList", litterList);     // TODO: SKA ÄNDRAS till en redirect:litter eller nått
    }


////
//    @GetMapping("/litterlist")
//    public ModelAndView getLitterByUserId(
//            HttpSession session,
//            @RequestParam int userId) {
//        List<Litter> litterList = litterService.readLitterByUserId(userId);
//        return new ModelAndView("index").addObject(litterList);
//    }
//
//
//    @GetMapping("/event")
//    public ModelAndView newEventList(HttpSession session) {
//        List<Event> eventlist = repository.getEventList((int) session.getAttribute("userId"));
//        return new ModelAndView("event").addObject("eventlist", eventlist);
//    }


//    @PostMapping("/event")
//    public ModelAndView createEvent(
//            HttpSession session,
//            @RequestParam String name,
//            @RequestParam java.sql.Date date) {
//        repository.addEvent(name, date, (int) session.getAttribute("userId"));
//        return new ModelAndView("redirect:event");                //Ska redirect till inloggat läge
//
//

//    @GetMapping("/liter/{id}")
//    public ModelAndView getLitter(
//            HttpSession session,
//            @PathVariable int id) {
//        Litter litter = litterService.readLitter(id);
//        return new ModelAndView("").addObject(litter);
//    }


}