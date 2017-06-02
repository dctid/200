package aww.yeah;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TwoHundredControllerTest {

    private final static char[] CHARS = IntStream.rangeClosed('A', 'Z')
            .mapToObj(c ->  "" + (char)(c + 32) + (char) c).collect(Collectors.joining()).toCharArray();

    @Autowired
    private TestRestTemplate restTemplate;

    private final Random random = new Random();

    @Test
    public void twoHundredApplication_shouldRespondToRandomGetRequests_withStatusOk() throws Exception {

        for(int i=0; i<20;i++) {
            String randomPath = genRandomString();
            ResponseEntity<String> response = this.restTemplate.getForEntity("/" + randomPath, String.class, "pa");

            assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
            assertThat(response.getBody()).isEqualTo("{\"success\":\"Aww Yeah\"}");
        }
    }

    @Test
    public void twoHundredApplication_shouldRespondToRandomGetRequestsWithUriVars_withStatusOk() throws Exception {

        for(int i=0; i<20;i++) {
            String randomPath = genRandomString();
            ResponseEntity<String> response = this.restTemplate.getForEntity("/" + randomPath + "?param1={param1}&param2={param2}", String.class, genRandomString(), genRandomString());

            assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
            assertThat(response.getBody()).isEqualTo("{\"success\":\"Aww Yeah\"}");
        }
    }

    @Test
    public void twoHundredApplication_shouldRespondToRandomPostWithUriVarRequests_withStatusOk() throws Exception {

        for(int i=0; i<20;i++) {
            String randomPath = genRandomString();
            ResponseEntity<String> response = this.restTemplate.postForEntity("/" + randomPath + "?param1={param1}&param2={param2}",null, String.class, genRandomString(), genRandomString());

            assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
            assertThat(response.getBody()).isEqualTo("{\"success\":\"Aww Yeah\"}");
        }
    }

    @Test
    public void twoHundredApplication_shouldRespondToRandomPostWithFormDataRequests_withStatusOk() throws Exception {

        for(int i=0; i<20;i++) {
            String randomPath = genRandomString();
            MultiValueMap<String, String> requestParams = new LinkedMultiValueMap<String, String>(){{
               put(genRandomString(), asList(genRandomString()));
               put(genRandomString(), asList(genRandomString()));
            }};
            ResponseEntity<String> response = this.restTemplate.postForEntity("/" + randomPath, requestParams, String.class);

            assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
            assertThat(response.getBody()).isEqualTo("{\"success\":\"Aww Yeah\"}");
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