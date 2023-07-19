package clients;
import io.restassured.response.ValidatableResponse;
import pojo.OrderCreateRequest;
import static io.restassured.RestAssured.given;

public class OrderClient extends BaseClient {
    public ValidatableResponse createOrder (OrderCreateRequest orderCreateRequest) {
        return given()
                .spec(getSpec())
                .and()
                .body(orderCreateRequest)
                .when()
                .post("/api/v1/orders")
                .then();
    }
}
