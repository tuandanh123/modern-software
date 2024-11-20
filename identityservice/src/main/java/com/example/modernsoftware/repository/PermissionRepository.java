package com.example.modernsoftware.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.modernsoftware.entity.Permission;

@Repository
public interface PermissionRepository extends JpaRepository<Permission, String> {}
