/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */



package com.sg.cms.repository;


import com.sg.cms.entity.StaticPage;
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

public class StaticPageRepositoryTest {

    
       
    @Autowired 
    StaticPageRepository staticPageRepository;
     
    public StaticPageRepositoryTest() {
        
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
    public void testAddStaticPage(){

        StaticPage staticPage = new StaticPage();
        staticPage.setTitle("title");
        staticPageRepository.save(staticPage);

        assertThat(staticPage).isNotNull();
        assertThat(staticPage.getId()).isGreaterThan(0);
    }
    
    @Test
    public void testAddAndGetStaticPage(){

        StaticPage staticPage = new StaticPage();
        staticPage.setTitle("title");
        staticPageRepository.save(staticPage);
        
        
        StaticPage fromRepo = staticPageRepository.findById(staticPage.getId()).orElse(null);
        assertEquals(staticPage,fromRepo);
        assertEquals("title",fromRepo.getTitle());
        
    }
    
    @Test
    public void testAddAndDelete(){
        StaticPage staticPage = new StaticPage();
        staticPage.setTitle("title");
        staticPageRepository.save(staticPage);
        
        StaticPage fromRepoBeforeDelete = staticPageRepository.findById(staticPage.getId()).orElse(null);
        assertEquals(staticPage,fromRepoBeforeDelete);
        
        staticPageRepository.delete(staticPage);
        StaticPage fromRepoAfterDelete = staticPageRepository.findById(staticPage.getId()).orElse(null);
        
        Assertions.assertNull(fromRepoAfterDelete);
    }
    
    @Test
    public void testAddAndUpdate(){
        StaticPage staticPage = new StaticPage();
        staticPage.setTitle("title1");
        staticPageRepository.save(staticPage);
        
        //To update, first retrieve the element to update in order to get the entity in a managed state
        StaticPage fromRepoBeforeUpdate = staticPageRepository.findById(staticPage.getId()).orElse(null);
        assertEquals(staticPage,fromRepoBeforeUpdate);
        
        //then update the needed field, then use .save()
        //jpaRepository will automatically know which record to update with .save() thanks to the entity Manager
        //which is why its important to get the intity in a managed state.
        fromRepoBeforeUpdate.setTitle("title2");
        staticPageRepository.save(fromRepoBeforeUpdate);
        StaticPage fromRepoAfterDelete = staticPageRepository.findById(staticPage.getId()).orElse(null);
        
        assertEquals("title2", fromRepoAfterDelete.getTitle());
    }
    
    
}
