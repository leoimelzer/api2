package api2

import api2.commands.CidadeCommand
import api2.commands.FuncionarioCommand
import api2.commands.ReajusteSalarioCommand
import api2.utils.ReajusteSalarioUtils

import java.time.LocalDate

class BootStrap {
    CidadeService cidadeService = new CidadeService()
    FuncionarioService funcionarioService = new FuncionarioService()
    ReajusteSalarioService reajusteSalarioService = new ReajusteSalarioService()

    def init = { servletContext ->
        cidadeService.save(new CidadeCommand(id: 1, nome: "Sapiranga"))
        cidadeService.save(new CidadeCommand(id: 2, nome: "Porto Alegre"))
        cidadeService.save(new CidadeCommand(id: 3, nome: "Canoas"))

        funcionarioService.save(new FuncionarioCommand(id: 1, nome: "Joey Ramone", cidadeId: 2))
        funcionarioService.save(new FuncionarioCommand(id: 2, nome: "Scott Pilgrim", cidadeId: 3))

        reajusteSalarioService.save(new ReajusteSalarioCommand(
            id: 1,
            valorSalario: 4.0,
            dataReajuste: ReajusteSalarioUtils.formatDataReajuste(LocalDate.now()),
            funcionarioId: 1)
        )
    }

    def destroy = {
    }
}
