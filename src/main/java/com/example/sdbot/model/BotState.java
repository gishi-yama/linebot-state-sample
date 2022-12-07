package com.example.sdbot.model;

import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

/**
 * ユーザーIDをキーとして、ユーザーIDごとに記録するステートフルな情報を管理するデータ管理用のモデル。
 * ユーザーIDごとのモードの記憶とその切り替え、ユーザーIDごとの計算値の記憶とその再計算を担当している。
 * <p>
 * この例では単に、インスタンスの中にデータを記録しているだけだが、
 * 実際には外部ファイルや、Key-Valueデータモデルを用いるデータベース（データ層）等にアクセスする様な事例もある。
 */
@Repository
public class BotState {

    private final Map<String, BotMode> userState;
    private final Map<String, Integer> userResult;

    public BotState() {

        // ユーザーIDごとのモードを保持
        this.userState = new HashMap<>();
        // ユーザーIDごとの計算結果を保持
        this.userResult = new HashMap<>();
    }

    /**
     * 計算モードをON（セッションスコープの開始）
     *
     * @param userId キーとなるユーザーID
     */
    public void onKeisan(String userId) {
        userState.put(userId, BotMode.Keisan);
        userResult.put(userId, 0);
    }

    /**
     * 計算モードをOFF（セッションスコープを破棄）
     *
     * @param userId キーとなるユーザーID
     */
    public void offKeisan(String userId) {
        userState.remove(userId);
        userResult.remove(userId);
    }

    /**
     * 計算モードかどうかを調べる
     *
     * @param userId キーとなるユーザーID
     * @return 計算モードであれば true, それ以外は false;
     */
    public boolean isKeisanMode(String userId) {
        BotMode mode = userState.get(userId);
        return mode != null && mode.equals(BotMode.Keisan);
    }

    /**
     * 計算値を再計算し、最新の計算値を返す
     *
     * @param userId キーとなるユーザーID
     * @param addend 再計算に使う加算値
     * @return 最新の計算値
     */
    public Integer sum(String userId, int addend) {
        Integer augend = userResult.get(userId);
        Integer sum = augend + addend;
        userResult.put(userId, sum);
        return sum;
    }


}
