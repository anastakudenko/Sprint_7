import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasKey;

public class TestGetListOrders {
    @Before
    public void setUp() {
        RestAssured.baseURI = "http://qa-scooter.praktikum-services.ru";
    }

    @Test
    @DisplayName("Get List Order /api/v1/orders")
    public void getListOrder() {
        given()
                .header("Content-type", "application/json")
                .get("api/v1/orders")
                .then().statusCode(200)
                .body("$", hasKey("orders"));
    }
}
