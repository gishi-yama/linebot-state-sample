package com.example.sdbot.model;

import com.linecorp.bot.model.event.MessageEvent;
import com.linecorp.bot.model.event.message.TextMessageContent;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * 計算モードをスタートするModel（ビジネスサービスの一部）
 *
 * ユーザーIDをキーとして、ステートフルなスコープを終了する。
 */
@Service
public class KeisanStopper {

    private final BotState botState;

    public KeisanStopper(BotState botState) {
        this.botState = botState;
    }

    public Optional<Replier> stop(MessageEvent<TextMessageContent> event) {
        String userId = event.getSource().getUserId();
        botState.offKeisan(userId);
        return Optional.of(new KeisanStopReplier());
    }
}
