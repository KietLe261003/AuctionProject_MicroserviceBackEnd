package com.example.inspector_service.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "inspector_db")
public class Inspector {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String license;
    Long userId;

    @OneToMany(mappedBy = "inspector",cascade = CascadeType.ALL)
    @JsonManagedReference
    List<Request> requestList;
}
