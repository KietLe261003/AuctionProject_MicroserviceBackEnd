package com.example.account_service.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "role_db")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "RoleId")
    Long id;
    String name;
    Boolean delflag;

    @OneToMany(mappedBy = "role",cascade = CascadeType.ALL)
    @JsonManagedReference
    List<User> users;
}
