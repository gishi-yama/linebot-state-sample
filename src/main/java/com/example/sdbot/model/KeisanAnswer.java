package com.example.sdbot.model;

import com.linecorp.bot.model.event.MessageEvent;
import com.linecorp.bot.model.event.message.TextMessageContent;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * 計算モードの中で計算を行うModel（ビジネスサービスの一部）
 *
 * ユーザーIDをキーとして、これまでの合計値に新たな値を加算する。
 */
@Service
public class KeisanAnswer {

    // ステート管理用のインスタンス
    private final BotState botState;

    public KeisanAnswer(BotState botState) {
        this.botState = botState;
    }

    /**
     * 計算を行い、計算結果のメッセージを作成する
     *
     * @param event LineBotから送信されたテキストメッセージイベント
     * @return 返答メッセージ用のデータ（存在しない場合は空）
     */
    public Optional<Replier> answer(MessageEvent<TextMessageContent> event) {
        String userId = event.getSource().getUserId();
        if (botState.isKeisanMode(userId)) {
            String message = event.getMessage().getText();
            try {
                Integer added = Integer.parseInt(message);
                Integer sum = botState.sum(userId, added);
                return Optional.of(new KeisanSumReplier(sum));
            } catch (NumberFormatException e) {
                return Optional.of(new KeisanErrorReplier());
            }
        }
        return Optional.empty();
    }
}