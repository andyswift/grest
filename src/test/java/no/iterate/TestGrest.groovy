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
        1..12 == grest.GET(employees) { it.id }
    }

    def "search for employee by id" () {
        expect:
        grest.GET( employees: 1 ).firstName == "James"
    }

    def "search for employee by id with closure" () {
        expect:
        grest.GET( employees: 1 ) { it.firstName } == "James"
    }

    def "search for employee by id result is the content of the response" () {
        expect:
        grest.GET( employees: 1 ,{it.id}) == 1
    }

    def "How about on the String" () {
        String.metaClass.GET = {
            grest.GET(delegate)
        }
        expect:
        "/employees/1".GET().id == 1
    }

    def "How about on the String with a closure" () {
        String.metaClass.GET = {Closure c ->
            grest.GET(delegate,c)
        }

        expect:
        "/employees/1".GET{it.id} == 1
    }

}
