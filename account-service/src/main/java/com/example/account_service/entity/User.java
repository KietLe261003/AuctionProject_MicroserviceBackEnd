package com.example.account_service.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "user_db")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String name;
    String password;
    String address;
    Boolean gender;
    String email;
    String phone;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(columnDefinition = "RoleId", nullable = false,referencedColumnName = "RoleId")
    @JsonBackReference
    Role role;
}
