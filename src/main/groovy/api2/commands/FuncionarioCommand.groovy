package api2.commands

import grails.validation.Validateable

class FuncionarioCommand implements Validateable {
    Long id
    String nome
    Long cidadeId
}
