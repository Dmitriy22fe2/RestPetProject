package org.example.restpetproject.util;

import org.springframework.validation.BindingResult;

import java.util.stream.Collectors;

public class ErrorsUtil {
    public static String errorsJoin(BindingResult bindingResult) {
        String errorMessage = bindingResult.getFieldErrors()
                .stream()
                .map(error -> error.getField() + " : " + error.getDefaultMessage())
                .collect(Collectors.joining("; "));
        return errorMessage;
    }
}
