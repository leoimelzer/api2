package api2

import api2.command.CidadeCommand
import api2.command.FuncionarioCommand

class BootStrap {
    CidadeService cidadeService = new CidadeService()
    FuncionarioService funcionarioService = new FuncionarioService()

    def init = { servletContext ->
        cidadeService.save(new CidadeCommand(id: 1, nome: "Sapiranga"))
        cidadeService.save(new CidadeCommand(id: 2, nome: "Porto Alegre"))
        cidadeService.save(new CidadeCommand(id: 3, nome: "Canoas"))

        funcionarioService.save(new FuncionarioCommand(id: 1, nome: "Joey Ramone", cidadeId: 2))
        funcionarioService.save(new FuncionarioCommand(id: 2, nome: "Scott Pilgrim", cidadeId: 3))
    }
    def destroy = {
    }
}
