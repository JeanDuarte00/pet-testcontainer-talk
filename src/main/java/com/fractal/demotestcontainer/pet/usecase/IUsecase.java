package com.fractal.demotestcontainer.pet.usecase;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface IUsecase<T> {

	T create(T payload);
	List<T> getAll();
	Optional<T> getById(UUID id);
}
