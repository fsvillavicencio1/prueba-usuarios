package com.example.sgcmusuarios.service;

import java.util.*;
import com.example.sgcmusuarios.dto.*;

public interface IUserService {
	public List<User> getUsers();
	public User findById(Long id);
	public User save(User user);
	public void deleteById (Long id);
}
