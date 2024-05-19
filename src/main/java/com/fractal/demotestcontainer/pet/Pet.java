package com.fractal.demotestcontainer.pet;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import org.apache.logging.log4j.util.Strings;

import java.util.Objects;
import java.util.UUID;

@Entity
public class Pet {

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private UUID id;

	private String name;
	private String breed;
	private String type;

	public Pet (){}

	public Pet (String name, String breed, String type){
		if(Strings.isBlank(breed) || Strings.isBlank(name) || Strings.isBlank(type))
			throw new RuntimeException("Invalid object, check payload");
		this.breed = breed;
		this.name = name;
		this.type = type;
	}

	public UUID getId () {
		return id;
	}
}
