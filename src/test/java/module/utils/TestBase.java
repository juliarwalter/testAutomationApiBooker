package module.utils;

import io.restassured.http.ContentType;
import module.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;

public class TestBase {
    protected String token;
    protected String url = "https://restful-booker.herokuapp.com";
    protected String pathLogin = "/auth";

    protected String pathGetBooking = "/booking";

    User user = new User();

    @BeforeEach
    //Method used to extract the token
    public void BeforeEach(){
        baseURI = url;

        this.token = given()
                //.log().all()
                .contentType(ContentType.JSON)
                .body(user)
            .when()
                .post(this.pathLogin)
            .then()
                //.log().body()
                .extract()
                .path("token");
    }
}
