package com.reclamation.stage.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.reclamation.stage.models.Claim;

@Repository
public interface ClaimRepository extends JpaRepository<Claim, String>  {
      Page<Claim> findAll(Pageable pageable);
      Page<Claim> findByUsername(String username,Pageable pageable);
	  Optional<Claim> findById(Long id);

}
