package be.thomasmore.party.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

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

    @GetMapping("/pay")
    public String pay(Model model) {
        LocalDate today = LocalDate.now();
        LocalDate futureDay = today.plusDays(30);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        model.addAttribute("today", today.format(formatter));
        model.addAttribute("futureDay", futureDay.format(formatter));

        return "pay";
    }
}
