package com.cinema.point.domain.comparator;

import com.cinema.point.domain.Seance;

import java.time.LocalTime;
import java.util.Comparator;

public class SeanceTimeComparator implements Comparator<Seance> {

    @Override
    public int compare(Seance o1, Seance o2) {
        LocalTime o1Time = o1.getMovieBeginTime().toLocalTime();
        LocalTime o2Time = o2.getMovieBeginTime().toLocalTime();
        if (o1Time.getHour() == o2Time.getHour()) {
            return o1Time.getMinute() - o2Time.getMinute();
        }
        return o1Time.getHour() - o2Time.getHour();
    }
}
