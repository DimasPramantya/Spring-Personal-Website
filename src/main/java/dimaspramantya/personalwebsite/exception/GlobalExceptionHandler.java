package dimaspramantya.personalwebsite.exception;

import dimaspramantya.personalwebsite.response.ResponseObj;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({UserNotFoundException.class})
    public ResponseEntity<ResponseObj> userNotFoundHandler(UserNotFoundException exc){
        ResponseObj res = new ResponseObj();
        res.setMessage(exc.getMessage());
        res.setAdditionalProperty("timestamps", System.currentTimeMillis());
        res.setStatus(HttpStatus.NOT_FOUND.value());
        return new ResponseEntity<ResponseObj>(res, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({EmailAlreadyRegisteredException.class})
    public ResponseEntity<ResponseObj> emailAlreadyExistedHandler(EmailAlreadyRegisteredException exc){
        ResponseObj res = new ResponseObj();
        res.setMessage(exc.getMessage());
        res.setAdditionalProperty("timestamps", System.currentTimeMillis());
        res.setStatus(HttpStatus.CONFLICT.value());
        System.out.println(res);
        return new ResponseEntity<ResponseObj>(res, HttpStatus.CONFLICT);
    }

    @ExceptionHandler({RuntimeException.class})
    public ResponseEntity<ResponseObj> commonExceptionHandlder(RuntimeException exc){
        ResponseObj res = new ResponseObj();
        res.setMessage(exc.getMessage());
        res.setAdditionalProperty("timestamps", System.currentTimeMillis());
        res.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        return new ResponseEntity<ResponseObj>(res, HttpStatus.INTERNAL_SERVER_ERROR);
    }




}
