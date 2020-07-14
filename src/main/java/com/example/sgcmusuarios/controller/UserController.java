package com.example.sgcmusuarios.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import com.example.sgcmusuarios.payload.request.UserRequest;
import com.example.sgcmusuarios.payload.request.RoleRequest;
import com.example.sgcmusuarios.service.*;

import com.example.sgcmusuarios.dao.UserRepository;
import com.example.sgcmusuarios.dto.Role;
import com.example.sgcmusuarios.dto.User;
import com.example.sgcmusuarios.dao.RoleRepository;
import com.example.sgcmusuarios.payload.response.MessageResponse;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.validation.Valid;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/sdgcm/api/v1")
public class UserController {
	
	@Autowired
	private UserService userService;
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	private RoleService roleService;
	@Autowired
	RoleRepository roleRepository;
	
	@Autowired
	PasswordEncoder encoder;
	
	@PostMapping("/user")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> saveUser(@Valid @RequestBody UserRequest userRequest){
		if(userRepository.existsByUsername(userRequest.getUsername())) {
			return ResponseEntity
					 .badRequest()
					 .body(new MessageResponse("Error: Usuario ya existe"));
		}
		
		User user = new User(userRequest.getUsername(), encoder.encode(userRequest.getPassword()), userRequest.getEmail());
		
		Set<String> strRoles = userRequest.getRole();
		Set<Role> roles = new HashSet<>();
		
		strRoles.forEach(role  -> {
			Role userRoles = roleRepository.findByNombre(role)
					.orElseThrow(() -> new RuntimeException("Error: Rol no registrado.."));
			roles.add(userRoles);
		});
		
		user.setRoles(roles);
		userService.save(user);
		
		return ResponseEntity.ok(new MessageResponse("Usuario registrado con éxito!"));
	}
	
	@GetMapping(value="/users")
	@PreAuthorize("hasRole('ADMIN')")
	public List<User> getUsers(){
		return userService.getUsers();
	}
	
	
	@GetMapping(value="/finduser/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public User getUserById(@PathVariable Long id) {
		return userService.findById(id);
	}
	
	@PutMapping(value="/updateuser/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> updateUser(@RequestBody UserRequest userRequest, @PathVariable Long id){
		User user = userService.findById(id);
		String username = new String(user.getUsername());
		String username2 = new String(userRequest.getUsername());
		if(username.intern() != username2.intern()) {
			if(userRepository.existsByUsername(userRequest.getUsername())) {
				return ResponseEntity
						 .badRequest()
						 .body(new MessageResponse("Error: Usuario ya existe"));
			}
		}
		
		//User user = userService.findById(id);
		user.setUsername(userRequest.getUsername());
		user.setPassword(encoder.encode(userRequest.getPassword()));
		user.setEmail(userRequest.getEmail());
		
		Set<String> strRoles = userRequest.getRole();
		Set<Role> roles = new HashSet<>();
		
		strRoles.forEach(role  -> {
			Role userRoles = roleRepository.findByNombre(role)
					.orElseThrow(() -> new RuntimeException("Error: Rol no registrado.."));
			roles.add(userRoles);
		});
		
		user.setRoles(roles);
		userService.save(user);
		
		return ResponseEntity.ok(new MessageResponse("Usuario modificado con éxito!"));
		
	}
	
	@DeleteMapping("/deleteuser/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> deleteById(@PathVariable Long id) {
		userService.deleteById(id);
		
		return ResponseEntity.ok(new MessageResponse("Usuario eliminado con éxito!"));
	}
	
	@PostMapping("/rol")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> saveRol(@Valid @RequestBody RoleRequest roleRequest){
		if(roleRepository.existsByNombre(roleRequest.getNombre())) {
			return ResponseEntity
					 .badRequest()
					 .body(new MessageResponse("Error: Rol ya existe"));
		}
		
		Role rol = new Role(roleRequest.getNombre()); 
		
		roleService.save(rol);
		
		return ResponseEntity.ok(new MessageResponse("Rol registrado con éxito!"));
	}
	
	@GetMapping(value="/roles")
	@PreAuthorize("hasRole('ADMIN')")
	public List<Role> getRoles(){
		return roleService.getRoles();
	}
	
	@GetMapping(value="/findrol/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public Role getRolById(@PathVariable Long id) {
		return roleService.findById(id);
	}
	
	@PutMapping(value="/updaterol/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> updateRol(@RequestBody RoleRequest roleRequest, @PathVariable Long id){
		Role rol = roleService.findById(id);
		String rol_nombre = new String(rol.getNombre());
		String rol_nombre2 = new String(roleRequest.getNombre());
		if(rol_nombre.intern() != rol_nombre2.intern()) {
			if(roleRepository.existsByNombre(roleRequest.getNombre())) {
				return ResponseEntity
						 .badRequest()
						 .body(new MessageResponse("Error: Rol ya existe"));
			}
		}
		
		rol.setNombre(roleRequest.getNombre());
		roleService.save(rol);
		return ResponseEntity.ok(new MessageResponse("Rol modificado con éxito!"));
		
	}
	
	@DeleteMapping("/deleterole/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> deleteRolById(@PathVariable Long id) {
		roleService.deleteById(id);
		
		return ResponseEntity.ok(new MessageResponse("Rol eliminado con éxito!"));
	}
	
	
}
