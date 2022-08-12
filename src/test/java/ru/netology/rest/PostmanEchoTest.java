package ru.netology.rest;

import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

import org.json.JSONObject;


public class PostmanEchoTest {

    @Test
    void shouldSendRequestPostTest() {
        // Given - When - Then
        // Предусловия
        given()
                .baseUri("https://postman-echo.com")
                .body("Test") // отправляемые данные (заголовки и query можно выставлять аналогично)
                // Выполняемые действия
                .when()
                .post("/post")
                // Проверки
                .then()
                .statusCode(200)
                .body("data", equalTo("Test"));
    }

    //пробуем вернуть POST-запрос с объектом
    @Test
    void shouldSendRequestPostWithParam() {
        JSONObject requestBody = new JSONObject()
                .put("name", "test name")
                .put("age", 18)
                .put("hobby", "sport");

        given()
                .baseUri("https://postman-echo.com")
                .body(requestBody.toString())
                .when()
                .post("/post")
                .then()
                .statusCode(200)
                .body("data", containsString("test name"));
    }
}
