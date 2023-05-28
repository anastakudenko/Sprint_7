package clients;
import io.restassured.response.ValidatableResponse;
import pojo.CourierCreateRequest;
import pojo.LoginCourierRequest;
import static io.restassured.RestAssured.given;

public class CourierClient extends BaseClient {
public ValidatableResponse create(CourierCreateRequest courierCreateRequest){
    return given()
            .spec(getSpec())
            .and()
            .body(courierCreateRequest)
            .when()
            .post("/api/v1/courier")
            .then();
}
public ValidatableResponse login(LoginCourierRequest loginCourierRequest){
    return given()
            .spec(getSpec())
            .body(loginCourierRequest)
            .when()
            .post("/api/v1/courier/login")
            .then();
}

public ValidatableResponse delete(int id) {
     return given()
             .spec(getSpec())
             .when()
             .delete("api/v1/courier/" + id)
             .then();
    }
}
