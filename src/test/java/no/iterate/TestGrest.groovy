package no.iterate

import spock.lang.Specification
import static no.iterate.Entities.employees

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

    def "search for employee by id result is the return value from the closure" () {
        expect:
        grest.GET( employees: 1 ,{it.id}) == 1
    }

    def "search for employee by id result is an employee" () {
        given:
        Employee employee = new Employee(id:1, firstName: "James")
        expect:
        grest.GET( employees: 1 ,{new Employee(it)}) == employee
    }

    def "Possible to make a GET request on a String" () {
        String.metaClass.GET = {
            grest.GET(delegate)
        }
        expect:
        "/employees/1".GET().firstName == "James"
    }

    def "Possible to make a GET request on a String and map the response to an Object" () {

        //Adds a GET method to all Strings
        //which will perform a HTTP request to the path specified by the String
        String.metaClass.GET = { Closure c ->
            grest.GET(delegate,c)
        }

        given:
        Employee e = "/employees/1".GET {
            new Employee(it)
        }

        expect:
        e.firstName == "James"
    }

}
