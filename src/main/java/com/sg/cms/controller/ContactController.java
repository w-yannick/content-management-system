/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.sg.cms.controller;

import com.sg.cms.entity.Contact;
import com.sg.cms.repository.BlogBodyRepository;
import com.sg.cms.repository.BlogRepository;
import com.sg.cms.repository.ContactRepository;
import com.sg.cms.view.CmsView;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ContactController {
    
    @Autowired
    BlogRepository blogRepository;

    @Autowired
    BlogBodyRepository blogBodyRepository;

    @Autowired
    ContactRepository contactRepository;

    @Autowired
    CmsView view;

    @GetMapping("/contact")
    public String displayContactPage(HttpServletRequest request, Model model){
        model.addAttribute("activePage", "contact");
        HomeController.assignRole(request, model);
        return view.displayContactPage();
    }

    
    @PostMapping("/contact")
    public String saveMessage(Contact contact, HttpServletRequest request, Model model){
        contactRepository.save(contact);
        HomeController.assignRole(request, model);
        return view.displayConfirmContactPage();
    }
}