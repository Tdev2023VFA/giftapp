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
@Table(name = "giftpack")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class giftpack {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cid")
    private Integer cid;

    @Column(name = "gname")
    private String gname;

    @Column(name = "gdescription")
    private String gdescription;

    @Column(name = "thumbnailimg")
    private String thumbnailimg;

    @Column(name = "price")
    private Integer price;

    @Column(name = "averagestar")
    private Integer averageStar;

    @Column(name = "activestate")
    private Integer activeState;
}
