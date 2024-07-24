package org.example.security.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PagesController {

    @RequestMapping("/")
    public String home() {
        return "index";
    }

    @RequestMapping("/pages/all")
    public String all() {
        return "all";
    }

    @RequestMapping("/pages/admins")
    public String admins() {
        return "admins";
    }

    @RequestMapping("/pages/moderators")
    public String moderators() {
        return "moderators";
    }
}
