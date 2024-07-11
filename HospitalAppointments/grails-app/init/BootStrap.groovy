package hospitalappointments

class BootStrap {

    def init = { servletContext ->
        def doctor1 = new Doctor(nombre: 'Juan', apellidoPaterno: 'Perez', apellidoMaterno: 'Gomez', especialidad: 'Cardiología').save(failOnError: true)
        def doctor2 = new Doctor(nombre: 'Ana', apellidoPaterno: 'Martinez', apellidoMaterno: 'Lopez', especialidad: 'Neurología').save(failOnError: true)
        
        def consultorio1 = new Consultorio(numero: 101, piso: 1).save(failOnError: true)
        def consultorio2 = new Consultorio(numero: 102, piso: 1).save(failOnError: true)
    }

    def destroy = {
    }
}
