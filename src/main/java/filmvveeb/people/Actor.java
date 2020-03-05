package filmvveeb.people;

public class Actor {
    String firstName;
    String secondName;
    String surname;

    public Actor(String firstName, String surname) {
        this.firstName = firstName;
        this.surname = surname;
    }

    public Actor(String firstName, String secondName, String surname) {
        this.firstName = firstName;
        this.secondName = secondName;
        this.surname = surname;
    }

    @Override
    public String toString() {
        return secondName != null
                ? firstName + " " + secondName + " " + surname
                : firstName + " " + surname;
    }
}
