package api2.command

import grails.validation.Validateable

class FuncionarioCommand implements Validateable {
    Long id
    String nome
    Long cidadeId
}
