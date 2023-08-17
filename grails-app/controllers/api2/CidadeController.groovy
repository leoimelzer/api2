package api2

import org.springframework.validation.FieldError

class CidadeController {
    def messageSource

	static responseFormats = ['json']
    static allowedMethods = [
            list: 'GET',
            get: 'GET',
            save: 'POST',
            update: 'PUT',
            delete: 'DELETE'
    ]

    CidadeService cidadeService = new CidadeService()
	
    Object list() {
        List<Cidade> data = cidadeService.list()
        respond([success: true, data: data], status: 200)
    }

    Object get(Long id) {
        Cidade data = cidadeService.get(id)
        respond([success: true, data: data], status: 200)
    }

    Object save(CidadeCommand command) {
        Cidade data = cidadeService.save(command)
        respond([success: true, data: data], status: 201)
    }

    Object update() {
        Long id = Long.parseLong(params.id)
        String nome = request.JSON.nome
        cidadeService.update(new CidadeCommand(id: id, nome: nome))
        respond([], status: 204)
    }

    Object delete(Long id) {
        cidadeService.delete(id)
        respond([], status: 204)
    }

    Object handleValidationException(ValidationException ex) {
        Locale locale = Locale.getDefault()

        Integer errorCount = ex.errors.getFieldErrorCount()
        List errors = []

        ex.errors.fieldErrors.each { FieldError fieldError ->
            LinkedHashMap error = [:]
            error.field = fieldError.field
            error.message = messageSource.getMessage(fieldError, locale)
            errors.add(error)
        }

        respond([success: false, errorCount: errorCount, errors: errors], status: 400)
    }

    Object handleException(Exception ex) {
        respond([success: false, message: ex.getMessage()], status: 400)
    }
}
