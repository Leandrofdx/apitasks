package oi.github.leandrofdx.apitestes;

import org.junit.BeforeClass;
import org.junit.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class ApiTest {
	
	@BeforeClass
	public static void setup() {
		RestAssured.baseURI = "http://localhost:8001/tasks-backend";
	}

	@Test
	public void deveRetornarTarefas() {
		RestAssured.given()
		.log().all()
		.when()
			.get("/todo")
		.then()
			.statusCode(200)
		;
	}
	
	
	@Test
	public void deveAdicionarTarefasComSucesso() {
		RestAssured.given()
			.body("{ \"task\": \"nova\", \"dueDate\": \"2024-10-10\" }")
			.contentType(ContentType.JSON)
			
		.when()
			.post("/todo")
		.then()
			.statusCode(201)
		;
	}
	
	@Test
	public void naoDeveAdicionarTarefasInvalida() {
		RestAssured.given()
			.body("{ \"task\": \"nova\", \"dueDate\": \"2010-10-10\" }")
			.contentType(ContentType.JSON)
			
		.when()
			.post("/todo")
		.then()
			.statusCode(400)
		;
	}
	
	
}
