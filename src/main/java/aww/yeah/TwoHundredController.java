package aww.yeah;

import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
            return ResponseEntity.ok()
                    .contentType(MediaType.IMAGE_GIF)
                    .body(new InputStreamResource(ClassLoader.getSystemResourceAsStream("aww_yeah.gif")));
        }
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body("{\"success\":\"Aww Yeah\"}");
    }

    @Override
    public String getErrorPath() {
        return "/error";
    }
}
