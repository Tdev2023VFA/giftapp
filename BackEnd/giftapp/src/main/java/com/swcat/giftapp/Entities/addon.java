package com.swcat.giftapp.Entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "addon")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class addon {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cid")
    private Integer cid;

    @Column(name = "aname")
    private String aname;
    
    @Column(name = "adescription")
    private String adescription;

    @Column(name = "price")
    private Integer price;
}
