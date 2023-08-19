package api2

import api2.commands.CidadeCommand
import api2.exceptions.ValidationException
import api2.utils.ErrorMessageUtils
import org.springframework.http.HttpStatus
import org.springframework.validation.FieldError

class CidadeController {
	static responseFormats = ['json']
    static defaultAction = 'get'
    static allowedMethods = [
            list: 'GET',
            get: 'GET',
            save: 'POST',
            update: 'PUT',
            delete: 'DELETE'
    ]

    CidadeService cidadeService = new CidadeService()

    Object list() {
        ArrayList<Cidade> data = cidadeService.list()
        respond([success: true, data: data], status: HttpStatus.OK)
    }

    Object get(Long id) {
        Cidade data = cidadeService.get(id)
        respond([success: true, data: data], status: HttpStatus.OK)
    }

    Object save(CidadeCommand cidade) {
        Cidade data = cidadeService.save(cidade)
        respond([success: true, data: data], status: HttpStatus.CREATED)
    }

    Object update(Long id, CidadeCommand cidade) {
        cidade.id = id
        cidadeService.update(cidade)
        respond([:], status: HttpStatus.NO_CONTENT)
    }

    Object delete(Long id) {
        cidadeService.delete(id)
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
