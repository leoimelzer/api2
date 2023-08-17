package api2

import org.springframework.validation.Errors

class ValidationException extends Exception {
    private Errors errors

    ValidationException(Errors errors) {
        super()
        this.errors = errors
    }

    Errors getErrors() { this.errors }
}
