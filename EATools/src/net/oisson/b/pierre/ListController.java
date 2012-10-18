package net.oisson.b.pierre;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
 
/**
 * Handles requests for the data objects' lists.
 */
@Controller
public class ListController {
 
    @RequestMapping(value = "/listApps")
    public String listApps() {
        System.out.println("ListController: Passing through...");
        return "WEB-INF/views/listApps.jsp";
    }
    
    @RequestMapping(value = "/listEntities")
    public String listEntities() {
        System.out.println("ListController: Passing through...");
        return "WEB-INF/views/listEntities.jsp";
    }
    
    @RequestMapping(value = "/listIntegrations")
    public String listIntegrations() {
        System.out.println("ListController: Passing through...");
        return "WEB-INF/views/listIntegrations.jsp";
    }
}

