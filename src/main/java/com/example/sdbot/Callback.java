package com.example.sdbot;

import com.example.sdbot.model.*;
import com.linecorp.bot.model.event.MessageEvent;
import com.linecorp.bot.model.event.message.TextMessageContent;
import com.linecorp.bot.model.message.Message;
import com.linecorp.bot.spring.boot.annotation.EventMapping;
import com.linecorp.bot.spring.boot.annotation.LineMessageHandler;

import java.util.Optional;

/**
 * LineBotへの問いかけ（要求メッセージ）のControllerとなるクラス
 *
 * 要求として受け取った問いかけの内容によって、処理するモデル（≒ビジネスサービス）を切り替え、
 * 処理結果として得られたModelをLineBotの表示用データとして返答する。
 *
 * Viewは、Lineアプリ自体が行っているので、Controllerは返答メッセージ用のデータを返すだけである。
 * （フロントエンド/バックエンドに別れたアーキテクチャ）
 */
@LineMessageHandler
public class Callback {
    private final KeisanStarter keisanStarter;
    private final KeisanStopper keisanStopper;
    private final KeisanAnswer keisanAnswer;
    public Callback(KeisanStarter keisanStarter, KeisanStopper keisanStopper, KeisanAnswer keisanAnswer) {
        this.keisanStarter = keisanStarter;
        this.keisanStopper = keisanStopper;
        this.keisanAnswer = keisanAnswer;
    }

    /**
     * 文字列で問いかけられた時のControllerの返答処理
     *
     * @param event LineBotからの要求メッセージ
     * @return LineBotへの返答メッセージ用のデータ。
     * 計算モードOFFの時はオウム返しの返答、計算モードONの時は計算結果の返答となる。
     */
    @EventMapping
    public Message handleMessage(MessageEvent<TextMessageContent> event) {
        String message = event.getMessage().getText();

        Optional<Replier> replier = switch (message) {
            case "計算して" -> keisanStarter.start(event);
            case "計算やめて" -> keisanStopper.stop(event);
            default -> keisanAnswer.answer(event);
        };

        return replier.orElse(new Parrot(event)).reply();
    }
}
