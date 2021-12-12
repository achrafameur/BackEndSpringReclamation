package com.reclamation.stage.controllers;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.reclamation.stage.models.Claim;
import com.reclamation.stage.models.Localisation;
import com.reclamation.stage.models.Type;
import com.reclamation.stage.models.User;
import com.reclamation.stage.payload.response.MessageResponse;
import com.reclamation.stage.repository.ClaimRepository;
import com.reclamation.stage.repository.LocalisationRepository;
import com.reclamation.stage.repository.TypeRepository;
import com.reclamation.stage.repository.UserRepository;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RestController
@RequestMapping(value = "/api/admin")
public class AdminController {

	@Autowired
	LocalisationRepository localisationRepository ;
	@Autowired
	TypeRepository typeRepository ;	
	@Autowired
	ClaimRepository claimRepository;
	@Autowired
	UserRepository userRepository;

	
	@PostMapping("/localisation")
	public ResponseEntity<?> addlocalisation(@RequestBody Localisation localisation) {
		localisationRepository.save(localisation);
		return ResponseEntity.ok(new MessageResponse("localisation added successfully!"));
		
	}
	  @GetMapping(path="/locations")
	  public @ResponseBody Iterable<Localisation> getAllLocalisations() {
	    return localisationRepository.findAll();
	  }
	  @GetMapping(path="/location/{id}")
	  public ResponseEntity<Localisation> getLocalisationsById(@PathVariable Long id ) {
		Localisation localisation = localisationRepository.findById(id).orElseThrow();
		return ResponseEntity.ok(localisation);
	  }
		@PutMapping("/update/location/{id}")
		public ResponseEntity<?> updatelocalisation(@RequestBody Localisation localisationdetails , @PathVariable("id") Long id) {
			Localisation localisation = new Localisation();
		    localisation.setId(localisationdetails.getId());
		    localisation.setName(localisationdetails.getName());
		    localisationRepository.save(localisation);
			return ResponseEntity.ok(localisation);	
		}
		@DeleteMapping(path="/delete/location/{id}")
		public ResponseEntity<Map<String, Boolean>> deletelocalisation(@PathVariable("id") Long id ){
			localisationRepository.deleteById(id);
			 Map<String, Boolean> response = new HashMap<>();
			 response.put("deleted", Boolean.TRUE);
			 return ResponseEntity.ok(response);
		}
		
	
		//settings
	@PostMapping("/addtype")
	public ResponseEntity<?> addtype(@RequestBody Type type) {
		typeRepository.save(type);
		return ResponseEntity.ok(new MessageResponse("localisation added successfully!"));
	}
	  @GetMapping(path="/types")
	  public @ResponseBody Iterable<Type> getAllTypes() {
	    return typeRepository.findAll();
	  }
	  @GetMapping(path="/type/{id}")
	  public ResponseEntity<Type> getTypesById(@PathVariable Long id ) {
		  Type type = typeRepository.findById(id).orElseThrow();
		return ResponseEntity.ok(type);
	  }
		@PutMapping("/update/type/{id}")
		public ResponseEntity<?> updateType(@RequestBody Type typedetails , @PathVariable("id") Long id) {
			Type type = new Type();
			type.setId(typedetails.getId());
			type.setName(typedetails.getName());
			Type updatedType = typeRepository.save(type);
			return ResponseEntity.ok(updatedType);	
		}
		@DeleteMapping(path="/delete/type/{id}")
		public ResponseEntity<Map<String, Boolean>> deletetype(@PathVariable("id") Long id ){
			typeRepository.deleteById(id);
			 Map<String, Boolean> response = new HashMap<>();
			 response.put("deleted", Boolean.TRUE);
			 return ResponseEntity.ok(response);
		}
		
		@PutMapping("/update/answer/{id}")
		public ResponseEntity<?> addAnswer(@RequestBody Claim claimDetails , @PathVariable("id") Long id) {
			Claim claim = new Claim();
			claim.setId(claimDetails.getId());
			claim.setEmail(claimDetails.getEmail());
			claim.setStatus(claimDetails.getStatus());
			claim.setRemarque(claimDetails.getRemarque());
			claim.setLocalisation(claimDetails.getLocalisation());
			claim.setType(claimDetails.getType());
			claim.setUsername(claimDetails.getUsername());
			claim.setAnswer(claimDetails.getAnswer());
			Claim updatedClaim = claimRepository.save(claim);
			return ResponseEntity.ok(updatedClaim);
		}
		@PutMapping("/update/status/{id}")
		public ResponseEntity<?> updateStatus(@RequestBody Claim claimDetails , @PathVariable("id") Long id) {
			Claim claim = new Claim();
			claim.setId(claimDetails.getId());
			claim.setEmail(claimDetails.getEmail());
			claim.setStatus(claimDetails.getStatus());
			claim.setRemarque(claimDetails.getRemarque());
			claim.setLocalisation(claimDetails.getLocalisation());
			claim.setType(claimDetails.getType());
			claim.setUsername(claimDetails.getUsername());
			claim.setAnswer(claimDetails.getAnswer());
			Claim updatedClaim = claimRepository.save(claim);
			return ResponseEntity.ok(updatedClaim);
		}

		
		//users
		  @GetMapping(path="/allUsers/{page}/{size}")
		  	public @ResponseBody Page<User> getAllUsers(@PathVariable("page") Integer page, @PathVariable("size") Integer size) {
			  Pageable contentPaginated = PageRequest.of(page, size);
		    return userRepository.findAll(contentPaginated);
		  }
			@PutMapping("/update/users/{id}")
			public ResponseEntity<?> updateUsers(@RequestBody User userDetails , @PathVariable("id") Long id) {
				User user = new User();
				user.setId(userDetails.getId());
				user.setEmail(userDetails.getEmail());
				user.setUsername(userDetails.getUsername());
				user.setRoles(userDetails.getRoles());
				User updatedUser = userRepository.save(user);
				return ResponseEntity.ok(updatedUser);
			}
}
