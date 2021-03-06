package ru.job4j.io;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Проверка байтового потока. Тестирование.
 *
 * @author Alexander Savchenko
 * @version 1.0
 * @since 2019-02-07
 */
public class InputCheckerTest {
    private static final Logger LOG = LoggerFactory.getLogger(InputCheckerTest.class);
    private final InputChecker checker = new InputChecker();

    @Test
    public void whenDigitStreamAndEvenThenTrue() {
        assertTrue(
                execute("123456")
        );
    }

    @Test
    public void whenDigitStreamtAndOddThenFalse() {
        assertFalse(
                execute("1234567")
        );
    }

    @Test
    public void whenLargeDigitStreamAndEvenThenTrue() {
        assertTrue(
                execute("1234567890123456789012345678901234567890")
        );
    }

    @Test
    public void whenLargeDigitStreamAndOddThenFalse() {
        assertFalse(
                execute("12345678901234567890123456789012345678901")
        );
    }

    @Test
    public void whenNotOnlyDigitStreamThenFalse() {
        assertFalse(
                execute("abc123456_")
        );
    }

    @Test
    public void whenNoDigitSuffixStreamThenFalse() {
        assertFalse(
                execute("123456_")
        );
    }

    @Test
    public void whenNoDigitPrefixStreamThenFalse() {
        assertFalse(
                execute("_123456")
        );
    }

    @Test
    public void whenEmptyStreamThenFalse() {
        assertFalse(
                execute("")
        );
    }

    @Test
    public void whenIOExceptionThrown() {
        try (InputStream in = new InputStream() {
            @Override
            public int read() throws IOException {
                throw new IOException();
            }
        }) {
            assertFalse(checker.isEvenNumber(in));
        } catch (IOException ignore) {
        }
    }

    private boolean execute(String number) {
        boolean result = false;
        try (InputStream in = new ByteArrayInputStream(number.getBytes())) {
            result = checker.isEvenNumber(in);
        } catch (IOException ioe) {
            LOG.error(ioe.getMessage(), ioe);
        }
        return result;
    }
}
