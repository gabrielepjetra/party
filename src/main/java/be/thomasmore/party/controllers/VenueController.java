package be.thomasmore.party.controllers;

import be.thomasmore.party.model.Venue;
import be.thomasmore.party.repositories.VenueRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Optional;

@Controller
public class VenueController {
    private final VenueRepository venueRepository;

    public VenueController(VenueRepository venueRepository) {
        this.venueRepository = venueRepository;
    }

    @GetMapping("/venuedetails")
    public String venueDetails(Model model) {
        Optional<Venue> venueFromDb = venueRepository.findById(1);
        if (venueFromDb.isPresent()) {
            model.addAttribute("venue", venueFromDb.get());
        }

        return "venuedetails";
    }
}
