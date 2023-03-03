package com.server.sharemenu.exception;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/exception")
public class ControllerException {

    @Autowired
    private ServiceException serviceException;

    @GetMapping(value = "/throw")
    public ResponseEntity<?> throwError(@RequestParam int i) throws ArithmeticException, CustomException {
        serviceException.thrower(i);
        return ResponseEntity.ok("");
    }
}
