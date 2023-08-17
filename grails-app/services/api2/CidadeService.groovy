package api2

import api2.command.CidadeCommand
import api2.exception.ValidationException
import grails.gorm.transactions.Transactional
import org.springframework.context.MessageSource

@Transactional
class CidadeService {
    MessageSource messageSource

    Locale locale = Locale.getDefault()

    Object list() { Cidade.createCriteria().list {} }

    Cidade get(Long id) {
        Cidade cidade = Cidade.get(id)

        if (!cidade) throw new Exception(messageSource.getMessage("cidade.not.found", null, locale))

        return cidade
    }

    Cidade save(CidadeCommand command) {
        Cidade cidade = new Cidade(nome: command.nome)
        if (!cidade.validate()) throw new ValidationException(cidade.errors)

        cidade.save(flush: true)
        return cidade
    }

    void update(CidadeCommand command) {
        Cidade cidade = Cidade.get(command.id)
        if (!cidade) throw new Exception(messageSource.getMessage("cidade.not.found", null, locale))

        cidade.nome = command.nome

        if (!cidade.validate()) throw new ValidationException(cidade.errors)

        cidade.save(flush: true)
    }

    void delete(Long id) {
        Cidade cidade = Cidade.get(id)

        if (!cidade) throw new Exception(messageSource.getMessage("cidade.not.found", null, locale))

        cidade.delete(flush: true)
    }
}
