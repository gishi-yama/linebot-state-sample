package com.example.sdbot.model;

import com.linecorp.bot.model.message.Message;
import com.linecorp.bot.model.message.TextMessage;

/**
 * 計算モード開始を表示するための、LineBotの返答メッセージ用のデータを表すモデル
 */
public class KeisanStertReplier implements Replier {
    @Override
    public Message reply() {
        return new TextMessage("計算スコープ開始：数字をどんどん足していきます");
    }
}
