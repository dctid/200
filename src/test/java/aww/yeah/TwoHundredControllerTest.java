package aww.yeah;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Random;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TwoHundredControllerTest {

    private TwoHundredController twoHundredController;

    @Mock
    private Random random;

    @Before
    public void setUp() throws Exception {
        twoHundredController = new TwoHundredController(random);
    }

    @Test
    public void respondToAnything_shouldReturnJson_whenRandomIsNotZero() throws Exception {
        when(random.nextInt(anyInt())).thenReturn(1);
        ResponseEntity responseEntity = twoHundredController.respondToAnything();

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isEqualTo("{\"success\":\"Aww Yeah\"}");
    }

    @Test
    public void respondToAnything_shouldReturnGif_whenRandomIsZero() throws Exception {
        when(random.nextInt(anyInt())).thenReturn(0);
        ResponseEntity responseEntity = twoHundredController.respondToAnything();

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(((InputStreamResource)responseEntity.getBody()).getInputStream())
                .hasSameContentAs(new InputStreamResource(ClassLoader.getSystemResourceAsStream("aww_yeah.gif")).getInputStream());
    }

    @Test
    public void getErrorPath_shouldReturn_slashError() throws Exception {
        assertThat(twoHundredController.getErrorPath()).isEqualTo("/error");
    }
}