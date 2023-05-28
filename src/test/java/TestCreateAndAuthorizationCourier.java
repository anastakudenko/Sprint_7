import clients.CourierClient;
import dataprovider.CourierProvider;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Test;
import pojo.CourierCreateRequest;
import pojo.LoginCourierRequest;
import static org.hamcrest.Matchers.equalTo;
public class TestCreateCourierOk {
    private final CourierClient courierClient = new CourierClient();
    private int id;
    @Test
    @DisplayName("Can Create Courier /api/v1/courier")
    public void canCreateCourier() {
        CourierCreateRequest courierCreateRequest =  CourierProvider.getRandomCreateCourier();
        // создание
        courierClient.create(courierCreateRequest)
                .statusCode(201)
                .body("ok", equalTo(true));

        // получение айди для удаления
        LoginCourierRequest loginCourierRequest = LoginCourierRequest.from(courierCreateRequest);
        id = courierClient.login(loginCourierRequest)
                .statusCode(200)
                .extract().jsonPath().get("id");
    }

    @After
    public void tearDown() {
        courierClient.delete(id)
        .statusCode(200);
    }
}
