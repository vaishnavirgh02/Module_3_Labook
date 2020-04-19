package org.cap.controller;

import org.cap.entities.Trainee;
import org.cap.service.ITraineeService;
import org.cap.util.SessionData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class TraineeController {

    @Autowired
    private ITraineeService service;

    @Autowired
    private SessionData sessionData;

    @GetMapping("/")
    public ModelAndView home(){
       return new ModelAndView("homepage");
    }


    @GetMapping("/register")
    //@RequestMapping(path = "/register",method = RequestMethod.GET)
    public ModelAndView register() {
        ModelAndView mv = new ModelAndView("registerTrainee");
        return mv;
    }

    @GetMapping("/processregister")
    public ModelAndView processRegister(@RequestParam("traineeid") int id,
                                        @RequestParam("traineename") String traineename,
                                        @RequestParam("password") String password) {
        Trainee trainee = new Trainee();
        trainee.setTraineeId(id);
        trainee.setTraineeName(traineename);
        trainee.setPassword(password);
        service.save(trainee);
        ModelAndView mv = new ModelAndView("details", "trainee", trainee);
        return mv;
    }

    @GetMapping("/details")
    public ModelAndView traineeDetails() {
        int id = sessionData.getTraineeID();
        if (id == -1) {
            return new ModelAndView("login");
        }
        Trainee trainee = service.findTraineeById(id);
        ModelAndView mv = new ModelAndView("details", "trainee", trainee);
        return mv;
    }

    @GetMapping("/login")
    public ModelAndView loginPage() {
        return new ModelAndView("login");
    }

    @GetMapping("/processlogin")
    public ModelAndView processLogin(@RequestParam("traineeid") int id, @RequestParam("password") String password) {
        boolean correct = service.credentialsCorrect(id, password);
        if (!correct) {
            return new ModelAndView("login");
        }
        sessionData.setTraineeID(id);
        Trainee trainee = service.findTraineeById(id);
        ModelAndView mv = new ModelAndView("details", "trainee", trainee);
        return mv;
    }

   



}
