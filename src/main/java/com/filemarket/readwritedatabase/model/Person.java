package com.filemarket.readwritedatabase.model;

import lombok.*;

import javax.persistence.*;


@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Getter
@Setter
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "name")
    private String name;

    @Column(columnDefinition = "phone")
    private String phoneNumber;

}
