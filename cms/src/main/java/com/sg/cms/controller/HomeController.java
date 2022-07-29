/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.sg.cms.controller;

import com.sg.cms.entity.Blog;
import com.sg.cms.entity.BlogBody;
import com.sg.cms.entity.Contact;
import com.sg.cms.repository.BlogBodyRepository;
import com.sg.cms.repository.BlogRepository;
import com.sg.cms.repository.ContactRepository;
import com.sg.cms.view.CmsView;
import java.time.LocalDate;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HomeController {
    
    @Autowired
    BlogRepository blogRepository;

    @Autowired
    BlogBodyRepository blogBodyRepository;

    @Autowired
    ContactRepository contactRepository;

    @Autowired
    CmsView view;

    @RequestMapping(value={"/", "/home", "/index"}, method=RequestMethod.GET)
    public String displayHomePage(Model model, HttpServletRequest request) {
        
        List<Blog> blogs = blogRepository.findNonExpired(LocalDate.now(), true);
        
        assignRole(request, model);
        model.addAttribute("activePage", "home");
        model.addAttribute("blogs", blogs);

        return view.displayIndexPage();
    }
    
    
    public static void assignRole(HttpServletRequest request, Model model ){
       String role;
        if (request.isUserInRole("ADMIN")) {
            role = "ADMIN";
        }
        else if (request.isUserInRole("MANAGER")){
            role = "MANAGER";
        }
        else{
            role = "USER";
        }
        
        model.addAttribute("role", role);
   }
}