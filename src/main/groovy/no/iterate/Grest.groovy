package no.iterate

import groovyx.net.http.RESTClient
import static groovyx.net.http.ContentType.JSON

/**
 * The DSL for Making Rest Calls
 */
class Grest {

    private final RESTClient restClient;
    private final String baseUri

    /**
     * Creates a new Instance of Grest with the given endpoint URL
     * @param baseUri e.g. localhost:3000
     */
    Grest(String baseUri) {
        this.baseUri = baseUri
        this.restClient = new RESTClient("http://" + baseUri)
    }

    /**
     * Performs a HTTP GET request to the given path relative to the
     * base URI.
     * @param path
     * @return the content of the http response
     */
    def GET(String path) {
        def result;
        GET(path,{result = it})
        result;
    }

    /**
     * Performs a HTTP GET request to the given path relative to the base URI.
     * Calls the provided closure with the content of the response.
     * @param path
     * @param closure
     * @return
     */
    def GET(String path , Closure closure) {
        def result;
        restClient.get(path:path){ response, content ->
            result = closure.call(content)
        }
        result;
    }

    /**
     * Performs a HTTP GET request with the given params.
     * @param params An iterable object containing key value parameters.
     * @return the content of the response
     */
    def GET(params) {
        def result;
        GET(params, {result = it})
        result;
    }

    /**
     * Performs a HTTP GET request with the given params.
     * Calls the provided closure with the content of the response.
     *
     * @param params An iterable object containing key value parameters.
     * @param closure An iterable object containing key value parameters.
     * @return the content of the response
     */
    def GET(params , Closure closure) {
        if(params.entity) {
            GET(params.entity,closure)
        }
        else {
            String url = params.collect({
                it.key + "/" + it.value
            }).join()
            GET(url,closure)
        }
    }
}
