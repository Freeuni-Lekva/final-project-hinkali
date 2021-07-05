package filter_tests;

import dao.implementation.filters.AndFilter;
import dao.implementation.filters.NoFilter;
import dao.implementation.filters.OrFilter;
import dao.implementation.filters.StringFilter;
import dao.interfaces.Filter;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FilterTest {
    @Test
    public void testNoFilter(){
        Filter f = new NoFilter();
        assertEquals("", f.format());
    }

    @Test
    public void testCombinedFiltersWithNoFilter(){
        List<Filter> noFilters = new ArrayList<>();
        noFilters.add(new NoFilter());
        noFilters.add(new NoFilter());
        Filter andFilter = new AndFilter(noFilters);
        assertEquals("", andFilter.format());

        Filter orFilter = new OrFilter(noFilters);
        assertEquals("", orFilter.format());

        List<Filter> blankFilters = new ArrayList<>(noFilters);
        blankFilters.add(new StringFilter("columnName", ""));
        blankFilters.add(new StringFilter("", "filter"));
        Filter andFilter2 = new AndFilter(blankFilters);
        assertEquals("", andFilter2.format());
        Filter orFilter2 = new OrFilter(blankFilters);
        assertEquals("", orFilter2.format());
    }

    @Test
    public void testStringFilter1(){
        String expected = "username LIKE '%test%'";
        Filter stringFilter = new StringFilter("username", "test");
        assertEquals(expected, stringFilter.format());
    }

    @Test
    public void testStringFilter2(){
        Filter stringFilter = new StringFilter("username", "");
        assertEquals("", stringFilter.format());
    }

    @Test
    public void testCombinedFiltersAdvanced(){
        String expectedA = "username LIKE '%testuser%' AND name LIKE '%testname%'";
        List<Filter> miscFilters = new ArrayList<>();
        miscFilters.add(new NoFilter());
        miscFilters.add(new StringFilter("username", "testuser"));
        miscFilters.add(new StringFilter("name", "testname"));
        Filter andFilter = new AndFilter(miscFilters);
        assertEquals(expectedA, andFilter.format());

        String expectedB = "surname LIKE '%testsurname%' OR random LIKE '%random%'";
        List<Filter> extraFilters = new ArrayList<>();
        extraFilters.add(new NoFilter());
        extraFilters.add(new StringFilter("surname", "testsurname"));
        extraFilters.add(new StringFilter("random", "random"));
        Filter orFilter = new OrFilter(extraFilters);
        assertEquals(expectedB, orFilter.format());

        String expectedC = expectedA + " OR " + expectedB;
        List<Filter> combine = new ArrayList<>();
        combine.add(andFilter);
        combine.add(orFilter);
        Filter combinerOrFilter = new OrFilter(combine);

        assertEquals(expectedC, combinerOrFilter.format());
    }
}
