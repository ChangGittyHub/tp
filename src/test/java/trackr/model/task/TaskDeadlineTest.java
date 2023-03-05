package trackr.model.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static trackr.testutil.Assert.assertThrows;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;

public class TaskDeadlineTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new TaskDeadline(null));
    }

    @Test
    public void constructor_wrongFormat_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> new TaskDeadline("2023/01/01"));
    }

    @Test
    public void constructor_dateNotInCalendar_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> new TaskDeadline("35/14/2023"));
    }

    @Test
    public void constructor_datePassed_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> new TaskDeadline("01/01/2020"));
    }

    @Test
    public void isValidTaskDeadline() {
        // null task deadline
        assertThrows(NullPointerException.class, () -> TaskDeadline.isValidTaskDeadline(null));

        // invalid task deadline

        String wrongFormatDate = "2024-01-01";
        assertFalse(TaskDeadline.isValidTaskDeadline(wrongFormatDate)); //deadline is in the wrong format

        String invalidDate = "35/14/2024";
        assertFalse(TaskDeadline.isValidTaskDeadline(invalidDate)); //deadline is an invalid date in the calendar

        String passedDate = "01/01/2022";
        assertFalse(TaskDeadline.isValidTaskDeadline(passedDate)); // deadline is before today's date

        // valid task deadline
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String todayDate = LocalDate.now().format(dtf);
        String tomorrow = LocalDate.now().plusDays(1).format(dtf);
        String farAwayDate = LocalDate.now().plusMonths(7).plusDays(21).format(dtf);
        assertTrue(TaskDeadline.isValidTaskDeadline(todayDate)); // today's date
        assertTrue(TaskDeadline.isValidTaskDeadline(tomorrow)); // tomorrow's date
        assertTrue(TaskDeadline.isValidTaskDeadline(farAwayDate)); // very far away date
    }

    @Test
    public void toStringTest() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String todayDate = LocalDate.now().format(dtf);
        DateTimeFormatter dtf2 = DateTimeFormatter.ofPattern("dd LLLL yyyy");
        String expectedDate = LocalDate.now().format(dtf2);
        assertEquals(expectedDate, new TaskDeadline(todayDate).toString());
    }
}
