package ch.bbw.PasswordSafe.control;

import ch.bbw.PasswordSafe.model.Credentials;
import ch.bbw.PasswordSafe.model.Entry;
import ch.bbw.PasswordSafe.service.AuthenticationService;
import ch.bbw.PasswordSafe.service.PasswordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.Option;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

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
        model.addAttribute("allEntries", entries);

        return "passwordmanager";
    }

    @GetMapping("/newEntry")
    public String getNewEntryForm(Model model) {
        model.addAttribute("newEntry", new Entry());
        return "addentry";

    }

    @PostMapping("/submitEntry")
    public String addEntry(@Valid Entry newEntry, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
        	bindingResult.getAllErrors();
     
            System.out.println(bindingResult);
            model.addAttribute("newEntry", newEntry);
            return "addentry";
        }

        pwService.addEntry(newEntry);
        return "redirect:/safe";
    }

    @GetMapping("/updateEntry/{id}")
    public String showUpdateForm(@PathVariable("id") String id, Model model) {
        Optional<Entry> entry = this.pwService.getEntryById(id);
        if (entry.isPresent()) {
            model.addAttribute("updateEntry", entry.get());
            return "updateEntry";
        }
        //unexpected error
        return "redirect:/safe";
    }	

    @PostMapping("/updateEntry")
    public String updateEntry(@Valid Entry updateEntry) {
        this.pwService.updateEntry(updateEntry);
        return "redirect:/safe";

    }

    @GetMapping("/removeEntry/{id}")
    public String deleteEntry(@PathVariable("id") String id) {
        this.pwService.removeEntry(id);
        return "redirect:/safe";
    }

}
