package com.example.sdbot.model;

import com.example.sdbot.model.Replier;
import com.linecorp.bot.model.event.MessageEvent;
import com.linecorp.bot.model.event.message.TextMessageContent;
import com.linecorp.bot.model.message.Message;
import com.linecorp.bot.model.message.TextMessage;

/**
 * オウム返しのメッセージを表示するための、LineBotのメッセージ用のデータを表すモデル
 */
public class Parrot implements Replier {

    // オウム返しのメッセージの元となる、LineBotからの要求
    private MessageEvent<TextMessageContent> event;

    public Parrot(MessageEvent<TextMessageContent> event) {
        this.event = event;
    }

    @Override
    public Message reply() {
        TextMessageContent tmc = this.event.getMessage();
        String text = tmc.getText();
        return new TextMessage(text);
    }
}
