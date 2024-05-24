package com.fractal.demotestcontainer.pet.entrypoint;

import com.fractal.demotestcontainer.pet.Pet;
import com.fractal.demotestcontainer.pet.usecase.IUsecase;
import com.fractal.demotestcontainer.pet.usecase.PetUsecase;
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
public class PetEntrypoint {

	private final IUsecase<Pet> usecase;

	@Autowired
	public PetEntrypoint (PetUsecase usecase) {
		this.usecase = usecase;
	}

	@PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Pet> post (@RequestBody Pet newPet) {
		Pet response = this.usecase.create(newPet);
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}

	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Pet>> getAll () {
		List<Pet> response = this.usecase.getAll();
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}

	@GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getById (@PathVariable UUID id) {
		Optional<Pet> response = this.usecase.getById(id);
		if(response.isEmpty())
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No data found");
		return ResponseEntity.status(HttpStatus.OK).body((Pet)response.get());
	}

}
