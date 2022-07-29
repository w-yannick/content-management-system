package com.sg.cms.controller;


import com.sg.cms.entity.Blog;
import com.sg.cms.entity.BlogBody;
import com.sg.cms.repository.BlogBodyRepository;
import com.sg.cms.repository.BlogRepository;
import com.sg.cms.view.CmsView;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ContentController {

    @Autowired
    BlogRepository blogRepository;

    @Autowired
    BlogBodyRepository blogBodyRepository;

    @Autowired
    CmsView view;

    @GetMapping("getBlog")
    public String getBlogPage(Integer id, HttpServletRequest request, Model model){
        Blog blog = blogRepository.getById(id);
        BlogBody blogBody = blogBodyRepository.findById(id).orElse(new BlogBody(""));
        model.addAttribute("blog", blog);
        model.addAttribute("blogBody", blogBody);
        HomeController.assignRole(request, model);
        return view.displayBlogPage();
    }

    @GetMapping("deleteBlog")
    public String deleteBlog(Integer id){
        blogRepository.deleteById(id);
        return "redirect:/";
    }
}