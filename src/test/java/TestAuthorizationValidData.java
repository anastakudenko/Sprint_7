import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static io.restassured.RestAssured.given;

public class TestAuthorizationValidData {
    private final String json = "{\"login\": \"existingValid\", \"password\": \"existing\"}";
    private int id;

    @Before
    public void setUp() {
        RestAssured.baseURI = "http://qa-scooter.praktikum-services.ru";
        given()
                .header("Content-type", "application/json")
                .and()
                .body(json)
                .when()
                .post("/api/v1/courier")
                .then().statusCode(201);
    }
        @Test
        @DisplayName("Authorization Valid Data api/v1/courier/login")
        public void authorizationValidData() {
            id = given()
                    .header("Content-type", "application/json")
                    .body(json)
                    .when()
                    .post("/api/v1/courier/login")
                    .then()
                    .statusCode(200)
                    .extract().jsonPath().get("id");
    }

    @After
    public void tearDown() {
        given()
                .when()
                .delete("api/v1/courier/" + id)
                .then()
                .statusCode(200);
    }
}
