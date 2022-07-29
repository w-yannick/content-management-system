

package com.sg.cms.controller;

import com.sg.cms.entity.Blog;
import com.sg.cms.entity.BlogBody;
import com.sg.cms.entity.Hashtag;
import com.sg.cms.repository.BlogBodyRepository;
import com.sg.cms.repository.BlogRepository;
import com.sg.cms.repository.HashtagRepository;
import com.sg.cms.view.CmsView;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.servlet.http.HttpServletRequest;

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
    HashtagRepository hashtagRepository;

    @Autowired
    BlogBodyRepository blogBodyRepository;

    @Autowired
    CmsView view;
    
    @RequestMapping(value="/create", method=RequestMethod.GET)
    public String displayCreatePage(HttpServletRequest request, Model model) {
        model.addAttribute("activePage", "create");
        HomeController.assignRole(request, model);
        return view.displayCreatePage();
    }

    @PostMapping("createBlog")
    public String performCreateBlog(String title, String description,String expiryDate, String hashTags, String content){
        Blog blog = new Blog();
        blog.setTitle(title);
        blog.setDescription(description);
        if(expiryDate != ""){
            
            blog.setExpiryDate(LocalDate.parse(expiryDate));
        }
        //Parse hashtags
        List<String> parsedHashTags = parseHashtags(hashTags);
        List<Hashtag> hashtagList = new ArrayList<>();
        
        for(String ht : parsedHashTags){
            Hashtag hashtag = new Hashtag(); 
            hashtag.setName(ht);
            Hashtag fromDB = hashtagRepository.findByName(ht);
            if(fromDB == null){
                fromDB = hashtagRepository.save(hashtag);
            }
            hashtagList.add(fromDB);
        }
        blog.setHashtags(hashtagList);
        
        Blog test = blogRepository.save(blog);


        BlogBody blogBody = new BlogBody();
        blogBody.setId(blog.getId());
        blogBody.setBody(content);
        blogBodyRepository.save(blogBody);
        return "redirect:/getBlog?id="+test.getId();
    }

    public List<String> parseHashtags(String strHashTags){
        List<String> hashTags = new ArrayList<>();

        Matcher matcher = Pattern.compile("(#[^#\\s]*)")
                .matcher(strHashTags);
        while (matcher.find()) {
            hashTags.add(matcher.group());
        }
        return hashTags;
    }
    
    @RequestMapping(value="/pendingApproval", method=RequestMethod.GET)
    public String displayApprovalPage(HttpServletRequest request, Model model) {
        List<Blog> nonApprovedBlogs = blogRepository.findByApproved(false);
        List<Blog> ApprovedBlogs = blogRepository.findByApproved(true);
        model.addAttribute("activePage", "pendingApproval");
        model.addAttribute("nonApprovedBlogs", nonApprovedBlogs);
        model.addAttribute("approvedBlogs", ApprovedBlogs);
        HomeController.assignRole(request, model);
        return view.displayPendingApprovalPage();
    }
    
    @RequestMapping(value="/adminEditBlog", method=RequestMethod.GET)
    public String displayAdminEditPage(Integer id, HttpServletRequest request, Model model) {
        Blog blog = blogRepository.getById(id);
        BlogBody blogBody = blogBodyRepository.findById(id).orElse(new BlogBody(""));
        String hashtags = "";
        for (Hashtag ht: blog.getHashtags()){
            hashtags += ht.getName() + ' ';
        }
        model.addAttribute("blog", blog);
        model.addAttribute("hashtags", hashtags);
        model.addAttribute("blogBody", blogBody);
        HomeController.assignRole(request, model);
        return "adminEditBlog";
    }
    
    @RequestMapping(value="/adminEditBlog", method=RequestMethod.POST)
    public String performAdminEditPage(Integer id, String title, String description,String expiryDate, String hashTags, String content, boolean approved ,HttpServletRequest request, Model model) {
        Blog blog = blogRepository.getById(id);
        BlogBody blogBody = blogBodyRepository.findById(id).orElse(new BlogBody(""));

        List<String> parsedHashTags = parseHashtags(hashTags);
        List<Hashtag> hashtagList = new ArrayList<>();
        
        for(String ht : parsedHashTags){
            Hashtag hashtag = new Hashtag(); 
            hashtag.setName(ht);
            Hashtag fromDB = hashtagRepository.findByName(ht);
            if(fromDB == null){
                fromDB = hashtagRepository.save(hashtag);
            }
            hashtagList.add(fromDB);
        }
        
        blog.setTitle(title);
        blog.setDescription(description);
        if(expiryDate != ""){
            blog.setExpiryDate(LocalDate.parse(expiryDate));
        }
        else{
            blog.setExpiryDate(null);
        }
        
        blog.setApproved(approved);
        blog.setHashtags(hashtagList);
        blogBody.setBody(content);
        
        blogRepository.save(blog);
        blogBodyRepository.save(blogBody);
        
        
        return "redirect:/pendingApproval";
    }
}