package be.thomasmore.party.controllers;

import be.thomasmore.party.model.Animal;
import be.thomasmore.party.repositories.AnimalRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@Controller
public class AnimalController {
    private Logger logger = LoggerFactory.getLogger(AnimalController.class);
    private final AnimalRepository animalRepository;

    public AnimalController(AnimalRepository animalRepository) {
        this.animalRepository = animalRepository;
    }

    @GetMapping({"/animaldetails/{id}", "/animaldetails"})
    public String animalDetails(Model model, @PathVariable(required = false) Integer id) {
        logger.info(String.format("AnimalDetails -- id=%d", id));
        if (id == null) return "animaldetails";

        Optional<Animal> animalFromDb = animalRepository.findById(id);
        if (animalFromDb.isPresent()) {
            int maxId = (int) animalRepository.count();
            int prevId = (id == 1) ? maxId : id - 1;
            int nextId = (id == maxId) ? 1 : id + 1;

            model.addAttribute("animal", animalFromDb.get());
            model.addAttribute("prev",  prevId);
            model.addAttribute("next",  nextId);
        }
        return "animaldetails";
    }
}
