package com.ykb.annualleavemodule.helpers;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@ContextConfiguration(classes = {LeaveDuration.class})
@ExtendWith(SpringExtension.class)
class LeaveDurationTest {
    @Autowired
    private LeaveDuration leaveDuration;

    @Test
    void testLeaveDurationCalculator() throws Exception {
        assertEquals(1, leaveDuration
                .leaveDurationCalculator("2020-03-01", "2020-03-02"));
    }

    @Test
    void testLeaveDurationCalculatorThrowsException() throws Exception {
        assertAll("testLeaveDurationCalculator",
                () ->  assertThrows(Exception.class, () -> leaveDuration
                        .leaveDurationCalculator("2020/03/04", "2020-03-03")),
                () ->  assertThrows(Exception.class, () -> leaveDuration
                        .leaveDurationCalculator("2020-03-01", "2020/03/01")),
                () -> assertThrows(Exception.class, () -> leaveDuration
                        .leaveDurationCalculator(null, null))
        );
    }
}

