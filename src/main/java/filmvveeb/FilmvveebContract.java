package filmvveeb;

import java.time.LocalDate;
import java.util.List;

public class FilmvveebContract {

    public interface View {
        void refreshList(List<Movie> movies);

    }

    public interface Presenter {
        void prepareData();

        void initData();

        void onWordChange(String word);

        void onFromDateChange(LocalDate newDate);

        void onToDateChange(LocalDate newDate);

        void onGenreSort(Genre genre);

        List<Movie> getLastResult();
    }
}
