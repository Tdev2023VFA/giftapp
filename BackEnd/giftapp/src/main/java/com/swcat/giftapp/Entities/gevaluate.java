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
@Table(name = "gevaluate")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class gevaluate {
    @Id
    @Column(name = "cid")
    private Integer cid;

    @Column(name = "uname")
    private String uname;

    @Column(name = "star")
    private Integer star;
}
