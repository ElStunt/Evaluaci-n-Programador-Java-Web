package hospitalappointments

class Consultorio {
    int numero
    int piso

    static constraints = {
        numero blank: false
        piso blank: false
    }
}
