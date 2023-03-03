package com.server.sharemenu.exception;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UsernameNotFoundException.class)
    @ResponseBody
    public ResponseEntity<Object> handleAllOtherErrors(UsernameNotFoundException exception) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .contentType(MediaType.APPLICATION_JSON)
                .body(exception.getMessage());
    }

    @ExceptionHandler(TokenTypeException.class)
    @ResponseBody
    public ResponseEntity<Object> handleAllOtherErrors(TokenTypeException exception) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .contentType(MediaType.APPLICATION_JSON)
                .body(exception.getMessage());
    }

    @ExceptionHandler(CustumAuthException.class)
    @ResponseBody
    public ResponseEntity<Object> handleAllOtherErrors(CustumAuthException exception) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .contentType(MediaType.APPLICATION_JSON)
                .body("Invalid password or email!");
    }

    /**
     * @ExceptionHandler(DataIntegrityViolationException.class)
     * @ResponseBody public ResponseEntity<Object> handleResetEmailAlreadySent() {
     * return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
     * .contentType(MediaType.APPLICATION_JSON)
     * .body("Password request for this email is already sent");
     * }
     */

    @ExceptionHandler(CustomException.class)
    @ResponseBody
    public ResponseEntity<Object> handleAllOtherErrors(CustomException exception) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .contentType(MediaType.APPLICATION_JSON)
                .body(exception.getMessage());
    }

    @ExceptionHandler(UserAlreadyExists.class)
    @ResponseBody
    public ResponseEntity<Object> handleAllOtherErrors(UserAlreadyExists exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .contentType(MediaType.APPLICATION_JSON)
                .body(exception.getMessage());
    }

    @ExceptionHandler(AlreadyExists.class)
    @ResponseBody
    public ResponseEntity<Object> handleAlreadyExists(AlreadyExists exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .contentType(MediaType.APPLICATION_JSON)
                .body("Record with unique field " + exception.getMessage() + " already exists");
    }

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseBody
    public ResponseEntity<Object> uniqueConstraintViolated(DataIntegrityViolationException exception) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body("Unique constraint violated!");

    }
}
