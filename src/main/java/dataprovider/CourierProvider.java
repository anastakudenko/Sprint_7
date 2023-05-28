package dataprovider;
import org.apache.commons.lang3.RandomStringUtils;
import pojo.CourierCreateRequest;

public class CourierProvider {
    public static CourierCreateRequest getRandomCreateCourier() {
        CourierCreateRequest courierCreateRequest = new CourierCreateRequest();
        courierCreateRequest.setLogin(RandomStringUtils.randomAlphabetic(5));
        courierCreateRequest.setPassword(RandomStringUtils.randomAlphabetic(8));
        courierCreateRequest.setFirstName(RandomStringUtils.random(6));
        return courierCreateRequest;
    }
}
