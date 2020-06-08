package com.cinema.point.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "hall")
public class Hall {

    @Id
    @GeneratedValue
    @Column(nullable = false)
    private Long id;

//    @Column(name = "places_count", nullable = false)
//    private Integer placesCount;

    @Column(nullable = false)
    private Integer rows;

    @Column(nullable = false)
    private Integer columns;

    @Column(name = "free_places")
    private Integer freePlaces;

    @Column(name = "reserved_places")
    private Integer reservedPlaces = 0;

    //    @ManyToOne
//    @JoinColumn(name = "hall_id")
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private HallType type;

    public void reserve(int count) {
        this.reservedPlaces += count;
        this.freePlaces -= count;
    }

    public void unreserve(int count) {
        this.reservedPlaces -= count;
        this.freePlaces += count;
    }
}
