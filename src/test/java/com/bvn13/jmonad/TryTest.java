package com.bvn13.jmonad;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by bvn13 on 10.06.2019.
 */
public class TryTest {

    @Test
    public void testTryFirstProbe() throws Throwable {
        Try.ofThrowable(() -> String.valueOf(10))
                .get();
    }

    @Test
    public void testTryInteger() throws Throwable {
        Try<Integer> value = Try.ofThrowable(() -> Integer.valueOf("1"));
        assertEquals(Integer.valueOf(1), value.get());
    }

    @Test
    public void testTryIntegerAdd() throws Throwable {
        String value = Try.ofThrowable(() -> Integer.valueOf("1"))
                .flatMap(v -> String.format("%d", v))
                .get();
        assertEquals("1", value);
    }

    @Test
    public void testFailureWithDefault() {
        int value = Try.ofThrowable(() -> Integer.valueOf("T"))
                .orElse(5);
        assertEquals(5, value);
    }

    @Test
    public void testFilter() throws Throwable {
        int value = Try.ofThrowable(() -> Integer.valueOf("1"))
                .filter(i -> i > 0)
                .get();
        assertEquals(1, value);
        int notValue = Try.ofThrowable(() -> Integer.valueOf("1"))
                .filter(i -> i < 0)
                .orElse(10);
        assertEquals(10, notValue);
    }

    @Test
    public void testMap() throws Throwable {
        int value = Try.ofThrowable(() -> Integer.valueOf("1"))
                .map(i -> i + 20)
                .get();
        assertEquals(21, value);
    }

    @Test
    public void testOrElseThrow() throws Throwable {
        Throwable t = null;

        try {
            int value = Try.ofThrowable(() -> Integer.valueOf("1"))
                    .filter(v -> v < 0)
                    .orElseThrow(new IllegalArgumentException("!!!"));
        } catch (Throwable e) {
            t = e;
        }

        assertTrue(t instanceof IllegalArgumentException);
    }

    @Test
    public void testToOptional() {
        int value = Try.ofThrowable(() -> Integer.valueOf("1"))
                .toOptional().orElse(2);
        assertEquals(1, value);
        int notValue = Try.ofThrowable(() -> Integer.valueOf(""))
                .toOptional().orElse(2);
        assertEquals(2, notValue);
    }

    @Test
    public void testIsSuccess() {
        boolean success = Try.ofThrowable(() -> Integer.valueOf("1")).isSuccess();
        assertTrue(success);
        boolean failure = Try.ofThrowable(() -> Integer.valueOf("")).isSuccess();
        assertFalse(failure);
    }

}
