

package com.sg.cms.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class CreateController {
    
    @RequestMapping(value="/create", method=RequestMethod.GET)
    public String displayCreatePage(Model model) {
        model.addAttribute("activePage", "create");
        return "create";
    }
    
}