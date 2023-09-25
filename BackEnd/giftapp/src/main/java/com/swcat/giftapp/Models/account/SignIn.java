package com.swcat.giftapp.Models.account;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SignIn {
    private String username;
    private String password;
    private Integer accountType;
}
