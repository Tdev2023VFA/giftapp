package com.swcat.giftapp.Entities;

import java.util.Date;

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
@Table(name = "gorder")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class gorder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cid")
    private Integer cid;

    @Column(name = "uname")
    private String uname;

    @Column(name = "giftpackid")
    private Integer giftpackId;

    @Column(name = "receiveaddress")
    private String receiveAddress;

    @Column(name = "phonenumber")
    private Integer phoneNumber;

    @Column(name = "dateorder")
    private Date dateOrder;

    @Column(name = "datearrive")
    private Date dateArrive;

    @Column(name = "greetingcardcontent")
    private String greetingCardContent;
}
