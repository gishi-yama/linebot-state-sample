package com.example.sdbot.model;


import com.linecorp.bot.model.message.Message;

public interface Replier {

    /**
     * 返答メッセージ様のモデルが必ず実装するメソッド
     *
     * @return LineBotのメッセージ用のデータ
     */
    Message reply();

}
