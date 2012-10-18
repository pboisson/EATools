package net.oisson.b.pierre.controllers;

import java.util.logging.Logger;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
 
/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final Logger log = Logger.getLogger(HomeController.class.getName());
 
    @RequestMapping(value = "/home")
    public String home() {
    	log.warning("HomeController: Passing through...");
        return "home";
    }
}

