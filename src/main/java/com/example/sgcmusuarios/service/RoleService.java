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
public class RoleService implements IRoleService{
	
	@Autowired
	private RoleRepository daoRole;
	
	@Override
	public Role save(Role role) {
		return daoRole.save(role);
	}
	
	@Override
	public List<Role> getRoles(){
		return (List<Role>) daoRole.findAll();
	}
	
	@Override
	@Transactional(readOnly = true)
	public Role findById(Long id) {
		return daoRole.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("ID de rol no valido %s", id)));
	}
	
	@Override
	public void deleteById(Long id) {
		daoRole.deleteById(id);
	}
}
