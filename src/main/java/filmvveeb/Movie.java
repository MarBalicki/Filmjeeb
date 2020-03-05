package filmvveeb;

import filmvveeb.people.Cast;
import filmvveeb.people.Director;

import java.time.LocalDate;
import java.util.Objects;

public class Movie {
    private Genre genre;
    protected String title;
    private Director director;
    private Cast cast;
    protected LocalDate releaseDate;
    private int rating;

    public Movie(String title, LocalDate releaseDate) {
        this.title = title;
        this.releaseDate = releaseDate;
    }

    public Movie(String title, Director director, Cast cast, LocalDate releaseDate) {
        this.title = title;
        this.director = director;
        this.cast = cast;
        this.releaseDate = releaseDate;
    }

    public Movie(String title, Genre genre, Director director, Cast cast, LocalDate releaseDate, int rating) {
        this.title = title;
        this.genre = genre;
        this.director = director;
        this.cast = cast;
        this.releaseDate = releaseDate;
        this.rating = rating;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Movie movie = (Movie) o;
        return Objects.equals(title, movie.title) &&
                Objects.equals(releaseDate, movie.releaseDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, releaseDate);
    }

    @Override
    public String toString() {
        try {
            return "Tytuł filmu: "
                    + "\"" + title + "\""
                    + " - " + genre + ", "
                    + director + ", "
                    + "Aktorzy: " + cast
                    .toString()
                    .replace("[", "")
                    .replace("]", "")
                    + " data premiery: " + releaseDate;
//                    + ", ocena: " + rating;
        } catch (NullPointerException e) {
//            System.out.println("remove brackets");
        }
        return null;
    }
}
