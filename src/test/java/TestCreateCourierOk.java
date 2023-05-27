import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import static org.hamcrest.Matchers.equalTo;
import static io.restassured.RestAssured.*;

@RunWith(Parameterized.class)
public class TestCreateCourierOk {
    private final String json;
    public TestCreateCourierOk(String json) {
        this.json = json;
    }
    @Before
    public void setUp() {
        RestAssured.baseURI = "http://qa-scooter.praktikum-services.ru";
    }
    @Parameters
    public static Object[][] getJson() {
        return new Object[][]{{ "{\"login\": \"existingOK1\", \"password\": \"existing\", \"firstName\": \"existing\"}"},{"{\"login\": \"1234OK1\", \"password\": \"1234\"}"}};
    }
    @Test
    @DisplayName("Can Create Courier /api/v1/courier")
    public void canCreateCourier() {
        given()
                .header("Content-type", "application/json")
                .and()
                .body(json)
                .when()
                .post("/api/v1/courier")
                .then().statusCode(201)
                .body("ok", equalTo(true));
    }

    @After
    public void tearDown() {
        int id = given()
                .header("Content-type", "application/json")
                .body(json)
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
