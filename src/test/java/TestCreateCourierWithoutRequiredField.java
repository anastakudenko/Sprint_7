import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import static org.hamcrest.Matchers.equalTo;
import static io.restassured.RestAssured.*;
@RunWith(Parameterized.class)
public class TestCreateCourierWithoutRequiredField {
    private final String json;
    public TestCreateCourierWithoutRequiredField(String json) {
        this.json = json;
    }
    @Before
    public void setUp() {
        RestAssured.baseURI = "http://qa-scooter.praktikum-services.ru";
    }
    @Parameters
    public static Object[][] getJson() {
        return new Object[][]{{ "{\"login\": \"TestBE\"}"},{"{\"password\": \"TestBE\"}"}};
    }
    @Test
    @DisplayName("Error Without Required Fields /api/v1/courier")
    public void errorWithoutRequiredFields() {
        given()
                .header("Content-type", "application/json")
                .and()
                .body(json)
                .when()
                .post("/api/v1/courier")
                .then().statusCode(400)
                .body("message", equalTo("Недостаточно данных для создания учетной записи"));
    }
}
