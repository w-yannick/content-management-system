/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.cms.view;

import org.springframework.stereotype.Component;

/**
 *
 * @author w-yan
 */
@Component
public class CmsView {
    public String displayIndexPage(){
        return "index";
    }
    public String displayAdminPage(){
        return "admin";
    }
    public String displayLoginPage(){
        return "login";
    }
    public String displayLogoutPage(){
        return "logout";
    }
    public String displayBlogPage(){
        return "blog";
    }
    public String displayStaticPage(){
        return "staticPage";
    }
    public String displayEditStaticPage(){
        return "editStaticPage";
    }
    public String displayStaticPageDetails(){
        return "staticPageDetails";
    }
    public String displayCreateStaticPage(){
        return "createStaticPage";
    }
    public String displaySearchPage(){
        return "searchBlog";
    }
    public String displayCreatePage(){
        return "create";
    }
    public String displayContactPage(){
        return "contact";
    }
    public String displayConfirmContactPage(){
        return "confirmContact";
    }
    public String displayPendingApprovalPage(){
        return "pendingApproval";
    }
    
}
