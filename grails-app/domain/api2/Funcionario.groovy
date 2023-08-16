package api2

class Funcionario {
    Long id
    String nome

    static belongsTo = [cidade: Cidade]

    static constraints = {
        id generator: 'increment', primary: true
        nome nullable: false, blank: false, maxSize: 50, unique: 'cidade'
        cidade nullable: false
    }
}
