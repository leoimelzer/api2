package api2

import api2.commands.FuncionarioCommand
import api2.exceptions.ControllerExceptionHandler
import org.springframework.http.HttpStatus

class FuncionarioController implements ControllerExceptionHandler {
    static responseFormats = ['json']
    static defaultAction = 'get'
    static allowedMethods = [
            list: 'GET',
            get: 'GET',
            save: 'POST',
            update: 'PUT',
            delete: 'DELETE'
    ]

    FuncionarioService funcionarioService = new FuncionarioService()

    Object list() {
        ArrayList<Funcionario> data = funcionarioService.list()
        respond([success: true, data: data], status: HttpStatus.OK)
    }

    Object get(Long id) {
        Funcionario data = funcionarioService.get(id)
        respond([success: true, data: data], status: HttpStatus.OK)
    }

    Object save(FuncionarioCommand funcionario) {
        Funcionario data = funcionarioService.save(funcionario)
        respond([success: true, data: data], status: HttpStatus.CREATED)
    }

    Object update(Long id, FuncionarioCommand funcionario) {
        funcionario.id = id
        funcionarioService.update(funcionario)
        respond([:], status: HttpStatus.NO_CONTENT)
    }

    Object delete(Long id) {
        funcionarioService.delete(id)
        respond([:], status: HttpStatus.NO_CONTENT)
    }
}
