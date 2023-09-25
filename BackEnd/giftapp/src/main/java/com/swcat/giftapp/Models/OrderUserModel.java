package com.swcat.giftapp.Models;

import java.util.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderUserModel {
    private Integer cid;
    private String uname;
    private Integer giftpackId;
    private String receiveAddress;
    private Integer phoneNumber;
    private String dateOrder;
    private String dateArrive;
    private String greetingCardContent;
    private List<Integer> addonId;
    private List<String> content;
    private String status;
    private int cost;
}
