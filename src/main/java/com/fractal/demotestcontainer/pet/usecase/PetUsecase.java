package com.fractal.demotestcontainer.pet.usecase;

import com.fractal.demotestcontainer.pet.Pet;
import com.fractal.demotestcontainer.pet.repository.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PetUsecase implements IUsecase<Pet>{

	private final PetRepository repository;

	@Autowired
	public PetUsecase (PetRepository repository) {
		this.repository = repository;
	}

	@Override
	public Pet create (Pet payload) {
		return this.repository.save(payload);
	}

	@Override
	public List<Pet> getAll () {
		return this.repository.findAll();
	}

	@Override
	public Optional<Pet> getById (UUID id) {
		return this.repository.findById(id);
	}
}
