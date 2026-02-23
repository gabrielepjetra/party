package be.thomasmore.party.controllers;

import be.thomasmore.party.model.Artist;
import be.thomasmore.party.repositories.ArtistRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@Controller
public class ArtistController {
    private final ArtistRepository artistRepository;

    public ArtistController(ArtistRepository artistRepository) {
        this.artistRepository = artistRepository;
    }

    @GetMapping("/artistlist")
    public String artistList(Model model) {
        Iterable<Artist> allArtists = artistRepository.findAll();

        model.addAttribute("artists", allArtists);

        return "artistlist";
    }

    @GetMapping({"/artistdetails", "/artistdetails/{id}"})
    public String artistDetails(Model model, @PathVariable(required = false) Integer id) {
        if (id == null) return "artistdetails";

        Optional<Artist> artistFromDb = artistRepository.findById(id);

        if (artistFromDb.isPresent()) {
            model.addAttribute("artist", artistFromDb.get());
        }

        return "artistdetails";
    }
}
