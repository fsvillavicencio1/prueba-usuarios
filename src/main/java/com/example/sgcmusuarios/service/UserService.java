package com.example.sgcmusuarios.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import com.example.sgcmusuarios.dao.*;
import com.example.sgcmusuarios.dto.*;

@Service
public class UserService implements IUserService{
	
	@Autowired
	private UserRepository dao;
	
	@Override
	public User save(User user) {
		return dao.save(user);
	}
	
	@Override
	public List<User> getUsers(){
		return (List<User>) dao.findAll();
	}
	
	@Override
	@Transactional(readOnly = true)
	public User findById(Long id) {
		return dao.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("ID de rol no valido %s", id)));
	}
	
	@Override
	public void deleteById(Long id) {
		dao.deleteById(id);
	}


}
