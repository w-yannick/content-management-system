/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.sg.cms.controller;

import com.sg.cms.entity.Blog;
import com.sg.cms.entity.BlogBody;
import com.sg.cms.repository.BlogBodyRepository;
import com.sg.cms.repository.BlogRepository;
import com.sg.cms.view.CmsView;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HomeController {
    
    @Autowired
    BlogRepository blogRepository;

    @Autowired
    BlogBodyRepository blogBodyRepository;

    @Autowired
    CmsView view;

    @RequestMapping(value={"/", "/home", "/index"}, method=RequestMethod.GET)
    public String displayHomePage(Model model) {

        List<Blog> blogs = blogRepository.findAll();
        model.addAttribute("activePage", "home");
        model.addAttribute("blogs", blogs);
        return view.displayIndexPage();
    }

    @GetMapping("getBlog")
    public String getBlog(Integer id, Model model){
        Blog blog = blogRepository.getById(id);
        BlogBody blogBody = blogBodyRepository.getById(id);
        model.addAttribute("blog", blog);
        model.addAttribute("blogBody", blogBody);
        return "blog";
    }

}