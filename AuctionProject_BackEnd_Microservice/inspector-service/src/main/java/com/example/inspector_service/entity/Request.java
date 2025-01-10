package com.example.inspector_service.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "request_db")
public class Request {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Column(columnDefinition = "NVARCHAR(MAX)", nullable = false)
    String name;
    @Column(columnDefinition = "NVARCHAR(MAX)", nullable = false)
    String description;
    Boolean verify;
    Boolean status;
    @ManyToOne()
    @JoinColumn(columnDefinition = "inspector_id",nullable = false,referencedColumnName = "id")
    Inspector inspector;//id nhà kiểm định
    Long userId; //id người yêu cầu kiểm định
    Long assetId; //id của sản phẩm kiểm định
    Boolean deflag;
}
