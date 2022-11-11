package com.ykb.annualleavemodule.helpers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Slf4j
@Component
public class LeaveDuration {

    public int leaveDurationCalculator(String p_startDate, String p_endDate)
            throws Exception {

        try {
            int leaveDuration = 0;

            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-mm-dd");
            LocalDate startDate = LocalDate.parse(p_startDate);
            LocalDate endDate = LocalDate.parse(p_endDate);

            if (startDate == null || endDate == null) {
                throw new Exception("msg.emptyarguments");
            }

            if (startDate.isAfter(endDate)) {
                log.error("İzinine başlama tarihi bitiş tarihindne sonra olmamalı!");
                throw new Exception("msg.latestartdate");
            }

            leaveDuration = (int) ChronoUnit.DAYS.between(startDate, endDate) + 1;

            for (int i = 0; i <= leaveDuration; i++) {
                if (startDate.getDayOfWeek().getValue() == 6 || startDate.getDayOfWeek().getValue() == 7) {
                    leaveDuration--;
                }
                startDate = startDate.plusDays(1);
            }

            return leaveDuration;
        }
        catch (Exception ex){
            log.error(ex.getMessage());
            throw new Exception(ex.getMessage());
        }
    }
}
