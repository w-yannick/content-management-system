/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.cms.repository;

import com.sg.cms.entity.Blog;
import java.time.LocalDate;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author w-yan
 */


@Repository
public interface BlogRepository extends JpaRepository<Blog, Integer> {
    @Query(value = "SELECT * " +
            "FROM Blog " +
            "WHERE (ExpiryDate >= ? " + 
            "OR ExpiryDate is null) " + 
            "AND Approved = ? " + 
            "Order by id Desc LIMIT 20", nativeQuery = true)
    List<Blog> findNonExpired(LocalDate localDate, boolean approved);
    
    @Query(value = "SELECT * " +
            "FROM Blog " +
            "WHERE Approved = ? " + 
            "Order by id Desc LIMIT 20", nativeQuery = true)
    List<Blog> findByApproved(boolean approved);
    
    
}
