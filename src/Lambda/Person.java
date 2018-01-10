package Lambda;

import java.time.LocalDate;
import java.time.chrono.IsoChronology;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

public class Person {

    public Person(String name, Sex gender){
        this.name = name;
        this.gender = gender;
        this.emailAddress = "email address";

    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public Person(String name, LocalDate birth, Sex gender, String emailAddress){
        this.name = name;
        this.gender = gender;
        this.emailAddress = emailAddress;
        this.birthday = birth;

    }

    public enum Sex {
        MALE, FEMALE
    }

    String name;
    LocalDate birthday;
    Sex gender;
    String emailAddress;

    public int getAge() {
        return LocalDate.now().getYear() - birthday.getYear() + 1;
    }

    public void printPerson() {
        System.out.println("Name: " + name);
        System.out.println("Birthday: " + birthday.toString());
        System.out.println("Sex: " + gender);
        System.out.println("Email: " + emailAddress);


    }

    public Sex getGender() {
        return gender;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public static void printPersonOlderThan(List<Person> roster, int age) {
        for (Person p : roster) {
            if (p.getAge() >= age ) {
                p.printPerson();
            }
        }
    }

    public static void printPersonWithAgeRange(
            List<Person> roster, int low, int high) {
        for (Person p : roster) {
            if (low <= p.getAge() && p.getAge() < high) {
                p.printPerson();
            }
        }
    }

    public static void printPerson(
            List<Person> roster, CheckPerson tester) {
        for (Person p : roster) {
            if (tester.test(p)) {
                p.printPerson();
            }
        }
    }

    public static void printPersonsWithPredicate(
            List<Person> roster, Predicate<Person> tester) {
        for (Person p : roster) {
            if (tester.test(p)) {
                p.printPerson();
            }
        }
    }

    public static void printPersonsWithFunction(
            List<Person> roster,
            Predicate<Person> tester,
            Function<Person, String> mapper,
            Consumer<String> block) {
            for (Person p : roster) {
                if (tester.test(p)) {
                    String data = mapper.apply(p);
                    block.accept(data);
                }
            }
    }


    public static List<Person> createRoster() {
        List<Person> roster = new ArrayList<>();
        roster.add(
                new Person(
                        "Fred",
                        IsoChronology.INSTANCE.date(1980, 6, 20),
                        Person.Sex.MALE,
                        "fred@example.com"));
        roster.add(
                new Person(
                        "Jane",
                        IsoChronology.INSTANCE.date(1990, 7, 15),
                        Person.Sex.FEMALE, "jane@example.com"));
        roster.add(
                new Person(
                        "George",
                        IsoChronology.INSTANCE.date(1991, 8, 13),
                        Person.Sex.MALE, "george@example.com"));
        roster.add(
                new Person(
                        "Bob",
                        IsoChronology.INSTANCE.date(2000, 9, 12),
                        Person.Sex.MALE, "bob@example.com"));

        return roster;
    }


    public String getName() {
        return name;
    }

    public static int compareByAge(Person a, Person b) {
        return a.birthday.compareTo(b.birthday);

    }

    public static void main(String[] args) {
        List<Person> roster = new ArrayList();
        Person.printPerson(roster, new CheckPersonEligibleForSelectiveService());
        Person.printPerson(roster, new CheckPerson() {
            public boolean test(Person p) {
                return p.getGender() == Sex.MALE
                        && p.getAge() >= 18
                        && p.getAge() <= 25;
            }
        });
        Person.printPerson(
                roster,
                (Person p) -> p.getGender() == Sex.MALE
                    && p.getAge() >= 18
                    && p.getAge() <= 25
        );

        Person.printPersonsWithPredicate(
                roster,
                p -> p.getGender() == Sex.MALE
                    && p.getAge() >= 18
                    && p.getAge() <= 25
        );

        Person.printPersonsWithFunction(
                roster,
                p -> p.getGender() == Sex.MALE
                    && p.getAge() >= 18
                    && p.getAge() <= 25,
                p -> p.getEmailAddress(),
                email -> System.out.println(email));

    }


}


