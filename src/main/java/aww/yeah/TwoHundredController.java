package aww.yeah;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Random;

@RestController
public class TwoHundredController implements ErrorController {

    private Random random;

    public TwoHundredController(Random random) {
        this.random = random;
    }

    @RequestMapping("/error")
    public ResponseEntity respondToAnything(){

        if(random.nextInt(5) == 0){
            try {
                Resource awwyeahGif = new ClassPathResource("aww_yeah.gif");
                return ResponseEntity.ok()
                        .contentType(MediaType.IMAGE_GIF)
                        .body(new InputStreamResource(awwyeahGif.getInputStream()));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body("{\"success\":\"Aww Yeah\"}");
    }

    public String getErrorPath() {
        return "/error";
    }
}
