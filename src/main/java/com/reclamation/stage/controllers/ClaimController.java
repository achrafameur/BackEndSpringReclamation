package com.reclamation.stage.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.reclamation.stage.models.Claim;
import com.reclamation.stage.models.Localisation;
import com.reclamation.stage.payload.response.MessageResponse;
import com.reclamation.stage.repository.ClaimRepository;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RestController
@RequestMapping(value = "/api/client/claim")
public class ClaimController {
	
	@Autowired
	ClaimRepository claimRepository;
	
	
	@PostMapping("/add")
	public ResponseEntity<?> addclaim(@RequestBody Claim claim) {
		claim.setAnswer("");
		claimRepository.save(claim);
		return ResponseEntity.ok(new MessageResponse("claim added successfully!"));
		
	}
	  @GetMapping(path="/all/{page}/{size}")
	  public @ResponseBody Page<Claim> getAllClaims(@PathVariable("page") Integer page, @PathVariable("size") Integer size) {
		Pageable contentPaginated = PageRequest.of(page, size);
	    return claimRepository.findAll(contentPaginated);
	  }
	  @GetMapping(path="/all/{id}")
	  	public @ResponseBody ResponseEntity<Claim> getAllClaimsDetails(@PathVariable("id") Long id) {
		  Claim claim =  claimRepository.findById(id).orElseThrow();
	    return ResponseEntity.ok(claim);
	  }
	  @GetMapping(path="/username/{username}/{page}/{size}")
	  public Page<Claim> getClaimsByUsername(@PathVariable("username") String username,@PathVariable("page") Integer page, @PathVariable("size") Integer size ) {
			Pageable contentPaginated = PageRequest.of(page, size);
		    return claimRepository.findByUsername(username,contentPaginated);
	     }
	  @GetMapping(path="/admin/{id}")
	  public Optional<Claim> getClaimsById(@PathVariable("id") Long id ) {
		    return claimRepository.findById(id);
	     }
}
