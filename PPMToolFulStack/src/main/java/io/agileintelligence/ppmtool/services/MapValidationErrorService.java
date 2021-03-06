package io.agileintelligence.ppmtool.services;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.HashMap;
import java.util.Map;

@Service
public class MapValidationErrorService {

    public ResponseEntity<?> MapValidationService(BindingResult result) {
        // Check error
        if (result.hasErrors()) {
            Map<String, String> mapErrors = new HashMap<>();
            for (FieldError error: result.getFieldErrors()) {
                mapErrors.put(error.getField(), error.getDefaultMessage());
            }
            //Show errors
            return new ResponseEntity<Map<String, String>>(mapErrors, HttpStatus.BAD_REQUEST);
        }

        return null;
    }

}
