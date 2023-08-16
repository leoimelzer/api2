package api2

class Cidade {
    Long id
    String nome

    static constraints = {
        id generator: 'increment', primary: true
        nome nullable: false, blank: false, unique: true, maxSize: 50
    }
}
