package com.swcat.giftapp.Models.giftpack;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class giftpackNewModel {
    private String gname;

    private String gdescription;

    private String thumbnailimg;

    private Integer price;

    private Integer averageStar;

    private Integer activeState;
}
