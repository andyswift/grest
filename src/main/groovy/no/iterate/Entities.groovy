package no.iterate

/**
 * Represents the available entities in the REST API we are consuming.
 */
enum Entities {

    employees("employees")

    //required because this gets treated as a NamedParameter and the entity is expected
    def entity

    Entities(entity){
        this.entity = entity
    }
}
