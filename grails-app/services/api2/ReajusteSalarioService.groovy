package api2

import api2.commands.ReajusteSalarioCommand
import api2.exceptions.ValidationException
import api2.utils.ErrorMessageUtils
import api2.utils.ReajusteSalarioUtils
import grails.gorm.transactions.Transactional

@Transactional
class ReajusteSalarioService {
    ArrayList<LinkedHashMap> list() {
        ArrayList<LinkedHashMap> response = []
        ArrayList<ReajusteSalario> reajusteSalarios = ReajusteSalario.createCriteria().list {}

        for (ReajusteSalario reajusteSalario : reajusteSalarios) {
            response << ReajusteSalarioUtils.formatResponse(reajusteSalario)
        }

        return response
    }

    LinkedHashMap get(Long id) {
        ReajusteSalario reajusteSalario = ReajusteSalario.get(id)
        if (!reajusteSalario) throw new Exception(ErrorMessageUtils.getMessage("reajusteSalario.not.found"))

        return ReajusteSalarioUtils.formatResponse(reajusteSalario)
    }

    LinkedHashMap save(ReajusteSalarioCommand command) {
        Funcionario funcionario = Funcionario.get(command.funcionarioId)
        if (!funcionario) throw new Exception(ErrorMessageUtils.getMessage("funcionario.not.found"))

        if (command.dataReajuste && !ReajusteSalarioUtils.isValidDataReajuste(command.dataReajuste)) {
            throw new Exception(ErrorMessageUtils.getMessage("dataReajuste.invalid.format"))
        }

        if (command.valorSalario?.scale > 2 || command.valorSalario?.precision > 6) {
            throw new Exception(ErrorMessageUtils.getMessage("valorSalario.invalid.precision"))
        }

        ReajusteSalario reajusteSalario = new ReajusteSalario(
            dataReajuste: command.dataReajuste ? ReajusteSalarioUtils.parseDataReajuste(command.dataReajuste) : null,
            funcionario: funcionario,
            valorSalario: command.valorSalario
        )

        if (!reajusteSalario.validate()) throw new ValidationException(reajusteSalario.errors)

        reajusteSalario.save(flush: true)
        return ReajusteSalarioUtils.formatResponse(reajusteSalario)
    }

    void update(ReajusteSalarioCommand command) {
        ReajusteSalario reajusteSalario = ReajusteSalario.get(command.id)
        if (!reajusteSalario) throw new Exception(ErrorMessageUtils.getMessage("reajusteSalario.not.found"))

        if (command.funcionarioId) {
            Funcionario funcionario = Funcionario.get(command.funcionarioId)
            if (!funcionario) throw new Exception(ErrorMessageUtils.getMessage("funcionario.not.found"))

            reajusteSalario.funcionario = funcionario
        }

        if (command.dataReajuste) {
            if (!ReajusteSalarioUtils.isValidDataReajuste(command.dataReajuste)) {
                throw new Exception(ErrorMessageUtils.getMessage("dataReajuste.invalid.format"))
            }

            reajusteSalario.dataReajuste = ReajusteSalarioUtils.parseDataReajuste(command.dataReajuste)
        }

        if (command.valorSalario) {
            if (command.valorSalario?.scale > 2 || command.valorSalario?.precision > 6) {
                throw new Exception(ErrorMessageUtils.getMessage("valorSalario.invalid.precision"))
            }

            reajusteSalario.valorSalario = command.valorSalario
        }

        if (!reajusteSalario.validate()) throw new ValidationException(reajusteSalario.errors)

        reajusteSalario.save(flush: true)
    }

    void delete(Long id) {
        ReajusteSalario reajusteSalario = ReajusteSalario.get(id)
        if (!reajusteSalario) throw new Exception(ErrorMessageUtils.getMessage("reajusteSalario.not.found"))

        reajusteSalario.delete(flush: true)
    }
}
