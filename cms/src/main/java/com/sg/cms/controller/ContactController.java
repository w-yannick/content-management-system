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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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
    public String displayContactPage(Model model){
        model.addAttribute("activePage", "contact");
        return view.displayContactPage();
    }

    
    @PostMapping("/contact")
    public String saveMessage(Contact contact){
        contactRepository.save(contact);
        return view.displayConfirmContactPage();
    }
}