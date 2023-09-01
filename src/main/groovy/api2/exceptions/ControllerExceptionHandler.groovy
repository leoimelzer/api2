package api2.exceptions

import api2.utils.ErrorMessageUtils
import grails.artefact.controller.RestResponder
import org.springframework.http.HttpStatus
import org.springframework.validation.FieldError

trait ControllerExceptionHandler implements RestResponder {
    Object handleValidationException(ValidationException ex) {
        ArrayList errors = []

        ex.errors.fieldErrors.each { FieldError fieldError ->
            LinkedHashMap error = [:]
            error.field = fieldError.field
            error.message = ErrorMessageUtils.getMessage(fieldError.code)
            errors.add(error)
        }

        respond([success: false, errors: errors], status: HttpStatus.BAD_REQUEST)
    }

    Object handleException(Exception ex) {
        respond([success: false, message: ex.getMessage()], status: HttpStatus.BAD_REQUEST)
    }
}