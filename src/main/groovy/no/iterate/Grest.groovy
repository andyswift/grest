package no.iterate

import groovyx.net.http.RESTClient
import static groovyx.net.http.ContentType.JSON

/**
 * The DSL for Making Rest Calls
 */
class Grest {

    enum Entities {
        employees("employees")
        //required because this gets treated as a NamedParameter and the entity is expected
        def entity

        Entities(entity){
            this.entity = entity
        }
    }

    private final RESTClient restClient;
    private final String baseUri


    Grest(String baseUri) {
        this.baseUri = baseUri
        this.restClient = new RESTClient("http://" + baseUri)
    }

    def GET(String path ) {
        def result;
        GET(path,{result = it})
        result;
    }

    void GET(String path , Closure closure) {
        restClient.get(path:path){ response, content ->
            closure.call(content)
        }
    }

    def GET(params) {
        def result;
        GET(params, {result = it})
        result;
    }

    void GET(params , Closure closure) {
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
