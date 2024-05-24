package com.fractal.demotestcontainer;

import com.fractal.demotestcontainer.config.PostgresContainer;
import com.fractal.demotestcontainer.pet.Pet;
import com.fractal.demotestcontainer.pet.repository.PetRepository;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.stream.Stream;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ActiveProfiles("test")
@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = DemoTestcontainerApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PetEntrypointSpec extends PostgresContainer {

	private final PetRepository repository;

	@Autowired
	public PetEntrypointSpec (PetRepository repository, @LocalServerPort int serverPort) {
		this.repository = repository;
		RestAssured.port = serverPort;
	}

	@Test
	@DisplayName("Should create a new pet and be able to retrieve it by id")
	public void saveAndFetchDataById(){
		var pet = new Pet("Fish", "Fish", "Fish");

		var postResponse = given()
				.contentType(ContentType.JSON)
				.when()
				.body(pet)
				.post("/pet")
				.then()
				.statusCode(200)
				.extract()
				.as(Pet.class);

		var petDB = given()
				.contentType(ContentType.JSON)
				.when()
				.get("/pet/" + postResponse.getId())
				.then()
				.statusCode(200)
				.extract()
				.as(Pet.class);

		assertEquals(postResponse.getId(), petDB.getId());
	}

	@ParameterizedTest
	@MethodSource("petStreamData")
	@DisplayName("Should create a new pet and be able to retrieve all pets")
	public void saveAndFetchData(Pet pet){

		given()
				.contentType(ContentType.JSON)
				.when()
				.body(pet)
				.post("/pet")
				.then()
				.statusCode(200);

		given()
				.contentType(ContentType.JSON)
				.when()
				.get("/pet")
				.then()
				.statusCode(200);
	}

	private static Stream<Pet> petStreamData(){
		return Stream.of(
				new Pet("Dog", "Dog", "Dog"),
				new Pet("Cat", "Cat", "Cat")
		);
	}

}