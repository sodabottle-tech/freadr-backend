package com.sodabottle.freadr.models;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.envers.Audited;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "otp")
@Data
@NoArgsConstructor
@Audited
public class Otp {
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @NotNull(message = "{otp.notempty}")
    private Integer otp;

    @NotEmpty(message = "{mobile.notempty}")
    private String mobile;
    
    private Date createdAt;
    
    private boolean verified;
    
    private Date verifiedAt;

	public Otp(Integer otp, String mobile, Date createdAt) {
		this.otp = otp;
		this.mobile = mobile;
		this.createdAt = createdAt;
	}    
}