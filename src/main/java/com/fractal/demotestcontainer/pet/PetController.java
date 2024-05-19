package com.fractal.demotestcontainer.pet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/pet")
public class PetController {

	private final PetRepository repository;

	@Autowired
	public PetController (PetRepository repository) {
		this.repository = repository;
	}

	@PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Pet> post (@RequestBody Pet newPet) {
		Pet response = this.repository.save(newPet);
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}

	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Pet>> getAll () {
		List<Pet> response = this.repository.findAll();
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}

	@GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getById (@PathVariable UUID id) {
		Optional<Pet> response = this.repository.findById(id);
		if(response.isEmpty())
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No data found");
		return ResponseEntity.status(HttpStatus.OK).body((Pet)response.get());
	}

}
