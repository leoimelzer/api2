package api2

import api2.command.FuncionarioCommand
import api2.exception.ValidationException
import grails.gorm.transactions.Transactional
import org.springframework.context.MessageSource

@Transactional
class FuncionarioService {
    MessageSource messageSource
    Locale locale = Locale.getDefault()

    Object list() { Funcionario.createCriteria().list {} }

    Funcionario get(Long id) {
        Funcionario funcionario = Funcionario.get(id)

        if (!funcionario) throw new Exception(messageSource.getMessage("funcionario.not.found", null, locale))

        return funcionario
    }

    Funcionario save(FuncionarioCommand command) {
        Cidade cidade = Cidade.get(command.cidadeId)
        if (!cidade) throw new Exception(messageSource.getMessage("cidade.not.found", null, locale))

        Funcionario funcionario = new Funcionario(nome: command.nome, cidade: cidade)
        if (!funcionario.validate()) throw new ValidationException(funcionario.errors)

        funcionario.save(flush: true)
        return funcionario
    }

    void update(FuncionarioCommand command) {
        Funcionario funcionario = Funcionario.get(command.id)
        if (!funcionario) throw new Exception(messageSource.getMessage("funcionario.not.found", null, locale))

        if (command.cidadeId) {
            Cidade cidade = Cidade.get(command.cidadeId)
            if (!cidade) throw new Exception(messageSource.getMessage("cidade.not.found", null, locale))

            funcionario.cidade = cidade
        }

        funcionario.nome = command.nome

        if (!funcionario.validate()) throw new ValidationException(funcionario.errors)
        funcionario.save(flush: true)
    }

    void delete(Long id) {
        Funcionario funcionario = Funcionario.get(id)

        if (!funcionario) throw new Exception(messageSource.getMessage("funcionario.not.found", null, locale))
        funcionario.delete(flush: true)
    }
}
