package no.iterate

import groovyx.net.http.AsyncHTTPBuilder
import groovyx.net.http.HttpResponseDecorator
import groovyx.net.http.RESTClient
import spock.lang.Specification
import static groovyx.net.http.ContentType.HTML
import static groovyx.net.http.ContentType.JSON

/**
 * Explain the responsibility of this class
 */
class TestRestClient extends Specification {

    def "Rest client can make get requests to vg.no" () {
        def client = new RESTClient( 'http://localhost:3000' )
        def data;
        client.get (path:'/employees') { responseDecorator, content ->
            data = content
        }
        expect:
        data[0].id == 1
    }

    def "Async client makes requests async" () {
        def http = new AsyncHTTPBuilder(
                poolSize : 4,
                uri : 'http://localhost:3000',
                contentType : JSON )


        def result = http.get(path:'/employees/1') { resp, html ->
            println ' got async response!'
            return html
        }

        while ( ! result.done ) {
            println 'waiting...'
            Thread.sleep(2000)
        }

        expect:
        result.get().id == 1
        result.get().firstName == "James"
    }

}
