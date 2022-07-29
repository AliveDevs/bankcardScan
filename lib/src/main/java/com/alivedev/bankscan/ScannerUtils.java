package com.alivedev.bankscan;

import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.text.TextUtils;

import com.wintone.bankcard.BankCardAPI;


/**
 * 识别工具类
 */
public class ScannerUtils {


    /**
     * 识别银行卡，建议在子线程运行，识别率很低
     *
     * @param bmp
     * @return
     */
    public static String decodeBank(Bitmap bmp) throws Exception {
        if (bmp == null) return null;
        if (bmp.getWidth() % 2 == 1 || bmp.getHeight() % 2 == 1) {
            bmp = Bitmap.createBitmap(bmp, 0, 0, bmp.getWidth() / 2 * 2, bmp.getHeight() / 2 * 2);
        }
        int width = bmp.getWidth();
        int height = bmp.getHeight();
        BankCardAPI api = BankCardUtils.init();
        try {
            byte[] data = Utils.bitmapToNv21(bmp);
            String s = BankCardUtils.decode(api, data, width, height);
            if (TextUtils.isEmpty(s)) throw new Exception("failure");
            BankCardUtils.release(api);
            return s;
        } catch (Exception e) {}
        try {
            Matrix m = new Matrix();
            m.setRotate(90, width / 2, height / 2);
            bmp = Bitmap.createBitmap(bmp, 0, 0, width, height, m, true);
            byte[] data = Utils.bitmapToNv21(bmp);
            String s = BankCardUtils.decode(api, data, height, width);
            if (TextUtils.isEmpty(s)) throw new Exception("failure");
            BankCardUtils.release(api);
            return s;
        } catch (Exception e) {}
        BankCardUtils.release(api);
        throw new Exception("failure");
    }

    /**
     * 获取银行卡信息，请在子线程运行
     *
     * @param cardNumber 银行卡号
     * @return
     */
    public static BankCardInfoBean getBankCardInfo(String cardNumber) {
        return BankCardUtils.getBankCardInfo(cardNumber);
    }

}
