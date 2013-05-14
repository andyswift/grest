package no.iterate

import spock.lang.Specification
import static no.iterate.Grest.Entities.employees

/**
 * Tests the Groovy DSL for Rest aka Grest
 */
class TestGrest extends Specification {

    private Grest grest = new Grest("localhost:3000")

    def "making a get request causes data to be fetched from a server" () {
        expect:
        grest.GET("/employees/1").firstName == "James"
    }

    def "fetch automatically based on an enum" () {

        expect:
        grest.GET(employees).id == 1..12
    }

    def "fetch automatically based on an enum with closure" () {
        expect:
        grest.GET(employees) { assert it.id == 1..12 }
    }

    def "search for employee by id" () {
        expect:
        grest.GET( employees: 1 ).firstName == "James"
    }

    def "search for employee by id with closure" () {
        expect:
        grest.GET( employees: 1 ) { assert it.firstName == "James" }
    }
}
