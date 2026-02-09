package be.thomasmore.party.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping({"/","/home"})
    public String home(Model model) {
        int myCalculatedValue = 34 * 62;
        model.addAttribute("myCalculatedValue", myCalculatedValue);
        return "home";
    }

    @GetMapping("/about")
    public String about(Model model) {
        String myName = "Gabriele Pjetra";
        String myAddress = "VIIde-Olympiadelaan 17";
        String myCity = "Antwerp";
        String myCountry = "Belgium";
        model.addAttribute("myName", myName);
        model.addAttribute("myAddress", myAddress);
        model.addAttribute("myCity", myCity);
        model.addAttribute("myCountry", myCountry);
        return "about";
    }
}
