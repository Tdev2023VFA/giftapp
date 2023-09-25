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
@Table(name = "account")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class account {
    @Id
    @Column(name = "uname")
    private String uname;
    
    @Column(name = "passwd")
    private String passwd;

    @Column(name = "acctype")
    private Integer acctype;
}
