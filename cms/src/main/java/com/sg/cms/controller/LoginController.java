/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.cms.controller;

import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class LoginController {

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String showLoginForm(HttpServletRequest request, Model model) {
        HomeController.assignRole(request, model);
        return "login";
    }
    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String showLogoutConfirmationForm(HttpServletRequest request, Model model) {
        HomeController.assignRole(request, model);
        return "logout";
    }
}