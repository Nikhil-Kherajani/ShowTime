package com.driver.bookMyShow.Models;

import com.driver.bookMyShow.Enums.Gender;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "USERS")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString(exclude = {"ticketList", "password"}) // Exclude ticketList and password from toString
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(unique = true)
    private String emailId;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String password;

    @CreationTimestamp
    private Date createdOn;

    private int age;

    private String address;

    @Enumerated(value = EnumType.STRING)
    private Gender gender;

    private String mobileNo;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    @JsonIgnore
    @Builder.Default
    private List<Ticket> ticketList = new ArrayList<>();
}
