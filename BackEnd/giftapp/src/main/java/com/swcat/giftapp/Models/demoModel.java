package com.swcat.giftapp.Models;

import javax.xml.bind.annotation.XmlRootElement;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@XmlRootElement(name = "demoModel")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class demoModel {
    private int meoid;
    private String meoname;
    private String meosound;
}
