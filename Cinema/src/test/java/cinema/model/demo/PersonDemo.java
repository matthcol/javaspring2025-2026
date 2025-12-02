package cinema.model.demo;

import cinema.adapter.PersonCsvAdapter;
import cinema.model.Person;
import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReaderBuilder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import com.opencsv.CSVReader;
import utils.CsvTools;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.text.MessageFormat;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import static org.junit.jupiter.api.Assertions.*;

class PersonDemo {

    @Test
    void demoNullReference(){
        // variable person : reference objet du type (classe) Person
        Person person; // variable non initialisé
        person = null; // reference nulle
        System.out.println(person);
        Assertions.assertThrows(
                NullPointerException.class,
                () -> System.out.println(person.toString())
        ); // java.lang.NullPointerException
    }

    @Test
    void demoDefaultConstructorSetterGetter(){
        Person person = new Person();
        System.out.println(person);

        person.setName("Zendaya");
        person.setBirthdate(LocalDate.of(1996,9,1));

        System.out.println("Name: " + person.getName());
        System.out.println("Birthdate: " + person.getBirthdate());
    }

    @Test
    void demo(){
        var person = new Person("Zendaya", LocalDate.of(1996,9,1));
        System.out.println(person);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/persons.tsv", delimiter = '\t', useHeadersInDisplayName = true)
    void demoPersonUnitFromCsv(int id, String name, LocalDate birthdate){
        System.out.println(MessageFormat.format("#{0} - {1} ({2})",
                id,
                name,
                birthdate
        ));
    }

    @Test
    void demoPersonCsvFor() throws IOException {
        Reader reader = new FileReader("src/test/resources/persons.tsv");
        CSVReader csvReader = new CSVReaderBuilder(reader)
                .withSkipLines(1)
                .withCSVParser(
                        new CSVParserBuilder()
                                .withSeparator('\t')
                                .build()
                )
                .build();
        int cptLine = 0;
        for (String[] line: csvReader){
            System.out.println(Arrays.toString(line));
            if (++cptLine >= 10) break;
        }
        csvReader.close();
        reader.close();
        // TODO: utiliser try .. catch .. finally (Java 1 à 6) ou try-with-resources (Java 7)
    }

    @Test
    void demoPersonCsvStream() throws IOException {
        try (
            Reader reader = new FileReader("src/test/resources/persons.tsv");
            CSVReader csvReader = new CSVReaderBuilder(reader)
                    .withSkipLines(1)
                    .withCSVParser(
                            new CSVParserBuilder()
                                    .withSeparator('\t')
                                    .build()
                    )
                    .build()
        ) {
            StreamSupport.stream(
                            Spliterators.spliteratorUnknownSize(
                                    csvReader.iterator(),
                                    Spliterator.ORDERED
                            ),
                            false
                    )
                    .limit(10)
                    .forEach(line -> System.out.println(Arrays.toString(line)));
        } // auto : try => csvReader.close() puis reader.close();
    }

    @Test
    void demoPersonStream2() throws IOException {
        try (
            Stream<String[]> stream = CsvTools.streamFromCsv("src/test/resources/persons.tsv", '\n', '"', 1)
        ) {
            stream.limit(10)
                    .peek(line -> System.out.println(Arrays.toString(line)))
                    .map(PersonCsvAdapter::personFromCsvLine)
                    .forEach(System.out::println);
        } // auto close
    }

}