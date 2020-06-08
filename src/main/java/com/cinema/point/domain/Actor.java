package com.cinema.point.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@Table(name = "actor")
public class Actor {

    @Id
    @GeneratedValue
    @Column(nullable = false)
    private Long id;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Lob
    @Basic(fetch = FetchType.LAZY)
    @Column(name = "picture")
    private byte[] profilePicture;

}
