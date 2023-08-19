package api2.commands

import grails.validation.Validateable

class ReajusteSalarioCommand implements Validateable {
    Long id
    String dataReajuste
    BigDecimal valorSalario
    Long funcionarioId
}
