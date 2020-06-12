package com.cinema.point.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Time;
import java.util.HashSet;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "seance")
public class Seance {

    @Id
    @GeneratedValue
    @Column(nullable = false)
    private Long id;

    @Column(name = "date_from", nullable = false)
    private Date seanceDateFrom;

    @Column(name = "date_to", nullable = false)
    private Date seanceDateTo;

    @Column(name = "start_movie", nullable = false)
    private Time movieBeginTime;

    @Column(name = "end_movie", nullable = false)
    private Time movieEndTime;

    @Column(name = "ticket_price", nullable = false)
    private BigDecimal ticketPrice;

    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "hall_id", nullable = false)
    private Hall hall;

    @ElementCollection(targetClass = Day.class)
    @Enumerated(EnumType.STRING)
    private Set<Day> day = new HashSet<>();

    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "movie_id", nullable = false)
    private Movie movie;
}
