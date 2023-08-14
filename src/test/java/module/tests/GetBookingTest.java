package module.tests;

import io.restassured.http.ContentType;
import module.utils.TestBase;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

@DisplayName("Get Booking Api test: List all books, list books by name and by id")
public class GetBookingTest extends TestBase {

    @Test
    @DisplayName("C001: Test GetBooking - Request without Token: Status 401")
    void testRequestWithoutToken() {

        baseURI = url;

        given()
                //.log().all()
                .contentType(ContentType.JSON)
        .when()
                .get(pathGetBooking)
        .then()
                //.log().body()
                .statusCode(401);
    }

    @Test
    @DisplayName("C002: Test GetBooking - Successful request /booking: Status 200")
    void testSuccessfulRequest() {

        baseURI = url;

        given()
                .contentType(ContentType.JSON)
                .header("Authorization",  "Basic " + token)
        .when()
                .get(pathGetBooking)
        .then()
                .statusCode(200)
        .body("boookingid", is(notNullValue()));
    }
    @Test
    @DisplayName("C003: Test GetBooking - Successful request /booking?lastname: Status 200")
    void testSuccessfulRequestLastNameBrown() {

        baseURI = url;

        given()
                .contentType(ContentType.JSON)
                .header("Authorization",  "Basic " + token)
        .when()
                .get(pathGetBooking + "?lastname=Brown")
        .then()
                .statusCode(200)
                .body("boookingid", is(notNullValue()));
    }
    @Test
    @DisplayName("C004: Test GetBooking - Successful request /booking?bookId: Status 200")
    void testSuccessfulRequestId5() {

        baseURI = url;

        given()
                .contentType(ContentType.JSON)
                .log().all()
                .header("Authorization",  "Basic " + token)
        .when()
                .get(pathGetBooking + "/5")
        .then()
                .statusCode(200);
    }
}
