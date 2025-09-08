package com.rest1.global.rsData;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class RsData<T> {   //resultData라는 의미다.

    private String resultCode;
    private String msg; //메세지
    private T data;    //삭제할 때 comment를 보여줄 것이다.
                    //T는 Object와 같지만 하나 차이가 있다.
                    // 객체를 생성할때 해당 T가 무엇인지 강제로 작성해야한다.

    public RsData(String resultCode, String msg) {  //부가 데이터가 필요없는 경우
        this.resultCode = resultCode;
        this.msg = msg;
        this.data = null;

    }
}
