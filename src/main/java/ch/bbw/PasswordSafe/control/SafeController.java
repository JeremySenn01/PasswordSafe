package ch.bbw.PasswordSafe.control;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import ch.bbw.PasswordSafe.model.Entry;
import ch.bbw.PasswordSafe.service.PasswordService;

@Controller
public class SafeController {

    @Autowired
    private PasswordService pwService;

    @GetMapping("/")
    public String getEntries(Model model) {
    	model.addAttribute("allEntries", pwService.getAllEntries());
		model.addAttribute("newentry", new Entry());
		return "pwService";

    }

    @PostMapping("/addEntry")
    public String addEntry(@ModelAttribute Entry newEntry, Model model) {
    	pwService.addEntry(newEntry);
		model.addAttribute("allEntries", pwService.getAllEntries());
        return "redirect:/addentry";
    }

    @PutMapping("/")
    public String updateEntry(@ModelAttribute Entry updateEntry) {
        return null;
    }
    
}
