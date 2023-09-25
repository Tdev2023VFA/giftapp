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
@Table(name = "giftpackcomments")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class giftpackComments {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cid")
    private Integer cid;

    @Column(name = "giftpackid")
    private Integer giftpackId;

    @Column(name = "uname")
    private String uname;

    @Column(name = "content")
    private String content;
}
