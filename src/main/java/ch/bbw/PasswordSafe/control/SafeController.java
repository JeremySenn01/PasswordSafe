package ch.bbw.PasswordSafe.control;

import ch.bbw.PasswordSafe.model.Entry;
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
    private PasswordService pwService;

    @GetMapping("/")
    public String getEntries(Model model) {
        return "";
    }

    @PostMapping("/")
    public String addEntry(@ModelAttribute Entry newEntry) {
        return "";
    }

    @PutMapping("/")
    public String updateEntry(@ModelAttribute Entry updateEntry) {
        return null;
    }
}
