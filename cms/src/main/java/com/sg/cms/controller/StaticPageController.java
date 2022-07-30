package com.sg.cms.controller;


import com.sg.cms.entity.StaticPage;
import com.sg.cms.repository.BlogBodyRepository;
import com.sg.cms.repository.BlogRepository;
import com.sg.cms.repository.StaticPageRepository;
import com.sg.cms.view.CmsView;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class StaticPageController {

    @Autowired
    BlogRepository blogRepository;

    @Autowired
    BlogBodyRepository blogBodyRepository;
    @Autowired
    StaticPageRepository staticPageRepository;

    @Autowired
    CmsView view;
    
    @GetMapping("staticPage")
    public String getStaticPage(Integer id, HttpServletRequest request, Model model){
        List<StaticPage> staticPages = staticPageRepository.findAll();
        model.addAttribute("staticPages", staticPages);
        model.addAttribute("activePage", "static");
        HomeController.assignRole(request, model);
        return view.displayStaticPage();
    }
    
    @GetMapping("createStaticPage")
    public String createStaticPage(Integer id, HttpServletRequest request, Model model){
        List<StaticPage> staticPages = staticPageRepository.findAll();
        model.addAttribute("activePage", "static");
        HomeController.assignRole(request, model);
        return view.displayCreateStaticPage();
    }
    
    
    
    
    @PostMapping("createStaticPage")
    public String performCreateStaticPage(String title, String content){
        StaticPage sp = new StaticPage();
        sp.setTitle(title);
        sp.setContent(content);
        staticPageRepository.save(sp);
        return "redirect:/staticPage";
    }
    
    @GetMapping("getStaticPage")
    public String getStaticPageDetails(Integer id, HttpServletRequest request, Model model){
        StaticPage sp = staticPageRepository.getById(id);
        model.addAttribute("activePage", "static");

        model.addAttribute("staticPage", sp);
        HomeController.assignRole(request, model);
        return view.displayStaticPageDetails();
    }
    
    @GetMapping("editStaticPage")
    public String editStaticPage(Integer id, HttpServletRequest request, Model model){
        StaticPage sp = staticPageRepository.getById(id);
        model.addAttribute("activePage", "static");
        model.addAttribute("staticPage", sp);
        HomeController.assignRole(request, model);
        return view.displayEditStaticPage();
    }
    
    @PostMapping("editStaticPage")
    public String performEditStaticPage(Integer id, String title, String content, HttpServletRequest request, Model model){
        StaticPage sp = staticPageRepository.getById(id);
        sp.setTitle(title);
        sp.setContent(content);
        staticPageRepository.save(sp);
        return "redirect:/staticPage";
    }
    @GetMapping("deleteStaticPage")
    public String deleteStaticPage(Integer id){
        staticPageRepository.deleteById(id);
        return "redirect:/staticPage";
    }
}