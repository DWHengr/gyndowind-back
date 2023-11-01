package com.dwh.gyndowindback.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Util {
    public static String encrypt(String input) {
        try {
            // 创建MessageDigest对象，指定算法为MD5
            MessageDigest md = MessageDigest.getInstance("MD5");
            // 将输入字符串转换为字节数组
            byte[] inputBytes = input.getBytes();
            // 计算MD5摘要
            byte[] digest = md.digest(inputBytes);
            // 将字节数组转换为十六进制字符串
            StringBuilder hexString = new StringBuilder();
            for (byte b : digest) {
                hexString.append(String.format("%02x", b));
            }
            // 截取前32位，即取前16个字节
            return hexString.toString().substring(0, 32);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }

}
