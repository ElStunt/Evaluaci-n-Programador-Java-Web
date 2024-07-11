package hospitalappointments

import grails.validation.ValidationException
import java.time.LocalDateTime

class CitaController {
    static scaffold = Cita

    def create() {
        respond new Cita(params)
    }

    def save(Cita cita) {
        if (cita == null) {
            notFound()
            return
        }

        // Reglas de validación
        if (Cita.countByConsultorioAndHorarioConsulta(cita.consultorio, cita.horarioConsulta) > 0) {
            flash.message = "El consultorio ya está ocupado a esa hora"
            render view: 'create', model: [cita: cita]
            return
        }
        
        if (Cita.countByDoctorAndHorarioConsulta(cita.doctor, cita.horarioConsulta) > 0) {
            flash.message = "El doctor ya tiene una cita a esa hora"
            render view: 'create', model: [cita: cita]
            return
        }

        if (Cita.countByNombrePacienteAndHorarioConsultaBetween(cita.nombrePaciente, cita.horarioConsulta.minusHours(2), cita.horarioConsulta.plusHours(2)) > 0) {
            flash.message = "El paciente no puede tener citas con menos de 2 horas de diferencia"
            render view: 'create', model: [cita: cita]
            return
        }

        if (Cita.countByDoctorAndHorarioConsultaBetween(cita.doctor, cita.horarioConsulta.toLocalDate().atStartOfDay(), cita.horarioConsulta.toLocalDate().atTime(23, 59)) >= 8) {
            flash.message = "El doctor no puede tener más de 8 citas en el día"
            render view: 'create', model: [cita: cita]
            return
        }

        try {
            cita.save(flush: true)
            flash.message = "Cita creada exitosamente"
            redirect action: "index"
        } catch (ValidationException e) {
            render view: 'create', model: [cita: cita]
        }
    }
}
