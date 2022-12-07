package com.example.sdbot.model;

import com.linecorp.bot.model.event.MessageEvent;
import com.linecorp.bot.model.event.message.TextMessageContent;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * 計算モードをスタートするModel（ビジネスサービスの一部）
 *
 * ユーザーIDをキーとして、ステートフルなスコープを開始する。
 */
@Service
public class KeisanStarter {

    private final BotState botState;


    public KeisanStarter(BotState botState) {
        this.botState = botState;
    }

    /**
     * 計算モードを開始し、通知用のメッセージを作成する
     *
     * @param event LineBotから送信されたテキストメッセージイベント
     * @return 返答メッセージ用のデータ（存在しない場合は空）
     */
    public Optional<Replier> start(MessageEvent<TextMessageContent> event) {
        String userId = event.getSource().getUserId();
        botState.onKeisan(userId);
        return Optional.of(new KeisanStertReplier());
    }
}

