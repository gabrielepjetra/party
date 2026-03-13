package be.thomasmore.party.controllers;

import be.thomasmore.party.model.Party;
import be.thomasmore.party.repositories.PartyRepository;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PartyController {
    private Logger logger = LoggerFactory.getLogger(PartyController.class);
    private final PartyRepository partyRepository;

    public PartyController(PartyRepository partyRepository) {
        this.partyRepository = partyRepository;
    }

    @GetMapping("/partylist")
    public String partylist(Model model) {
        logger.info("partyList");
        final Iterable<Party> allParties = partyRepository.findAll();
        model.addAttribute("parties", allParties);
        return "partylist";
    }
}
