package com.swcat.giftapp.Models.gPackComments;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class gPackCommentsModel {
    private Integer cid;
    private Integer giftpackId;
    private String uname;
    private String content;
}
