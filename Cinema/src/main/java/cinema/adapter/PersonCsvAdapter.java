package cinema.adapter;

import cinema.model.Person;

import java.time.LocalDate;

public class PersonCsvAdapter {

    public static Person personFromCsvLine(String[] line){
        String name = line[1];
        String birthdateStr = line[2];
        LocalDate birthdate = birthdateStr.isEmpty() ? null : LocalDate.parse(birthdateStr);
        return new Person(name, birthdate);
    }
}
