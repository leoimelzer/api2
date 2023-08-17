package api2

import api2.command.CidadeCommand
import api2.exception.ValidationException
import org.springframework.context.MessageSource
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

    MessageSource messageSource
    CidadeService cidadeService = new CidadeService()
	
    Object list() {
        Object data = cidadeService.list()
        respond([success: true, data: data], status: HttpStatus.OK)
    }

    Object get(Long id) {
        Cidade data = cidadeService.get(id)
        respond([success: true, data: data], status: HttpStatus.OK)
    }

    Object save(CidadeCommand command) {
        Cidade data = cidadeService.save(command)
        respond([success: true, data: data], status: HttpStatus.CREATED)
    }

    Object update() {
        Long id = Long.parseLong(params.id)
        String nome = request.JSON.nome
        cidadeService.update(new CidadeCommand(id: id, nome: nome))
        respond([:], status: HttpStatus.NO_CONTENT)
    }

    Object delete(Long id) {
        cidadeService.delete(id)
        respond([:], status: HttpStatus.NO_CONTENT)
    }

    Object handleValidationException(ValidationException ex) {
        Locale locale = Locale.getDefault()
        List errors = []

        ex.errors.fieldErrors.each { FieldError fieldError ->
            LinkedHashMap error = [:]
            error.field = fieldError.field
            error.message = messageSource.getMessage(fieldError, locale)
            errors.add(error)
        }

        respond([success: false, errors: errors], status: HttpStatus.BAD_REQUEST)
    }

    Object handleException(Exception ex) {
        respond([success: false, message: ex.getMessage()], status: HttpStatus.BAD_REQUEST)
    }
}
