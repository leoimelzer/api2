package api2

import api2.commands.CidadeCommand
import api2.exceptions.ValidationException
import api2.utils.ErrorMessageUtils
import grails.gorm.transactions.Transactional

@Transactional
class CidadeService {
    ArrayList<Cidade> list() { Cidade.createCriteria().list {} }

    Cidade get(Long id) {
        Cidade cidade = Cidade.get(id)
        if (!cidade) throw new Exception(ErrorMessageUtils.getMessage("cidade.not.found"))

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
        if (!cidade) throw new Exception(ErrorMessageUtils.getMessage("cidade.not.found"))

        cidade.nome = command.nome
        if (!cidade.validate()) throw new ValidationException(cidade.errors)

        cidade.save(flush: true)
    }

    void delete(Long id) {
        Cidade cidade = Cidade.get(id)

        if (!cidade) throw new Exception(ErrorMessageUtils.getMessage("cidade.not.found"))
        if (Funcionario.findByCidade(cidade)) throw new Exception(ErrorMessageUtils.getMessage("child.record.found"))

        cidade.delete(flush: true)
    }
}
