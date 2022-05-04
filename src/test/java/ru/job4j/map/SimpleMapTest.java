package ru.job4j.map;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import java.util.Iterator;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.*;

public class SimpleMapTest {

    @Test
    public void whenPut2DiffEntriesIsTrue() {
        SimpleMap<String, Integer> schools = new SimpleMap<>();
        Assert.assertTrue(schools.put("StPete", 1500));
        Assert.assertTrue(schools.put("Moscow", 5500));
    }

    @Test
    public void whenPut2DiffEntries1SameIsFalse() {
        SimpleMap<String, Integer> schools = new SimpleMap<>();
        schools.put("StPeteStPeteStPeteStPete", 1500);
        schools.put("Moscow", 5500);
        Assert.assertFalse(schools.put("Moscow", 5500));
    }

    @Test
    public void whenPutMoscowAndGetMoscowIs5500() {
        SimpleMap<String, Integer> schools = new SimpleMap<>();
        schools.put("Moscow", 5500);
        assertThat(schools.get("Moscow"), is(5500));
    }

    @Test
    public void whenNothingPutAndGetNull() {
        SimpleMap<String, Integer> schools = new SimpleMap<>();
        assertThat(schools.get("Moscow"), is(nullValue()));
    }

    @Test
    public void whenPut2Remove1() {
        SimpleMap<String, Integer> schools = new SimpleMap<>();
        schools.put("Moscow", 5500);
        schools.put("Samara", 50);
        assertTrue(schools.remove("Samara"));
        assertThat(schools.get("Moscow"), is(5500));
        assertThat(schools.get("Samara"), is(nullValue()));
    }

    @Test
    public void whenPut1Remove2() {
        SimpleMap<String, Integer> schools = new SimpleMap<>();
        schools.put("Moscow", 5500);
        assertTrue(schools.remove("Moscow"));
        assertFalse(schools.remove("Moscow"));
    }

    @Test
    public void whenGetIteratorFromEmptyMapThenHasNextReturnFalse() {
        SimpleMap<String, Integer> schools = new SimpleMap<>();
        Iterator<String> iterator = schools.iterator();
        assertFalse(iterator.hasNext());
    }

    @Test
    public void whenPutTwiceAndIteratorHasNextThreeTimesAndNextTwice() {
        SimpleMap<String, Integer> schools = new SimpleMap<>();
        schools.put("Moscow", 5500);
        schools.put("Samara", 50);
        schools.put("Izhevsk", 9);
        Iterator<String> iterator = schools.iterator();
        assertTrue(iterator.hasNext());
        assertEquals("Izhevsk", iterator.next());
        assertTrue(iterator.hasNext());
        assertEquals("Moscow", iterator.next());
        assertTrue(iterator.hasNext());
        assertEquals("Samara", iterator.next());
        assertFalse(iterator.hasNext());
    }

    @Ignore
    @Test
    public void whenPut10ElAndExpand() {
        SimpleMap<String, Integer> schools = new SimpleMap<>();
        schools.put("Moscow", 5500);
        schools.put("Samara", 50);
        schools.put("StPete", 1500);
        schools.put("Syzran", 25);
        schools.put("Rostov", 30);
        schools.put("Omsk", 19);
        schools.put("Izhevsk", 9);
        schools.put("EKB", 25);
        schools.put("Kazan", 190);
        schools.put("Ufa", 100);
        Iterator<String> iterator = schools.iterator();
        assertTrue(iterator.hasNext());
        assertEquals("Izhevsk", iterator.next());
        assertTrue(iterator.hasNext());
        assertEquals("Kazan", iterator.next());
        assertTrue(iterator.hasNext());
        assertEquals("Syzran", iterator.next());
        assertTrue(iterator.hasNext());
    }
}