package api2

import api2.command.FuncionarioCommand
import api2.exception.ValidationException
import org.springframework.context.MessageSource
import org.springframework.http.HttpStatus
import org.springframework.validation.FieldError

class FuncionarioController {
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
    FuncionarioService funcionarioService = new FuncionarioService()

    Object list() {
        Object data = funcionarioService.list()
        respond([success: true, data: data], status: HttpStatus.OK)
    }

    Object get(Long id) {
        Funcionario data = funcionarioService.get(id)
        respond([success: true, data: data], status: HttpStatus.OK)
    }

    Object save(FuncionarioCommand command) {
        Funcionario data = funcionarioService.save(command)
        respond([success: true, data: data], status: HttpStatus.CREATED)
    }

    Object update() {
        Long id = Long.parseLong(params.id)
        Long cidadeId = request.JSON.cidadeId
        String nome = request.JSON.nome

        funcionarioService.update(new FuncionarioCommand(id: id, nome: nome, cidadeId: cidadeId))
        respond([:], status: HttpStatus.NO_CONTENT)
    }

    Object delete(Long id) {
        funcionarioService.delete(id)
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
