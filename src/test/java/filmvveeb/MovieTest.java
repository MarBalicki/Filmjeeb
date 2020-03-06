package filmvveeb;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class MovieTest {

    @Test
    void testEquals() {
        Movie movie = new Movie("Test Film ", LocalDate.of(1991,12,3));
        Movie movie1 = new Movie("Test film", LocalDate.of(1991,12,3));
        boolean isItEqual = movie.equals(movie1);
        assertTrue(isItEqual);
    }
}