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
    @DisplayName("C001: Test Login - Invalid credentials - username 'admin' with other password: Status 401")
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
    void testLoginWithOutAttribute(){
        baseURI = url;

        given()
                .log().all()
                .contentType(ContentType.TEXT)
                .body("{}")
        .when()
                .post(pathLogin)
        .then()
                .statusCode(400);

    }

    @Test
    @DisplayName("C004: Test Login - Crate a new credential: Status 201")
    void testLoginNewCredential(){
        baseURI = url;

        user.setUsername("admin2");
        user.setPassword("teste");

        given()
                .log().all()
                .contentType(ContentType.JSON)
                .body(user)
        .when()
                .post(pathLogin)
        .then()
                .statusCode(201);

    }
}

