package com.ch.core.utils;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

import java.util.LinkedHashMap;
import java.util.LinkedList;

@Component
public class HelperUtil {

    public static LinkedList<LinkedHashMap<String, String>> refineErrors(Errors errors) {
        LinkedList errorList = new LinkedList<LinkedHashMap<String, String>>();
        errors.getFieldErrors().forEach(e-> {
            LinkedHashMap<String, String> error = new LinkedHashMap<>();
            error.put("field", e.getField());
            error.put("message", e.getDefaultMessage());
            errorList.push(error);
        });
        return errorList;
    }
}
