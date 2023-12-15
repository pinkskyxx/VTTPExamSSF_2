package vttp.ssf.assessment.eventmanagement.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.validation.Valid;
import vttp.ssf.assessment.eventmanagement.models.RegisterName;

@Controller
@RequestMapping("/register")
public class RegistrationController {
    

    // TODO: Task 6
    //post
    //send the register and sent for registeration
    @PostMapping("/customer")
    public String updateEmployeeRecord(@Valid @ModelAttribute("invEvent") RegisterName emp, BindingResult result, Model model) {

        if (result.hasErrors()) {
            return "eventRegister";
        }

        // empRepo.updateEmployee(emp);
        
        return "successRegisteration";
    }


    // TODO: Task 7
    // post
    @PostMapping("/customercheck")
    public String CheckEmployeeRecord(@Valid @ModelAttribute("invEvent") RegisterName emp, BindingResult result, Model model) {

        if (result.hasErrors()) {
            return "errorRegisteration";
        }

        // empRepo.updateEmployee(emp);
        return "successRegisteration";
        // return "redirect:/events/listing";
    }
    //check success
    //check fail
}
