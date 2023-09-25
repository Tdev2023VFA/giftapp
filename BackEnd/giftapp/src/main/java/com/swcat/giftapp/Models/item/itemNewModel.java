package com.swcat.giftapp.Models.item;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class itemNewModel {
    private String itemName;

    private String itemDescription;

    private String thumbnailimg;

    private Integer price;

    private Integer activeState;
}
