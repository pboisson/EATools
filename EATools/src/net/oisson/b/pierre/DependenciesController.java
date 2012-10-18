package net.oisson.b.pierre;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
 
/**
 * Handles requests for the dependencies between apps.
 */
@Controller
public class DependenciesController {
 
    @RequestMapping(value = "/listDependencies")
    public String listApps() {
        System.out.println("DependenciesController: Passing through...");
        return "WEB-INF/views/listApps.jsp";
    }

}

