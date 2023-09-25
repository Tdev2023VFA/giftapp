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
@Table(name = "orderprocess")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class orderProcess {
    @Id
    @Column(name = "gorderid")
    private Integer gorderId;

    @Column(name = "pstate")
    private String pstate;

    @Column(name = "pdelivered")
    private Integer pdelivered;

    @Column(name = "pcanceled")
    private Integer pcanceled;
}
