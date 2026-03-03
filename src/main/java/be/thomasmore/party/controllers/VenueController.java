package be.thomasmore.party.controllers;

import be.thomasmore.party.model.Venue;
import be.thomasmore.party.repositories.VenueRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
public class VenueController {
    private Logger logger = LoggerFactory.getLogger(VenueController.class);
    private final VenueRepository venueRepository;

    public VenueController(VenueRepository venueRepository) {
        this.venueRepository = venueRepository;
    }

    @GetMapping("/venuelist")
    public String venueList(Model model, @RequestParam(required = false) Integer minCapacity) {
        logger.info(String.format("venueList -- min=%d", minCapacity));
        final Iterable<Venue> allVenues = venueRepository.findBYFilter(minCapacity);
        long nrOfVenues = venueRepository.count();
        model.addAttribute("venues", allVenues);
        return "venuelist";
    }

    @GetMapping({"/venuedetails/{id}","/venuedetails"})
    public String venueDetails(Model model, @PathVariable(required = false) Integer id) {
        if(id == null) {
            return "venuedetails";
        }

        Optional<Venue> venueFromDb = venueRepository.findById(id);
        if (venueFromDb.isPresent()) {
            int maxId = (int) venueRepository.count();

            int prevId = (id == 1) ? maxId : id - 1;
            int nextId = (id == maxId) ? 1 : id + 1;

            model.addAttribute("venue", venueFromDb.get());
            model.addAttribute("prev", prevId);
            model.addAttribute("next", nextId);
        }

        return "venuedetails";
    }
}
