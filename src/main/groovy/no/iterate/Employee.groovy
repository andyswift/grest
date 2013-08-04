package no.iterate

import groovy.transform.TupleConstructor

/**
 * Explain the responsibility of this class
 */
class Employee {
    def id
    def firstName

    def propertyMissing(String name) {
        println "TODO missing property $name"
        return null
    }

    def propertyMissing(String name, def arg) {
        println "TODO missing property $name"
        return null
    }

    boolean equals(o) {
        if (this.is(o)) return true
        if (getClass() != o.class) return false

        Employee employee = (Employee) o

        if (firstName != employee.firstName) return false
        if (id != employee.id) return false

        return true
    }

    int hashCode() {
        int result
        result = (id != null ? id.hashCode() : 0)
        result = 31 * result + (firstName != null ? firstName.hashCode() : 0)
        return result
    }

    @Override
    public java.lang.String toString() {
        return "Employee{" +
                "id=" + id +
                ", firstName=" + firstName +
                '}';
    }
}
