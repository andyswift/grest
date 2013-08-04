import no.iterate.Employee

at "localhost:3000"

def people = get employees

//magic!
println "Found $people.firstName"

def person = get employees : 1
def person2 = get("/employees/1")

assert person.firstName == person2.firstName

String firstName = get employees : 1, {it.firstName}
println "Found $firstName"

Closure createNewEmployee = {new Employee(it)}
Employee employee = get employees : 1, createNewEmployee
println employee
//What more can we do?