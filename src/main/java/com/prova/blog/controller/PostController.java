package com.prova.blog.controller;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.prova.blog.model.Post;
import com.prova.blog.model.User;
import com.prova.blog.service.PostService;
import com.prova.blog.service.UserService;

 
@Controller
public class PostController {
     
    @Autowired
    private PostService service;
    
    @Autowired
    private UserService Usrservice;
    
    @GetMapping("/")
    public ModelAndView findAll() {         
        ModelAndView mv = new ModelAndView("/post");
        mv.addObject("posts", service.findAll());   
        return mv;
    }
    
    
    @GetMapping("/Posts")
    public ModelAndView PostsAll() {         
        ModelAndView mv = new ModelAndView("/posts/list");
        mv.addObject("posts", service.findAll());   
        return mv;
    }
     
    @GetMapping("/Posts/add")
    public ModelAndView add(Post post) {        
        ModelAndView mv = new ModelAndView("/posts/add");
        mv.addObject("post", post);        
        return mv;
    }
     
    @GetMapping("/Posts/edit/{id}")
    public ModelAndView edit(@PathVariable("id") Long id) {         
        return add(service.findOne(id));
    }
    @GetMapping("/Posts/detail/{id}")
    public ModelAndView detail(@PathVariable("id") Long id) {  
    	 ModelAndView mv = new ModelAndView("/posts/detail");
    	 
    	 mv.addObject("post", service.findOne(id));        
         return mv;
         //return add(service.findOne(id));
    }
    
     
    @GetMapping("/Posts/delete/{id}")
    public ModelAndView delete(@PathVariable("id") Long id) {
         
        service.delete(id);
         
        return findAll();
    }
 
    @PostMapping("/Posts/save")
    public ModelAndView save(@Valid Post post, BindingResult result) {
         
    	//System.out.println("Objeto: " + post);
    	
    	//System.out.println("BindingResult : " + result); 
    	if(result.hasErrors()) {
            return add(post);
        }
         
        service.save(post);
         
        return findAll();
    }
     
    
    
    
    
   /***
    *  Implementação de Usuarios
    *
    */
    
    @GetMapping("/Users")
    public ModelAndView UsersAll() {  
    	 ModelAndView mv = new ModelAndView("/users/list");
         mv.addObject("rows", Usrservice.findAll());   
        return mv;
    }
    
    @GetMapping("/Users/add")
    public ModelAndView add(User user) {  
        ModelAndView mv = new ModelAndView("/users/add");
        mv.addObject("user", user);        
        return mv;
    }
    
    @PostMapping("/Users/save")
    public ModelAndView save(@Valid User user, BindingResult result) {
         
    	System.out.println("Objeto: " + user);
    	
    	System.out.println("BindingResult : " + result); 
    	
    	if(result.hasErrors()) {
            return add(user);
        }
         
    	Usrservice.save(user);
         
        return findAll();
    }
    
    @GetMapping("/Users/edit/{id}")
    public ModelAndView editar(@PathVariable("id") Long id) {
         
        return add(Usrservice.findOne(id));
    }
    
    @GetMapping("/Users/delete/{id}")
    public ModelAndView deletar(@PathVariable("id") Long id) {
         
    	Usrservice.delete(id);
         
        return findAll();
    }

    
    /***
    *  Implementação geral
    *
    */
    
    @GetMapping("/About")
    public ModelAndView sobre() {        
        ModelAndView mv = new ModelAndView("/geral/about");
        //mv.addObject("post", post);        
        return mv;
    }
    
    @GetMapping("/403")
    public String error403() {
        return "/geral/403";
    }
    
    @GetMapping("/login")
    public String login() {
        return "/geral/login";
    }
    
    
    
    
    
}
