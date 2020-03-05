package filmvveeb.people;

public class Director {
    String firstName;
    String secondName;
    String surname;

    public Director(String firstName, String surname) {
        this.firstName = firstName;
        this.surname = surname;
    }

    public Director(String firstName, String secondName, String surname) {
        this.firstName = firstName;
        this.secondName = secondName;
        this.surname = surname;
    }

    @Override
    public String toString() {
        return secondName != null
                ? "Reżyser: " + firstName + " " + secondName + " " + surname
                : "Reżyser: " + firstName + " " + surname;
    }
}
