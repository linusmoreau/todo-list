package model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class DateTest {

    @Test
    public void testDate() {
        Date date1 = new Date();
        assertFalse(date1.isSet());
        Date date2 = new Date(2021, 10, 13);
        assertTrue(date2.isSet());
        assertEquals("2021-10-13", date2.toString());

        date1.setDate(2021, 10, 13);
        assertEquals(date1.comparator(), date2.comparator());
    }

    @Test
    public void testToString() {
        Date date = new Date();
        assertEquals("", date.toString());
        date.setDate(2021, 8, 9);
        assertEquals("2021-08-09", date.toString());

    }

    @Test
    public void testGetters() {
        Date date = new Date(1941, 9, 23);
        assertEquals(1941, date.getYear());
        assertEquals(9, date.getMonth());
        assertEquals(23, date.getDay());
    }

    @Test
    public void testComparator() {
        Date date = new Date();
        assertEquals(2_147_483_647, date.comparator());
        date.setDate(2021, 10, 15);
        assertEquals(20211015, date.comparator());
    }
}
