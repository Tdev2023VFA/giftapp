package com.swcat.giftapp.Models.addon;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class GOrderAddOnModel {
    private Integer cid;
    private Integer gorderId;
    private Integer addonId;
    private String content;
    private String aname;
    private String adescription;
    private Integer price;
}
