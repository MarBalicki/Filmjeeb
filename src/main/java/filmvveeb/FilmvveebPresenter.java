package filmvveeb;

import filmvveeb.people.Actor;
import filmvveeb.people.Cast;
import filmvveeb.people.Director;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FilmvveebPresenter implements FilmvveebContract.Presenter {
    private final FilmvveebContract.View view;
    private List<Movie> movies = new ArrayList<>();
    private List<Movie> lastResult = new ArrayList<>();
    private String word;
    private LocalDate fromDate;
    private LocalDate toDate;
    private Genre genre;

    public FilmvveebPresenter(FilmvveebContract.View view) {
        this.view = view;
    }

    @Override
    public void prepareData() {
        File file = new File("movies2.csv");
        try {
            FileReader reader = new FileReader(file);
            BufferedReader buffer = new BufferedReader(reader);
            String line = buffer.readLine();
            boolean firstIgnored = false;
            while (line != null) {
                if (!firstIgnored) {
                    firstIgnored = true;
                } else {
                    Movie movie = parseMovie(line);
                    movies.add(movie);
                }
                line = buffer.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Movie parseMovie(String line) {
        String[] split = line.split(";");
//        int movieLength = Integer.parseInt(split[1]);
        String title = split[2];
        if (!split[3].equals("")) {
            String genreString = split[3];
            genre = Genre.valueOf(genreString.toUpperCase().replace(" ","_"));
        }
        Director director = getDirector(split[6]);
        Cast cast = getCast(new String[]{split[4], split[5]});
        int year = Integer.parseInt(split[0]);
        LocalDate date = LocalDate.MIN;
        return new Movie(title, genre, director, cast, date.withYear(year));
    }

    private Director getDirector(String director) {
        try {
            String[] directorData = director.split(", ");
            String surname = directorData[0];
            String name = directorData[1];
            return new Director(name, surname);
        } catch (IndexOutOfBoundsException e) {
//            System.out.println("director");
        }
        return null;
    }

    private Cast getCast(String[] data) {
        List<Actor> actors = new ArrayList<>();
        try {
            for (String d : data) {
                String[] actorData = d.split(", ");
                String actorName = actorData[1];
                String actorSurname = actorData[0];
                Actor actor = new Actor(actorName, actorSurname);
                actors.add(actor);
            }
            return new Cast(actors);
        } catch (IndexOutOfBoundsException e) {
//            System.out.println("cast");
        }
        return null;
    }

    @Override
    public void initData() {
        view.refreshList(movies);
    }

    @Override
    public void onWordChange(String word) {
        this.word = word;
        lastResult = movies.stream()
                .filter(movie -> movie.title.toLowerCase().contains(word))
                .collect(Collectors.toList());
        view.refreshList(lastResult);
    }

    @Override
    public void onFromDateChange(LocalDate fromDate) {
        this.fromDate = fromDate;
        refreshAndShow();
    }

    @Override
    public void onToDateChange(LocalDate toDate) {
        this.toDate = toDate;
        refreshAndShow();
    }

    private void refreshAndShow() {
        refreshResult();
        view.refreshList(lastResult);
    }

    private void refreshResult() {
        Stream<Movie> stream = movies.stream();
        if (word != null) {
            stream = stream.filter(movie -> movie.title.contains(word));
        }
        if (fromDate != null) {
            stream = stream.filter(movie -> !movie.releaseDate.isBefore(fromDate));
        }
        if (toDate != null) {
            stream = stream.filter(movie -> !movie.releaseDate.isAfter(toDate));
        }
        lastResult = stream.collect(Collectors.toList());
    }

    @Override
    public void onGenreChange(String word) {
        this.word = word;
        lastResult = movies.stream()
                .filter(movie -> movie.genre.toString().toLowerCase().contains(word))
                .collect(Collectors.toList());
        view.refreshList(lastResult);
    }

    @Override
    public List<Movie> getLastResult() {
        return lastResult;
    }
}
