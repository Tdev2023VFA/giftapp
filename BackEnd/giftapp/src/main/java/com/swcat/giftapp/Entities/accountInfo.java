package com.swcat.giftapp.Entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "accountinfo")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class accountInfo {
    @Id
    @Column(name = "uname")
    private String uname;

    @Column(name = "disname")
    private String disName;

    @Column(name = "uaddress")
    private String uaddress;

    @Column(name = "phonenum")
    private Integer phonenum;

    @Column(name = "email")
    private String email;
}
