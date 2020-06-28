package ch.bbw.PasswordSafe.control;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import ch.bbw.PasswordSafe.model.Credentials;
import ch.bbw.PasswordSafe.service.AuthenticationService;
import ch.bbw.PasswordSafe.service.PasswordService;

import javax.validation.Valid;

@Controller
public class LoginController {

	@Autowired
	private PasswordService pwService;
	@Autowired
	private AuthenticationService authService;

	@GetMapping("/login")
	public String showLogin(Model model) {
		model.addAttribute("credentials", new Credentials());
		model.addAttribute("loginError", false);

		return "login.html";
	}

	@PostMapping("/login")
    public String signIn(@Valid Credentials credentials, BindingResult bindingResult) {
        boolean foundUser = authService.signInUser(credentials);

        if (bindingResult.hasErrors()) {
        	return "login";
		}

        //Login worked
        if (foundUser) {
            return "redirect:/safe";
        }
        //login didn't work
        return "redirect:/failedLogin";
    }

	@GetMapping("/failedLogin")
	public String loginError(Model model) {
		model.addAttribute("loginError", true);
		model.addAttribute("credentials", new Credentials());
		return "login.html";
	}
	
	@GetMapping("/logout")
	public String logout() {
		this.authService.logout();
		return "redirect:/login";
	}

}
