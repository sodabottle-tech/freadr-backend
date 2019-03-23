package com.sodabottle.freadr.models;

import com.sodabottle.freadr.enums.UserType;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.envers.Audited;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

@Entity(name = "user")
@Data
@Audited
@NoArgsConstructor
public class UserEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "type", nullable = false)
    private UserType userType;

    //@NotEmpty(message = "{firstName.notempty}")
    private String firstName;

    //@NotEmpty(message = "{lastName.notempty}")
    private String lastName;

    @Column(name = "image_url")
    private String imageUrl;

    @Email
    @NotEmpty(message = "{email.notempty}")
    private String email;

    @Column(name = "external_id", nullable = false, unique = true)
    private String externalId;

    @Column(name = "external_profile_url")
    private String externalProfileUrl;

    @Column(name = "external_token")
    private String externalToken;
}
