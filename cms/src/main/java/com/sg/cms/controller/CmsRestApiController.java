/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.cms.controller;


import com.sg.cms.entity.Blog;
import com.sg.cms.repository.BlogRepository;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.validation.ConstraintViolation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import java.util.ArrayList;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@RestController
@RequestMapping("/api/")
public class CmsRestApiController {
    
    @Autowired
    BlogRepository blogRepository;
    
    @GetMapping("/blogs")
    public List<Blog> getBlogs() {
        return blogRepository.findAll();
    }
    
    @GetMapping("/searchBlogs")
    public List<Blog> searchBlogs(String quickSearch, String dateMin, String dateMax ) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        //  LocalDate localDate = LocalDate.parse(date, formatter);
        quickSearch = (quickSearch != null) ? '%'+quickSearch+'%' : "%";
        LocalDateTime localDateMin = (dateMin != null) ? LocalDate.parse(dateMin, formatter).atStartOfDay() : blogRepository.findDateMin();
        LocalDateTime localDateMax = (dateMax != null) ? LocalDate.parse(dateMax, formatter).plusDays(1).atStartOfDay() : blogRepository.findDateMax();
       
        return blogRepository.findBySearch( quickSearch,  quickSearch,  quickSearch, localDateMin, localDateMax);
        
    }
   
}
