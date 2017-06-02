package aww.yeah;

import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class TwoHundredController implements ErrorController {

    @RequestMapping("/error")
    @ResponseStatus(HttpStatus.OK)
    public Map<String, Object> respondToAnything(){
        return new HashMap<String, Object>(){{put("success", "Aww Yeah");}};
    }

@ExceptionHandler(java.net.ProtocolException.class)
@ResponseStatus(HttpStatus.OK)
public Map<String, Object> handleExceptions(){
    return new HashMap<String, Object>(){{put("success", "Aww Yeah");}};
}
    @Override
    public String getErrorPath() {
        return "/error";
    }
}
