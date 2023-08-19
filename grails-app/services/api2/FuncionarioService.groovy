package api2

import api2.commands.FuncionarioCommand
import api2.exceptions.ValidationException
import api2.utils.ErrorMessageUtils
import grails.gorm.transactions.Transactional

@Transactional
class FuncionarioService {
    ArrayList<Funcionario> list() { Funcionario.createCriteria().list {} as ArrayList<Funcionario> }

    Funcionario get(Long id) {
        Funcionario funcionario = Funcionario.get(id)
        if (!funcionario) throw new Exception(ErrorMessageUtils.getMessage("funcionario.not.found"))

        return funcionario
    }

    Funcionario save(FuncionarioCommand command) {
        Cidade cidade = Cidade.get(command.cidadeId)
        if (!cidade) throw new Exception(ErrorMessageUtils.getMessage("cidade.not.found"))

        Funcionario funcionario = new Funcionario(nome: command.nome, cidade: cidade)
        if (!funcionario.validate()) throw new ValidationException(funcionario.errors)

        funcionario.save(flush: true)
        return funcionario
    }

    void update(FuncionarioCommand command) {
        Funcionario funcionario = Funcionario.get(command.id)
        if (!funcionario) throw new Exception(ErrorMessageUtils.getMessage("funcionario.not.found"))

        if (command.cidadeId) {
            Cidade cidade = Cidade.get(command.cidadeId)
            if (!cidade) throw new Exception(ErrorMessageUtils.getMessage("cidade.not.found"))

            funcionario.cidade = cidade
        }

        funcionario.nome = command.nome
        if (!funcionario.validate()) throw new ValidationException(funcionario.errors)

        funcionario.save(flush: true)
    }

    void delete(Long id) {
        Funcionario funcionario = Funcionario.get(id)
        if (!funcionario) throw new Exception(ErrorMessageUtils.getMessage("funcionario.not.found"))
        if (ReajusteSalario.findByFuncionario(funcionario)) throw new Exception(ErrorMessageUtils.getMessage("child.record.found"))

        funcionario.delete(flush: true)
    }
}
