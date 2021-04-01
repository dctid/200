package aww.yeah;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Random;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TwoHundredControllerTest {

    private TwoHundredController twoHundredController;

    @Mock
    private Random random;

    @BeforeEach
    public void setUp() throws Exception {
        twoHundredController = new TwoHundredController(random);
    }

    @Test
    void respondToAnything_shouldReturnJson_whenRandomIsNotZero() throws Exception {
        when(random.nextInt(anyInt())).thenReturn(1);
        ResponseEntity responseEntity = twoHundredController.respondToAnything();

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isEqualTo("{\"success\":\"Aww Yeah\"}");
    }

    @Test
    void respondToAnything_shouldReturnGif_whenRandomIsZero() throws Exception {
        when(random.nextInt(anyInt())).thenReturn(0);
        ResponseEntity responseEntity = twoHundredController.respondToAnything();

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(((InputStreamResource)responseEntity.getBody()).getInputStream())
                .hasSameContentAs(new InputStreamResource(ClassLoader.getSystemResourceAsStream("aww_yeah.gif")).getInputStream());
    }

    @Test
    void getErrorPath_shouldReturn_slashError() throws Exception {
        assertThat(twoHundredController.getErrorPath()).isEqualTo("/error");
    }


}
