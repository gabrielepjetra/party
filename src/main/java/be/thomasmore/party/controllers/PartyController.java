package be.thomasmore.party.controllers;

import be.thomasmore.party.model.Party;
import be.thomasmore.party.repositories.PartyRepository;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

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

    @GetMapping({"/partydetails/{id}", "/partydetails"})
    public String partyDetails(Model model, @PathVariable(required = false) Integer id) {
        logger.info(String.format("partyDetails -- id=%d", id));
        if (id == null) return "partydetails";

        Optional<Party> partyFromDb = partyRepository.findById(id);
        if (partyFromDb.isPresent()) {
            int maxId = (int) partyRepository.count();
            int prevId = (id == 1) ? maxId : id - 1;
            int nextId = (id == maxId) ? 1 : id + 1;

            model.addAttribute("party", partyFromDb.get());
            model.addAttribute("prev", prevId);
            model.addAttribute("next", nextId);
        }
        return "partydetails";
    }
}
