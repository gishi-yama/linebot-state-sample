package com.example.sdbot.model;

import com.linecorp.bot.model.message.Message;
import com.linecorp.bot.model.message.TextMessage;

/**
 * 合計値を表示するための、LineBotの返答メッセージ用のデータを表すモデル
 */
public class KeisanSumReplier implements Replier {

    private Integer sum;

    public KeisanSumReplier(Integer sum) {
        this.sum = sum;
    }

    @Override
    public Message reply() {
        return new TextMessage("合計 %d".formatted(sum));
    }

}
