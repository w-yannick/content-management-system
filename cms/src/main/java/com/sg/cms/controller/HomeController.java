/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.sg.cms.controller;

import com.sg.cms.entity.Blog;
import com.sg.cms.repository.BlogRepository;
import com.sg.cms.view.CmsView;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HomeController {
    
        @Autowired
    BlogRepository blogRepository;
    
    @Autowired
    CmsView view;
    

    @RequestMapping(value={"/", "/home", "/index"}, method=RequestMethod.GET)
    public String displayHomePage(Model model) {

        List<Blog> blogs = blogRepository.findAll();
        model.addAttribute("blogs", blogs);
        return view.displayIndexPage();
    }

}