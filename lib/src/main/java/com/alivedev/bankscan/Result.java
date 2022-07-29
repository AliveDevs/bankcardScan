package com.alivedev.bankscan;

/**
 * 识别结果
 *
 */
public class Result {

    // 图片路径
    public String path;
    // 数据
    public String data;

    @Override
    public String toString() {
        return data;
    }
}
