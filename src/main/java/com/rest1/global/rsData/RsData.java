package com.rest1.global.rsData;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class RsData {   //resultData라는 의미다.

    private String resultCode;
    private String msg; //메세지
    private Object data;    //삭제할 때 comment를 보여줄 것이다.
}
