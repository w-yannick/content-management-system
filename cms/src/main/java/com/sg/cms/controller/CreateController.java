

package com.sg.cms.controller;

import com.sg.cms.entity.Blog;
import com.sg.cms.entity.BlogBody;
import com.sg.cms.repository.BlogBodyRepository;
import com.sg.cms.repository.BlogRepository;
import com.sg.cms.view.CmsView;
import java.time.LocalDate;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class CreateController {

    @Autowired
    BlogRepository blogRepository;

    @Autowired
    BlogBodyRepository blogBodyRepository;

    @Autowired
    CmsView view;
    
    @RequestMapping(value="/create", method=RequestMethod.GET)
    public String displayCreatePage(Model model) {
        model.addAttribute("activePage", "create");
        return view.displayCreatePage();
    }

    @PostMapping("createBlog")
    public String performCreateBlog(String title, String description,String expiryDate, String content){
        Blog blog = new Blog();
        blog.setTitle(title);
        blog.setDescription(description);
        if(expiryDate != ""){
            blog.setExpiryDate(LocalDate.parse(expiryDate));
        }
        Blog test = blogRepository.save(blog);

        BlogBody blogBody = new BlogBody();
        blogBody.setId(blog.getId());
        blogBody.setBody(content);
        blogBodyRepository.save(blogBody);
        return "redirect:/getBlog?id="+test.getId();
    }
    
     @RequestMapping(value="/pendingApproval", method=RequestMethod.GET)
    public String displayApprovalPage(Model model) {
        List<Blog> blogs = blogRepository.findByApproved(false);
        model.addAttribute("activePage", "pendingApproval");
        model.addAttribute("blogs", blogs);
        return view.displayPendingApprovalPage();
    }
}