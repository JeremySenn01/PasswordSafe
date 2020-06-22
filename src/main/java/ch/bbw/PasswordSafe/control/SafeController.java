package ch.bbw.PasswordSafe.control;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import ch.bbw.PasswordSafe.model.Entry;
import ch.bbw.PasswordSafe.service.AuthenticationService;
import ch.bbw.PasswordSafe.service.PasswordService;

@Controller
public class SafeController {

    @Autowired
    private PasswordService pwService;
    @Autowired
    private AuthenticationService authService;

    @GetMapping("/safe")
    public String getSafe(Model model) {
    	if (!authService.hasCurrentUser()) {
    		return "redirect:/login";
    	}
    	List<Entry> entries = pwService.getAllEntries();
    	System.out.println("entries.length = " + entries.size());
    	model.addAttribute("allEntries", entries);
    	
    	return "passwordmanager";
    }
    
    @GetMapping("/newEntry")
    public String getNewEntryForm(Model model) {
		model.addAttribute("newEntry", new Entry());
		return "addentry";

    }

    @PostMapping("/submitEntry")
    public String addEntry(@ModelAttribute Entry newEntry, Model model) {
    	System.out.println("new Entry: " + newEntry);
    	pwService.addEntry(newEntry);
        return "redirect:/safe";
    }

    @PutMapping("/")
    public String updateEntry(@ModelAttribute Entry updateEntry) {
        return null;
    }
    
    @DeleteMapping("/")
    public String deleteEntry(@ModelAttribute Entry deleteEntry) {
    	return null;
    }
    
}
