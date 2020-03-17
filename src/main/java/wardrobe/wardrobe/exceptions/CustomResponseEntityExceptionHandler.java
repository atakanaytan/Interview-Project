package wardrobe.wardrobe.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


@ControllerAdvice
@RestController
public class CustomResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler
    public final ResponseEntity<Object> handleCategoryNameException(CategoryNameException ex, WebRequest request) {

        CategoryNameExceptionResponse exceptionResponse = new CategoryNameExceptionResponse(ex.getMessage());

        return new ResponseEntity(exceptionResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public final ResponseEntity<Object> handleProductNotFoundException(ProductNotFoundException ex, WebRequest request) {

        ProductNotFoundExceptionResponse exceptionResponse = new ProductNotFoundExceptionResponse(ex.getMessage());

        return new ResponseEntity(exceptionResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public final ResponseEntity<Object> handleCategoryNotFoundException(CategoryNotFoundException ex, WebRequest request) {

        CategoryNotFoundExceptionResponse exceptionResponse = new CategoryNotFoundExceptionResponse(ex.getMessage());

        return new ResponseEntity(exceptionResponse, HttpStatus.BAD_REQUEST);
    }
}
