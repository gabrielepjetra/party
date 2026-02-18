package be.thomasmore.party.controllers;

import be.thomasmore.party.model.Client;
import be.thomasmore.party.repositories.ClientRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

@Controller
public class ClientController {
    private final ClientRepository clientRepository;

    public ClientController(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @GetMapping("/clientgreeting")
    public String clientGreeting(Model model) {
        Optional<Client> clientFromDb = clientRepository.findById(1);
        if (clientFromDb.isPresent()) {
            Client client = clientFromDb.get();

            String greeting = calculateGreeting(client);

            model.addAttribute("greeting", greeting);
        }
        return "clientgreeting";
    }

    private String calculateGreeting(Client client) {
        LocalDateTime now = LocalDateTime.now();
        int hour = now.getHour();

        String timeGreeting;
        if (hour >= 6 && hour < 12) {
            timeGreeting = "Goedemorgen";
        } else if (hour >= 12 && hour < 17) {
            timeGreeting = "Goedemiddag";
        } else if (hour >= 17 && hour < 22) {
            timeGreeting = "Goedenavond";
        } else {
            timeGreeting = "Goedennacht";
        }

        String namePart = "";
        int orders = client.getNrOfOrders();

        if (orders == 0) {
            namePart = client.getName() + ", en welkom!";
        } else if (orders < 10) {
            namePart = client.getName();
        } else if (orders >= 80) {
            namePart = "allerliefste " + client.getName() + ", jij bent een topper!";
        } else if (orders >= 50) {
            namePart = "allerliefste " + client.getName();
        } else {
            namePart = "beste " + client.getName();
        }

        return timeGreeting + " " + namePart;
    }

    @GetMapping("/clientdetails")
    public String clientDetails(Model model) {
        Optional<Client> clientFromDb = clientRepository.findById(1);
        if (clientFromDb.isPresent()) {
            Client client = clientFromDb.get();

            double discount = calculateDiscount(client);

            model.addAttribute("client", client);
            model.addAttribute("discount", discount);
        }

        return "clientdetails";
    }

    private double calculateDiscount(Client client) {
        if (client.getTotalAmount() < 50) {
            return 0.0;
        } else {
            return client.getTotalAmount() * 0.005;
        }
    }

    @GetMapping("/clienthome")
    public String clientHome() {
        return "clienthome";
    }
}
