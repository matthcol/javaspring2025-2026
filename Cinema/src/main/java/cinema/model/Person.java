package cinema.model;

import java.time.LocalDate;
import java.util.Objects;

// default: extends Object
public class Person {

    // fields / attributes / properties

    private String name; // default null
    private LocalDate birthdate;

    // constructor(s) : initialiser les champs

    // default constructor
    public Person() {
    }

    public Person(String name) {
        this.name = name;
    }

    // full constructor
    public Person(String name, LocalDate birthdate) {
        this.name = name;
        this.birthdate = birthdate;
    }

    // accessors : getters and setters

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(LocalDate birthdate) {
        this.birthdate = birthdate;
    }

    // toString

    @Override // from java.lang.Object
    public String toString() {
        final StringBuilder sb = new StringBuilder("Person{");
        sb.append("name='").append(name).append('\'');
        sb.append(", birthdate=").append(birthdate);
        sb.append('}');
        return sb.toString();
    }

//    @Override
//    public boolean equals(Object o) {
//        if (o == null || getClass() != o.getClass()) return false;
//        Person person = (Person) o;
//        return Objects.equals(name, person.name) && Objects.equals(birthdate, person.birthdate);
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(name, birthdate);
//    }
}
