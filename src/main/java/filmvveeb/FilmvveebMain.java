package filmvveeb;

import filmvveeb.people.Actor;
import filmvveeb.people.Cast;
import filmvveeb.people.Director;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class FilmvveebMain {

    public static void main(String[] args) {

        FilmvveebWindow window = new FilmvveebWindow();
        System.out.println(window);

//        Director director = new Director("Maciej", "Kawulski");
//        Actor actor = new Actor("Maciej", "Kowalczyk");
//        List<Actor> actors = new ArrayList<>();
//        actors.add(actor);
//        Cast cast = new Cast(actors);
//        LocalDate releaseDate = LocalDate.of(2019,12,12);
//        Movie movie = new Movie("Jak zostałem Gangsterem. Historia prawdziwa",Genre.BIOGRAPHY,director, cast, releaseDate,8);
//        System.out.println(movie);
    }
}
