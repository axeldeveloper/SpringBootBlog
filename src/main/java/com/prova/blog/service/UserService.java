package com.prova.blog.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.prova.blog.model.User;
import com.prova.blog.repository.UserRepository;


@Service
public class UserService {
	 	@Autowired
	    private UserRepository repository;
	 	
	 	//@Autowired
	    //private BCryptPasswordEncoder bCryptPasswordEncoder;
	     
	    public List<User> findAll() {
	        return repository.findAll();
	    }
	     
	    public User findOne(Long id) {
	        return repository.findOne(id);
	    }
	     
	    public User save(User user) {
	    	//user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
	        return repository.saveAndFlush(user);
	    }
	     
	    public void delete(Long id) {
	        repository.delete(id);
	    }
}
