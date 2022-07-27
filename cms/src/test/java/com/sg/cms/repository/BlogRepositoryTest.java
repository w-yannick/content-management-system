/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */



package com.sg.cms.repository;


import com.sg.cms.entity.Blog;
import java.time.LocalDate;
import java.util.List;
import javax.sql.DataSource;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit.jupiter.SpringExtension;


@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)

public class BlogRepositoryTest {

    
       
    @Autowired 
    BlogRepository blogRepository;
     
    public BlogRepositoryTest() {
        
    }
    
    @BeforeAll
    public static void setUpClass() {
        
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach 
    public void setUp() {
    }
    
    @AfterEach
    public void tearDown() {
        
    }
    
    @Test
    public void testAddBlog(){

        Blog blog = new Blog();
        blog.setTitle("title");
        blogRepository.save(blog);

        assertThat(blog).isNotNull();
        assertThat(blog.getId()).isGreaterThan(0);
    }
    
    @Test
    public void testAddAndGetBlog(){

        Blog blog = new Blog();
        blog.setTitle("title");
        blogRepository.save(blog);
        
        
        Blog fromRepo = blogRepository.findById(blog.getId()).orElse(null);
        assertEquals(blog,fromRepo);
        assertEquals("title",fromRepo.getTitle());
        
    }
    
    @Test
    public void testAddAndDelete(){
        Blog blog = new Blog();
        blog.setTitle("title");
        blogRepository.save(blog);
        
        Blog fromRepoBeforeDelete = blogRepository.findById(blog.getId()).orElse(null);
        assertEquals(blog,fromRepoBeforeDelete);
        
        blogRepository.delete(blog);
        Blog fromRepoAfterDelete = blogRepository.findById(blog.getId()).orElse(null);
        
        Assertions.assertNull(fromRepoAfterDelete);
    }
    
    @Test
    public void testAddAndUpdate(){
        Blog blog = new Blog();
        blog.setTitle("title1");
        blogRepository.save(blog);
        
        //To update, first retrieve the element to update in order to get the entity in a managed state
        Blog fromRepoBeforeUpdate = blogRepository.findById(blog.getId()).orElse(null);
        assertEquals(blog,fromRepoBeforeUpdate);
        
        //then update the needed field, then use .save()
        //jpaRepository will automatically know which record to update with .save() thanks to the entity Manager
        //which is why its important to get the intity in a managed state.
        fromRepoBeforeUpdate.setTitle("title2");
        blogRepository.save(fromRepoBeforeUpdate);
        Blog fromRepoAfterDelete = blogRepository.findById(blog.getId()).orElse(null);
        
        assertEquals("title2", fromRepoAfterDelete.getTitle());
    }
    
    
}
