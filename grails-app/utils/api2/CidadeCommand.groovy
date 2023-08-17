package api2

import grails.validation.Validateable

class CidadeCommand implements Validateable {
    Long id
    String nome

    static constraints = {
        id generator: 'increment', primary: true
        nome nullable: false, blank: false, unique: true, maxSize: 50
    }
}
