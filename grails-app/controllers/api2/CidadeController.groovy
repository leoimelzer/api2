package api2

import api2.commands.CidadeCommand
import api2.exceptions.ControllerExceptionHandler
import org.springframework.http.HttpStatus

class CidadeController implements ControllerExceptionHandler {
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
}
