package no.iterate

import spock.lang.Specification

import static no.iterate.Entities.employees

/**
 * Tests the Groovy DSL for Rest aka Grest
 */
class JavaZoneDownload extends Specification {



    def "get sessions" () {

        File file= new File("buzz2013.txt")
        Grest grest = new Grest("jz13.java.no")

        grest.GET("/api/sessions").each {
            file.append(grest.GET(it.links.grep{ it.rel == 'details'}.uri[0] - "http://jz13.java.no").body)
            file.append("\n")
        }

        expect: 1 == 1
    }

}
