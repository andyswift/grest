package no.iterate

def binding= new Binding([
        robot: new Robot(),
        *: Direction.values().collectEntries({
                    [(it.name()): it]
                })
])

def shell= new GroovyShell(binding)
shell.evaluate(
        new File("test.groovy")
)