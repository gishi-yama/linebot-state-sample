package com.example.sdbot.model;

import com.linecorp.bot.model.message.Message;
import com.linecorp.bot.model.message.TextMessage;

/**
 * 計算モード終了を表示するための、LineBotの返答メッセージ用のデータを表すモデル
 */
public class KeisanStopReplier implements Replier {
    @Override
    public Message reply() {
        return new TextMessage("計算スコープ終了：計算を終わります");
    }


}
