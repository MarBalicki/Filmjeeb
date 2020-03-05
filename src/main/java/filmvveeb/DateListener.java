package filmvveeb;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

public abstract class DateListener implements KeyListener {
    private static final List<DateTimeFormatter> FORMATS = Arrays.asList(
            DateTimeFormatter.ofPattern("dd-MM-yyyy"),
            DateTimeFormatter.ofPattern("dd.MM.yyyy"),
            DateTimeFormatter.ofPattern("dd MM yyyy"),
            DateTimeFormatter.ofPattern("yyyy-dd-MM"),
            DateTimeFormatter.ofPattern("yyyy MM dd"),
            DateTimeFormatter.ofPattern("yyyy.MM.dd"));

    private final JTextField field;

    public DateListener(JTextField field) {
        this.field = field;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    private LocalDate parseDate(String input) {
        for (DateTimeFormatter formatter : FORMATS) {
            try {
                return LocalDate.parse(input, formatter);
            } catch (Exception e) {
//                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {
        String input = field.getText();
        LocalDate date = parseDate(input);
        System.out.println(date);
        onDateUpdate(date);
    }

    protected abstract void onDateUpdate(LocalDate newDate);
}
