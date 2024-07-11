package hospitalappointments

import java.time.LocalDateTime

class Cita {
    Consultorio consultorio
    Doctor doctor
    LocalDateTime horarioConsulta
    String nombrePaciente

    static constraints = {
        consultorio nullable: false
        doctor nullable: false
        horarioConsulta nullable: false
        nombrePaciente blank: false
    }
}
