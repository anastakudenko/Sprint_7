import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class TestCreateCourierTwoIdenticalLogins {
    String json1 = "{\"login\": \"Identical3\", \"password\": \"12345\", \"firstName\": \"IdenticalTest\"}";

    @Before
    public void setUp() {
        RestAssured.baseURI = "http://qa-scooter.praktikum-services.ru";
    }
    @Test
    @DisplayName("Can't Create Two Identical Logins /api/v1/courier")
    public void cantCreateTwoIdenticalLogins() {
        String json2 = "{\"login\": \"Identical3\", \"password\": \"1234\", \"firstName\": \"Identical\"}";
        given()
                .header("Content-type", "application/json")
                .and()
                .body(json1)
                .when()
                .post("/api/v1/courier")
                .then().statusCode(201);
        given()
                .header("Content-type", "application/json")
                .and()
                .body(json2)
                .when()
                .post("/api/v1/courier")
                .then().statusCode(409)
                .body("message", equalTo("Этот логин уже используется. Попробуйте другой."));
    }

    @After
    public void tearDown() {
        int id = given()
                .header("Content-type", "application/json")
                .body(json1)
                .when()
                .post("/api/v1/courier/login")
                .then()
                .statusCode(200)
                .extract().jsonPath().get("id");
        given()
                .when()
                .delete("api/v1/courier/" + id)
                .then()
                .statusCode(200);
    }
}
