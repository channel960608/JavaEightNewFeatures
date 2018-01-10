package Lambda;

public class CheckPersonEligibleForSelectiveService implements CheckPerson {


    @Override
    public boolean test(Person p) {
        return p.gender == Person.Sex.MALE &&
                p.getAge() >= 18 &&
                p.getAge() <= 25;
    }
}
