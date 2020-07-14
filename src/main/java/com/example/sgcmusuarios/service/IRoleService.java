package com.example.sgcmusuarios.service;

import java.util.*;


import com.example.sgcmusuarios.dto.*;


public interface IRoleService {
	public List<Role> getRoles();
	public Role findById(Long id);
	public Role save(Role role);
	public void deleteById (Long id);
}
