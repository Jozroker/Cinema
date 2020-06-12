package com.cinema.point.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "ticket")
public class Ticket {

    @Id
    @GeneratedValue
    @Column(nullable = false)
    private Long id;

    @Column(name = "place_row", nullable = false)
    private Integer placeRow;

    @Column(name = "place_column", nullable = false)
    private Integer placeColumn;

    @Column(name = "seance_date", nullable = false)
    private Date seanceDate;

    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "seance_id", nullable = false)
    private Seance seance;

}
