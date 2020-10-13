package UserOperations;

import POJO.UserObject;

import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class UserTest {
    private UserObject userObject;

    public UserTest() {
        userObject = new UserObject(61236, "grandmasterbit", "John", "Doe", "jdoe@example.com", "12345678", "08002341231", 0);
    }

    @Test(priority = 1)
    public void postUserTest() {
        given()
                .contentType(ContentType.JSON)
                .body(userObject)
                .post("https://petstore.swagger.io/v2/user")
                .then().assertThat()
                .statusCode(200);
    }

    @Test(priority = 2)
    public void getUserTest() {
        given()
                .contentType(ContentType.JSON)
                .get("https://petstore.swagger.io/v2/user/" + userObject.getUsername())
                .then().assertThat()
                .statusCode(200)
                .and()
                .assertThat()
                .body("id", equalTo(userObject.getId()))
                .and().body("username", equalTo(userObject.getUsername()))
                .and().body("firstName", equalTo(userObject.getFirstName()))
                .and().body("lastName", equalTo(userObject.getLastName()))
                .and().body("email", equalTo(userObject.getEmail()))
                .and().body("password", equalTo(userObject.getPassword()))
                .and().body("phone", equalTo(userObject.getPhone()))
                .and().body("userStatus", equalTo(userObject.getUserStatus()));
    }

    @Test(priority = 3)
    public void deleteUserTest() {
        given()
                .contentType(ContentType.JSON)
                .delete("https://petstore.swagger.io/v2/user/" + userObject.getUsername())
                .then().assertThat()
                .statusCode(200)
                .and().assertThat()
                .body("message", equalTo(userObject.getUsername()));
    }

}
