package api2

class BootStrap {
    CidadeService cidadeService = new CidadeService()

    def init = { servletContext ->
        cidadeService.save(new CidadeCommand(id: 1, nome: "Sapiranga"))
        cidadeService.save(new CidadeCommand(id: 2, nome: "Gotham"))
        cidadeService.save(new CidadeCommand(id: 3, nome: "Manhattan"))
    }
    def destroy = {
    }
}
