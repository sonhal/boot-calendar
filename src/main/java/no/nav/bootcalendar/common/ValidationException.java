package no.nav.bootcalendar.common;

import lombok.Getter;

import java.util.List;

@Getter
public class ValidationException extends Exception {

    private final List<String> errors;

    public ValidationException(List<String> errors) {
        this.errors = errors;
    }

}
