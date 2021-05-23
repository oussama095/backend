package com.doctor.backend.security.model;

import com.doctor.backend.model.AbstractEntity;
import com.doctor.backend.model.Patient;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Table
@EqualsAndHashCode(callSuper = true)
@Data
@ToString(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public class User extends AbstractEntity {

    @Column(unique = true)
    private String email;

    private String password;


    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE, CascadeType.REFRESH})
    @JoinTable(name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
    private List<Role> roles;

    private boolean enabled;

    @OneToOne(cascade = {CascadeType.MERGE, CascadeType.REFRESH}, fetch = FetchType.LAZY)
    private Patient patient;

}
