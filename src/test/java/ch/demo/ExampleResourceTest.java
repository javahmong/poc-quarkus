package ch.demo;

import ch.demo.entity.Employee;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.common.mapper.TypeRef;
import org.junit.jupiter.api.Test;

import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
public class ExampleResourceTest {

    @Test
    public void shouldGetEmployees() {
        String employees = given().header(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON + ";charset=UTF-8")
            .when().get("/employee/1")
            .then()
            .statusCode(200)
            .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON + ";charset=UTF-8")
            .extract().body().asPrettyString();

        System.out.println(employees);
    }

}