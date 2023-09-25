package com.swcat.giftapp.Models.delivery;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class orderProcessModel {
    private Integer gorderId;
    private String pstate;
    private Integer pdelivered;
    private Integer pcanceled;
}
