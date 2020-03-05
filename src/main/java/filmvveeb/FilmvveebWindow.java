package filmvveeb;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.time.LocalDate;
import java.util.List;

public class FilmvveebWindow implements FilmvveebContract.View {
    private static final int FIELD_WIDTH = 400;
    private static final int FIELD_HEIGHT = 60;
    private static final int PADDING = 50;
    private JFrame frame;
    private JTextField movieInput;
    private JTextField dateFrom;
    private JTextField dateTo;
    private JTextField genre;
    private JList<Movie> result;
    private FilmvveebContract.Presenter presenter = new FilmvveebPresenter(this);

    public FilmvveebWindow() {
        frame = new JFrame("FILMVVEEB");
        frame.setSize(1000, 1000);
        frame.setLayout(null);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        JLabel title = new JLabel("Filter by title");
        title.setBounds(PADDING, PADDING, FIELD_WIDTH, FIELD_HEIGHT);
        frame.add(title);
        movieInput = new JTextField();
        movieInput.setBounds(150, PADDING, FIELD_WIDTH, FIELD_HEIGHT);
        movieInput.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                presenter.onWordChange(movieInput.getText());
            }

            @Override
            public void keyPressed(KeyEvent e) {

            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });
        frame.add(movieInput);

        JLabel fromToDate = new JLabel("Filter by date");
        fromToDate.setBounds(PADDING, 150, FIELD_WIDTH, FIELD_HEIGHT);
        frame.add(fromToDate);
        dateFrom = new JTextField("from");
        dateFrom.setBounds(150, 150, 150, FIELD_HEIGHT);
        dateFrom.addKeyListener(new DateListener(dateFrom) {
            @Override
            protected void onDateUpdate(LocalDate newDate) {
                presenter.onFromDateChange(newDate);
            }
        });
        frame.add(dateFrom);

        dateTo = new JTextField("to");
        dateTo.setBounds(400, 150, 150, FIELD_HEIGHT);
        dateTo.addKeyListener(new DateListener(dateTo) {
            @Override
            protected void onDateUpdate(LocalDate newDate) {
                presenter.onToDateChange(newDate);
            }
        });
        frame.add(dateTo);

        JLabel genreSort = new JLabel("Filter by genre");
        genreSort.setBounds(PADDING, 250, FIELD_WIDTH, FIELD_HEIGHT);
        frame.add(genreSort);
        genre = new JTextField();
        genre.setBounds(150, 250, 150, FIELD_HEIGHT);
        genre.addKeyListener(new GenreListener(genre) {

        });
        frame.add(genre);

        result = new JList<>();
        result.setBounds(PADDING, 360, 880, 550);
        frame.add(result);

        frame.setVisible(true);
        presenter.initData();
        presenter.prepareData();

        //todo second window to add new films

    }

    @Override
    public void refreshList(List<Movie> movies) {
        DefaultListModel<Movie> list = new DefaultListModel<>();
        for (Movie movie : movies) {
            list.addElement(movie);
        }
        result.setModel(list);
    }

}
