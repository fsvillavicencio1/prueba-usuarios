package com.example.sgcmusuarios.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.sgcmusuarios.dto.*;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long>{
	Optional<Role> findByNombre(String nombre);
	Boolean existsByNombre(String nombre);
}
