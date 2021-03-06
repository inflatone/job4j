package ru.job4j.search;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Тестируем работу поиска в телефонном справочнике.
 */
public class PhoneDictionaryTest {
    private final PhoneDictionary phones = new PhoneDictionary();

    @Before
    public void prepare() {
        phones.add(
                new Person("Mickey", "Mouse", "12345", "Disney Land")
        );
    }

    @Test
    public void whenFindByName() {
        var result = this.phones.find("Mick");
        assertThat(result.iterator().next().getSurname(), is("Mouse"));
    }

    @Test
    public void whenFindByPhone() {
        var result = this.phones.find("123");
        assertThat(result.iterator().next().getSurname(), is("Mouse"));
    }

    @Test
    public void whenFindByAddress() {
        var result = this.phones.find("Land");
        assertThat(result.iterator().next().getSurname(), is("Mouse"));
    }

    @Test
    public void whenDictionaryHasNotKey() {
        var result = this.phones.find("TomCat");
        assertThat(result.iterator().hasNext(), is(false));
    }
}