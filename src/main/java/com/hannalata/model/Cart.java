package com.hannalata.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "carts")
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "status")
    private Status status;
    @ManyToOne(targetEntity = User.class)
    private User user;
    @Column(name = "creation_time")
    private Long creationTime;

    public Cart(Status status, User user, Long creationTime) {
        this.status = status;
        this.user = user;
        this.creationTime = creationTime;
    }
}
