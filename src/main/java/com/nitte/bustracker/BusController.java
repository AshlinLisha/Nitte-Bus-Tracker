package com.nitte.bustracker;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class BusController {
    private final BusService service;
    public BusController(BusService service) { this.service = service; }

    @GetMapping("/")
    public String home(
            @RequestParam(required=false, defaultValue="") String name,
            @RequestParam(required=false, defaultValue="") String destination,
            @RequestParam(required=false, defaultValue="") String type,
            @RequestParam(required=false, defaultValue="") String from,
            @RequestParam(required=false, defaultValue="") String to,
            Model model) {
        model.addAttribute("buses", service.search(name, destination, type, from, to));
        model.addAttribute("counts", service.countsByName());
        model.addAttribute("destinations", service.destinations());
        model.addAttribute("types", service.types());
        model.addAttribute("totalCount", service.totalCount());
        model.addAttribute("name", name);
        model.addAttribute("destination", destination);
        model.addAttribute("type", type);
        model.addAttribute("from", from);
        model.addAttribute("to", to);
        return "index";
    }
}
