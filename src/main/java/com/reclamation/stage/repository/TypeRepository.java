package com.reclamation.stage.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.reclamation.stage.models.Type;

public interface TypeRepository extends JpaRepository<Type, Long> {

}
