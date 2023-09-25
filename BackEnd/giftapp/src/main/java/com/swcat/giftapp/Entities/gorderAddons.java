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
@Table(name = "gorderaddons")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class gorderAddons {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cid")
    private Integer cid;

    @Column(name = "gorderid")
    private Integer gorderId;

    @Column(name = "addonid")
    private Integer addonId;

    @Column(name = "content")
    private String content;
}
