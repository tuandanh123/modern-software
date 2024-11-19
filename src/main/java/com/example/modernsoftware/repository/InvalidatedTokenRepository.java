package com.example.modernsoftware.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.modernsoftware.entity.InvalidatedToken;

public interface InvalidatedTokenRepository extends JpaRepository<InvalidatedToken, String> {}
