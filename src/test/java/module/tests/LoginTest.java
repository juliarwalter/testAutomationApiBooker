package module.tests;

import io.restassured.http.ContentType;
import module.User;
import module.utils.TestBase;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.core.IsEqual.equalTo;

@DisplayName("Login Page Api test: Auth - CreateToken")
public class LoginTest extends TestBase {

    User user = new User();
    @Test
    @DisplayName("C001: Test Login - Invalid credentials: Status 401")
    void testIncorrectPasswordLogin(){

        baseURI = url;

        user.setPassword("teste");

        given()
            .contentType(ContentType.JSON)
            .body(user)
        .when()
            .post(pathLogin)
        .then()
            .body("reason", equalTo("Bad credentials"))
            .statusCode(401);
    }
    @Test
    @DisplayName("C002: Test Login - Method request not allowed: Status 405")
    void testIncorrectMethodLogin(){
        baseURI = url;

        given()
                .contentType(ContentType.JSON)
                .body(user)
        .when()
                .put(pathLogin)
        .then()
                .statusCode(405);

    }
    @Test
    @DisplayName("C003: Test Login - Request without attribute: Status 400")
    void testLogin(){
        baseURI = url;

        given()
                .contentType(ContentType.TEXT)
                .body("{}")
                .when()
                .put(pathLogin)
                .then()
                .statusCode(400);

    }
}

