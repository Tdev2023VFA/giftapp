package com.swcat.giftapp.Models.gPackComments;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class MailCommentModel {
    private String email;
    private String content;
    private String commentResolve;
    private Integer giftpackId;
}
