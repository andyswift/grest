import no.iterate.Entities
import no.iterate.Grest

def grest = new Grest("localhost:3000")

def binding= new Binding([
        at : {
            grest = new Grest(it)
        },
        get : grest.&GET,
        employees : Entities.employees
])



/*String.metaClass.GET = {
    grest.GET(delegate)
} */

def shell= new GroovyShell(binding)
shell.evaluate(
    new File("commands.groovy")
)
