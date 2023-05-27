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
public class TestAuthorizationInvalidData {
    private String json;
    private int id;
    public TestAuthorizationInvalidData(String json) {
        this.json = json;
    }
    @Before
    public void setUp() {
        String credentials = "{\"login\": \"existingInvalid\", \"password\": \"existing\"}";
        RestAssured.baseURI = "http://qa-scooter.praktikum-services.ru";
        given()
                .header("Content-type", "application/json")
                .and()
                .body(credentials)
                .when()
                .post("/api/v1/courier")
                .then().statusCode(201);
        id = given()
                .header("Content-type", "application/json")
                .body(credentials)
                .when()
                .post("/api/v1/courier/login")
                .then()
                .statusCode(200)
                .extract().jsonPath().get("id");
    }

    @Parameters
    public static Object[][] getJson() {
        return new Object[][]{{ "{\"login\": \"existingInvalid\", \"password\": \"NOTexisting\"}"},{"{\"login\": \"Несуществующий\", \"password\": \"Пользователь\"}"}};
    }

    @Test
    @DisplayName("Authorization Invalid Data api/v1/courier/login")
    public void authorizationInvalidData() {
        given()
                .header("Content-type", "application/json")
                .and()
                .body(json)
                .when()
                .post("api/v1/courier/login")
                .then().statusCode(404                                          )
                .body("message", equalTo("Учетная запись не найдена"));
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
