package com.ykb.annualleavemodule.helpers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Set;

@Slf4j
@Component
public class LeaveDuration {

    public int leaveDurationCalculator(String p_startDate, String p_endDate) throws Exception {

        int leaveDuration = 0;

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-mm-dd");
        LocalDate startDate = LocalDate.parse(p_startDate);
        LocalDate endDate = LocalDate.parse(p_endDate);

        if (startDate == null || endDate == null) {
            throw new IllegalArgumentException("Invalid method argument(s)");
        }

        if (startDate.isAfter(endDate)) {
            log.error("İzinine başlama tarihi bitiş tarihindne sonra olmamalı!");
            throw new Exception("İzinine başlama tarihi bitiş tarihindne sonra olmamalı!");
        }

        leaveDuration = (int) ChronoUnit.DAYS.between(startDate, endDate) + 1;

        for (int i = 0; i <= leaveDuration; i++){
            if(startDate.getDayOfWeek().getValue() == 6 || startDate.getDayOfWeek().getValue() == 7){
                leaveDuration--;
            }
            startDate = startDate.plusDays(1);
        }

        return leaveDuration;
    }
}
