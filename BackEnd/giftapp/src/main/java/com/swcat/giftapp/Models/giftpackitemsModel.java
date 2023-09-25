package com.swcat.giftapp.Models;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class giftpackitemsModel {
    private int giftpackid;
    private List<Integer> itemid;

}
