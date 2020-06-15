package ch.bbw.PasswordSafe.control;

import ch.bbw.PasswordSafe.service.PasswordService;
import ch.bbw.PasswordSafe.model.Credentials;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {

    @Autowired
    private PasswordService pwService;
    @Autowired
    Credentials credentials;
    @GetMapping("/login")
    public String showLogin(Model model) {
    	model.addAttribute("credentials", credentials);
        return "index.html";
    }

    @PostMapping("/login")
    public String signIn(@ModelAttribute Credentials credentials) {
    	
    	
        return "";
    }
    
    @RequestMapping("/index.html")  
    public String loginError(Model model) {  
        model.addAttribute("loginError", true);  
        return "index.html";  
    }  

}
