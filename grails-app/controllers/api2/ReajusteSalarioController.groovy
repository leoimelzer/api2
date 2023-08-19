package api2

import api2.commands.ReajusteSalarioCommand
import api2.exceptions.ValidationException
import api2.utils.ErrorMessageUtils
import org.springframework.context.MessageSource
import org.springframework.http.HttpStatus
import org.springframework.validation.FieldError

class ReajusteSalarioController {
    static responseFormats = ['json']
    static defaultAction = 'get'
    static allowedMethods = [
            list: 'GET',
            get: 'GET',
            save: 'POST',
            update: 'PUT',
            delete: 'DELETE'
    ]

    ReajusteSalarioService reajusteSalarioService = new ReajusteSalarioService()

    Object list() {
        ArrayList<LinkedHashMap> data = reajusteSalarioService.list()
        respond([success: true, data: data], status: HttpStatus.OK)
    }

    Object get(Long id) {
        LinkedHashMap data = reajusteSalarioService.get(id)
        respond([success: true, data: data], status: HttpStatus.OK)
    }

    Object save(ReajusteSalarioCommand reajusteSalario) {
        LinkedHashMap data = reajusteSalarioService.save(reajusteSalario)
        respond([success: true, data: data], status: HttpStatus.CREATED)
    }

    Object update(Long id, ReajusteSalarioCommand reajusteSalario) {
        reajusteSalario.id = id
        reajusteSalarioService.update(reajusteSalario)
        respond([:], status: HttpStatus.NO_CONTENT)
    }

    Object delete(Long id) {
        reajusteSalarioService.delete(id)
        respond([:], status: HttpStatus.NO_CONTENT)
    }

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
