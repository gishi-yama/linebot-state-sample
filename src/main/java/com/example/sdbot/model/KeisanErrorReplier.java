package com.example.sdbot.model;

import com.linecorp.bot.model.message.Message;
import com.linecorp.bot.model.message.TextMessage;

/**
 * 計算中の入力エラーを表示するための、LineBotの返答メッセージ用のデータを表すモデル
 */
public class KeisanErrorReplier implements Replier {
    @Override
    public Message reply() {
        return new TextMessage("計算スコープ中：数字を入れてください");
    }
}
