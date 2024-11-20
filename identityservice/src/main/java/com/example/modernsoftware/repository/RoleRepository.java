package com.example.modernsoftware.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.modernsoftware.entity.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, String> {}