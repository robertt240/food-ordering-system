package com.example.model;

import org.junit.Before;
import org.junit.Test;

import java.util.*;

import static com.google.common.truth.Truth.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class BillTest {
    private Bill bill;
    private Orderable order3;
    private Orderable order2;
    private Orderable order1;
    private ResourceBundle bundle;

    @Before
    public void setUp() throws Exception {
        order1 = mock(Orderable.class);
        order2 = mock(Orderable.class);
        order3 = mock(Orderable.class);
        bundle = new ResourceBundle() {
            @Override
            protected Object handleGetObject(String key) {
                return "test";
            }

            @Override
            public Enumeration<String> getKeys() {
                return Collections.emptyEnumeration();
            }
        };
    }

    @Test
    public void shouldCalculateSumOfPrices() throws Exception {
        //given
        when(order1.getPrice()).thenReturn(2.5);
        when(order2.getPrice()).thenReturn(3.5);
        when(order3.getPrice()).thenReturn(10d);

        //when
        bill = new Bill(Arrays.asList(order1, order2, order3), bundle);

        //then
        assertThat(16d).isEqualTo(bill.getSum());
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionWhenNull() {
        //when
        bill = new Bill(null, bundle);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionWhenEmptyList() {
        //when
        bill = new Bill(new ArrayList<>(), bundle);
    }

}