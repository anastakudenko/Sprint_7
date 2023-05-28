import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import java.io.File;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.hasKey;

@RunWith(Parameterized.class)
public class TestCreateOrder {
        File json;

        public TestCreateOrder(String path) {
                this.json = new File(path);
        }
        @Before
        public void setUp() {
                RestAssured.baseURI = "http://qa-scooter.praktikum-services.ru";
        }
        @Parameters
        public static Object[][] getJson() {
                return new Object[][]{{"src/test/resources/orderWithColorBlack.json"},{"src/test/resources/orderWithColorGrey.json"},{"src/test/resources/orderWithColorsBlackAndGrey.json"},{"src/test/resources/orderWithoutColors.json"}};
        }
        @Test
        @DisplayName("Create Order /api/v1/orders")
        public void createOrder() {
                given()
                        .header("Content-type", "application/json")
                        .and()
                        .body(json)
                        .when()
                        .post("/api/v1/orders")
                        .then().statusCode(201)
                        .body("$", hasKey("track"));
        }
}


