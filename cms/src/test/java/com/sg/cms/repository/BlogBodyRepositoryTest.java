/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */



package com.sg.cms.repository;


import com.sg.cms.entity.BlogBody;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;


@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)

public class BlogBodyRepositoryTest {

    
       
    @Autowired 
    BlogBodyRepository blogBodyRepository;
     
    public BlogBodyRepositoryTest() {
        
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
    public void testAddBlogBody(){

        BlogBody blogBody = new BlogBody();
        blogBody.setId(1);
        blogBody.setBody("body");
        blogBodyRepository.save(blogBody);

        assertThat(blogBody).isNotNull();
        assertThat(blogBody.getId()).isGreaterThan(0);
    }
    
    @Test
    public void testAddAndGetBlogBody(){

        BlogBody blogBody = new BlogBody();
        blogBody.setBody("body");
        blogBodyRepository.save(blogBody);
        
        
        BlogBody fromRepo = blogBodyRepository.findById(blogBody.getId()).orElse(null);
        assertEquals(blogBody,fromRepo);
        assertEquals("body",fromRepo.getBody());
        
    }
    
    @Test
    public void testAddAndDelete(){
        BlogBody blogBody = new BlogBody();
        blogBody.setId(1);
        blogBody.setBody("body");
        blogBodyRepository.save(blogBody);
        
        BlogBody fromRepoBeforeDelete = blogBodyRepository.findById(blogBody.getId()).orElse(null);
        assertEquals(blogBody,fromRepoBeforeDelete);
        
        blogBodyRepository.delete(blogBody);
        BlogBody fromRepoAfterDelete = blogBodyRepository.findById(blogBody.getId()).orElse(null);
        
        Assertions.assertNull(fromRepoAfterDelete);
    }
    
    @Test
    public void testAddAndUpdate(){
        BlogBody blogBody = new BlogBody();
        blogBody.setBody("body1");
        blogBodyRepository.save(blogBody);
        
        //To update, first retrieve the element to update in order to get the entity in a managed state
        BlogBody fromRepoBeforeUpdate = blogBodyRepository.findById(blogBody.getId()).orElse(null);
        assertEquals(blogBody,fromRepoBeforeUpdate);
        
        //then update the needed field, then use .save()
        //jpaRepository will automatically know which record to update with .save() thanks to the entity Manager
        //which is why its important to get the intity in a managed state.
        fromRepoBeforeUpdate.setBody("body2");
        blogBodyRepository.save(fromRepoBeforeUpdate);
        BlogBody fromRepoAfterDelete = blogBodyRepository.findById(blogBody.getId()).orElse(null);
        
        assertEquals("body2", fromRepoAfterDelete.getBody());
    }
    
    
}
