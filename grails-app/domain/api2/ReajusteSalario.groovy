package api2

import java.time.LocalDate

class ReajusteSalario {
    Long id
    LocalDate dataReajuste
    BigDecimal valorSalario

    static belongsTo = [funcionario: Funcionario]

    static mapping = {
        version false
    }

    static constraints = {
        id generator: 'increment', primary: true
        dataReajuste nullable: false
        valorSalario nullable: false, precision: 6,  scale: 2
        funcionario nullable: false
    }
}
