package ch.bbw.PasswordSafe.control;

import ch.bbw.PasswordSafe.dao.Dao;
import ch.bbw.PasswordSafe.model.Entry;
import ch.bbw.PasswordSafe.service.AuthenticationService;
import ch.bbw.PasswordSafe.service.PasswordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

@Controller
public class SafeController {

    @Autowired
    private AuthenticationService pwService;

    @GetMapping("/")
    public String getEntries(Model model) {
    	model.addAttribute("allEntries", pwService.getAllEnries());
		model.addAttribute("newentrie", new Entry());
		return "pwService";

    }

    @PostMapping("addEntry")
    public String addEntry(@ModelAttribute Entry newEntry, Model model) {
    	pwService.addEntrie(newEntry);
		model.addAttribute("allEntries", pwService.getAllEntries());
        return "redirect:/passwordmanager.html";
    }

    @PutMapping("/")
    public String updateEntry(@ModelAttribute Entry updateEntry) {
        return null;
    }
    
}
