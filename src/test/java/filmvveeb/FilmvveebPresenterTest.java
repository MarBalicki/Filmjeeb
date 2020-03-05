package filmvveeb;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

class FilmvveebPresenterTest {

    private FilmvveebContract.Presenter presenter;
    private FilmvveebContract.View view;

    @BeforeEach
    public void setup() {
        view = Mockito.mock(FilmvveebContract.View.class);
        presenter = new FilmvveebPresenter(view);
        presenter.prepareData();
    }

    @Test
    public void initPresenter() {
        presenter.initData();
        Mockito.verify(view).refreshList(any());
    }

    @Test
    public void filterByFromDate() {
        presenter.onFromDateChange(LocalDate.of(1994, 1, 1));
        verify(view, times(1)).refreshList(any());
        List<Movie> lastResult = presenter.getLastResult();
        assertEquals(2, lastResult.size());
    }

    @Test
    public void filterToDate() {
        presenter.onToDateChange(LocalDate.of(1924, 12, 31));
        verify(view, times(1)).refreshList(any());
        List<Movie> lastResult = presenter.getLastResult();
        assertEquals(5, lastResult.size());
    }

}

