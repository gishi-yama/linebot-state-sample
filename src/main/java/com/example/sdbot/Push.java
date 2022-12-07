package com.example.sdbot;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;


/**
 * ブラウザからの要求のControllerとなるクラス
 */
@RestController
public class Push {

    /**
     * ブラウザで https://.../test にアクセスした時に起動するコントローラー
     * @param request 要求情報
     * @return ブラウザに表示される、アクセス確認用のテキストデータ
     */
    @GetMapping("test")
    public String hello(HttpServletRequest request) {
        return "Get from " + request.getRequestURL();
    }
}
