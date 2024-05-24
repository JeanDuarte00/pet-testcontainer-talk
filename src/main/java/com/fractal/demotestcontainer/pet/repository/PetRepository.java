package com.fractal.demotestcontainer.pet.repository;

import com.fractal.demotestcontainer.pet.Pet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PetRepository extends JpaRepository<Pet, UUID> {}
