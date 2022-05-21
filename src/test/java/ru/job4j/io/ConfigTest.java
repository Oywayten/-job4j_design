package ru.job4j.io;

import org.hamcrest.Matchers;
import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class ConfigTest {

    @Test
    public void whenPairWithoutComment() {
        String path = "./data/pair_without_comment.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("name"), is("Petr Arsentev"));
        assertThat(config.value("surname"), is(Matchers.nullValue()));
    }

    @Test
    public void whenPairWithCommentsAndGaps() {
        String path = "./data/pairs_with_comments_and_gaps.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("name"), is(Matchers.nullValue()));
        assertThat(config.value("fill"), is("drozdov"));
        assertThat(config.value("dron"), is("kollaider"));
        assertThat(config.value("onemore"), is(Matchers.nullValue()));
        assertThat(config.value("slonik"), is("zohcho"));
    }

    @Test
    public void whenPairWithCommentsAndGapsWithEquallysAtEnd() {
        String path = "./data/pairs_with_comments_and_gaps_with_equallys_at_end.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("name"), is(Matchers.nullValue()));
        assertThat(config.value("fill"), is("drozdov=1"));
        assertThat(config.value("dron"), is("kollaider="));
        assertThat(config.value("onemore"), is(Matchers.nullValue()));
        assertThat(config.value("slonik"), is("zohcho"));
    }

    @Test
    public void whenOneKeyWithoutValue() {
        String path = "./data/one_key_without_value.properties";
        Config config = new Config(path);
        assertThrows(IllegalArgumentException.class, config::load);
    }
}