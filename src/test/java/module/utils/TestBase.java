package module.utils;

import io.restassured.http.ContentType;
import module.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;

public class TestBase {
    private String token;
    public String url = "https://restful-booker.herokuapp.com";
    public String pathLogin = "/auth";

    User user = new User();

    @Test
    @BeforeEach
    //Method used to extract the token
    public void BeforeEach(){
        baseURI = url;

        this.token = given()
                .log().all()
                .contentType(ContentType.JSON)
                .body(user)
            .when()
                .post(this.pathLogin)
            .then()
                //.log().body()
                .extract()
                .path("token");
        System.out.println(token);
    }
}
