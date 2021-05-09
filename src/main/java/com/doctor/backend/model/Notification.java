package com.doctor.backend.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "notification")
public class Notification extends AbstractEntity {

    private String title;

    private String message;

    private Boolean isRead;

    @Column(name = "created_at", nullable = false, updatable = false)
    @CreatedDate
    private Date createdAt;

}
