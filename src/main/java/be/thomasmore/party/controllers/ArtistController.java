package be.thomasmore.party.controllers;

import be.thomasmore.party.model.Artist;
import be.thomasmore.party.repositories.ArtistRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
public class ArtistController {
    private Logger logger = LoggerFactory.getLogger(ArtistController.class);
    private final ArtistRepository artistRepository;

    public ArtistController(ArtistRepository artistRepository) {
        this.artistRepository = artistRepository;
    }

    @GetMapping("/artistlist")
    public String artistList(Model model,
                             @RequestParam(required = false) String keyword) {
        logger.info(String.format("artistList -- keyword=%s", keyword));
        final Iterable<Artist> allArtists = artistRepository.findByFilter(keyword);

        model.addAttribute("artists", allArtists);

        return "artistlist";
    }

    @GetMapping({"/artistdetails", "/artistdetails/{id}"})
    public String artistDetails(Model model, @PathVariable(required = false) Integer id) {
        if (id == null) return "artistdetails";

        Optional<Artist> artistFromDb = artistRepository.findById(id);

        if (artistFromDb.isPresent()) {
            int maxId = (int) artistRepository.count();

            int prevId = (id == 1) ? maxId : id - 1;
            int nextId = (id == maxId) ? 1 : id + 1;

            model.addAttribute("artist", artistFromDb.get());
            model.addAttribute("previd", prevId);
            model.addAttribute("nextid", nextId);
        }

        return "artistdetails";
    }
}
