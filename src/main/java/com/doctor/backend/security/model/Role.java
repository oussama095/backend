package com.doctor.backend.security.model;

import com.doctor.backend.model.AbstractEntity;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@EqualsAndHashCode(callSuper = true)
@Data
@ToString(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public class Role extends AbstractEntity {

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private ERole name;

//    @ManyToMany(mappedBy = "roles")
//    private List<User> users;

}
