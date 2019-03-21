package com.sodabottle.freadr.models;

import com.sodabottle.freadr.enums.UserType;
import lombok.Data;
import org.hibernate.envers.Audited;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Entity(name = "user")
@Data
@Audited
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "type", nullable = false)
    private UserType userType;

    @NotEmpty(message = "{firstName.notempty}")
    private String firstName;

    @NotEmpty(message = "{lastName.notempty}")
    private String lastName;

    private String picUrl;

    @Email
    @NotEmpty(message = "{email.notempty}")
    private String email;

    private boolean claimed;

    private boolean deleted;


}
