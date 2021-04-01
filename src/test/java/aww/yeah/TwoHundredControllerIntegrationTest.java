package aww.yeah;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.response.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static com.jayway.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TwoHundredControllerIntegrationTest {

    private final static char[] CHARS = IntStream.rangeClosed('A', 'Z')
            .mapToObj(c ->  "" + (char)(c + 32) + (char) c).collect(Collectors.joining()).toCharArray();

    @LocalServerPort
    private int port;

    private final Random random = new Random();

    @BeforeEach
    public void setUp() throws Exception {
        RestAssured.port = port;
    }

    @Test
    public void twoHundredApplication_shouldRespondToRandomGetRequests_withStatusOk() throws Exception {
        for(int i=0; i<20;i++) {

            Response response = given()
                    .get(genRandomString())
                    .thenReturn();

            assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK.value());

            if(MediaType.APPLICATION_JSON_VALUE.equals(response.getContentType())) {
                assertThat(response.getBody().asString()).isEqualTo("{\"success\":\"Aww Yeah\"}");
            } else {
                assertThat(response.getBody().asInputStream())
                        .hasSameContentAs(new InputStreamResource(ClassLoader.getSystemResourceAsStream("aww_yeah.gif")).getInputStream());
            }
        }
    }

    @Test
    public void twoHundredApplication_shouldRespondToRandomGetRequestsWithUriVars_withStatusOk() throws Exception {

        for(int i=0; i<20;i++) {
            Response response = given()
                    .param(genRandomString(), genRandomString())
                    .param(genRandomString(), genRandomString())
                    .get(genRandomString())
                    .thenReturn();

            assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK.value());
            if(MediaType.APPLICATION_JSON_VALUE.equals(response.getContentType())) {
                assertThat(response.getBody().asString()).isEqualTo("{\"success\":\"Aww Yeah\"}");
            } else {
                assertThat(response.getBody().asInputStream())
                        .hasSameContentAs(new InputStreamResource(ClassLoader.getSystemResourceAsStream("aww_yeah.gif")).getInputStream());
            }
        }
    }

    @Test
    public void twoHundredApplication_shouldRespondToRandomPostWithUriVarRequests_withStatusOk() throws Exception {

        for(int i=0; i<20;i++) {

            Response response = given()
                    .queryParam(genRandomString(), genRandomString())
                    .queryParam(genRandomString(), genRandomString())
                    .post(genRandomString())
                    .thenReturn();

            assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK.value());
            if(MediaType.APPLICATION_JSON_VALUE.equals(response.getContentType())) {
                assertThat(response.getBody().asString()).isEqualTo("{\"success\":\"Aww Yeah\"}");
            } else {
                assertThat(response.getBody().asInputStream())
                        .hasSameContentAs(new InputStreamResource(ClassLoader.getSystemResourceAsStream("aww_yeah.gif")).getInputStream());
            }
        }
    }

    @Test
    public void twoHundredApplication_shouldRespondToRandomPostWithFormDataRequests_withStatusOk() throws Exception {

        for(int i=0; i<20;i++) {

            Response response = given()
                    .formParam(genRandomString(), genRandomString())
                    .formParam(genRandomString(), genRandomString())
                    .post(genRandomString())
                    .thenReturn();

            assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK.value());
            if(MediaType.APPLICATION_JSON_VALUE.equals(response.getContentType())) {
                assertThat(response.getBody().asString()).isEqualTo("{\"success\":\"Aww Yeah\"}");
            } else {
                assertThat(response.getBody().asInputStream())
                        .hasSameContentAs(new InputStreamResource(ClassLoader.getSystemResourceAsStream("aww_yeah.gif")).getInputStream());
            }
        }
    }

    private String genRandomString() {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < random.nextInt( 15) + 5; i++) {
            builder.append(CHARS[random.nextInt(CHARS.length)]);
        }
        return builder.toString();
    }
}
