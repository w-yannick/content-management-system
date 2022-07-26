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
import org.springframework.web.bind.annotation.GetMapping;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.time.LocalDate;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
public class CmsController {

    @Autowired
    BlogRepository blogRepository;
    
    @Autowired
    CmsView view;
    

     @RequestMapping(value = {"/","/index","/home","/home/index"}, method = RequestMethod.GET)
    public String displayIndexPage(Model model) {

        List<Blog> blogs = blogRepository.findAll();
        model.addAttribute("blogs", blogs);
        return view.displayIndexPage();
    }
}
