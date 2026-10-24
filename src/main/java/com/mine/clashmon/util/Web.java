package com.mine.clashmon.util;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Web {
    public static ClashResult getClashResult(String url) {
        OkHttpClient client = new OkHttpClient()
                .newBuilder()
//                .addInterceptor(new RetryInterceptor(3, 3000))
                .build();
        Request req = new Request.Builder()
                .header("User-Agent", "clash")
                .url(url)
                .head()
                .build();

        try (Response resp = client.newCall(req).execute()) {
            int code = resp.code();
            System.out.println("StatusCode: " + code);

            // 遍历全部header
            System.out.println("\n=== All Headers ===");
            for (int i = 0; i < resp.headers().size(); i++) {
                String name = resp.headers().name(i);
                String value = resp.headers().value(i);
                System.out.println(name + ": " + value);
            }

            // 单独取
            final String userInfo = resp.header("subscription-userinfo");
            final String disp = resp.header("content-disposition");
            System.out.println("\nsubscription-userinfo: " + userInfo);
            System.out.println("content-disposition: " + disp);
            /**
             *
             * upload=799532839; download=44214770768; total=322122547200; expire=1792652654
             *
             */
            if (userInfo == null) {
                return new ClashResult(0, 0, 0);
            }
            long upload = 0;
            long download = 0;
            long total = 0;
// 按分号+空格切分，兼容标准 subscription-userinfo 格式
            String[] parts = userInfo.split("; ");
            for (String part : parts) {
                String[] kv = part.split("=");
                if (kv.length != 2) continue;
                String key = kv[0].trim();
                String val = kv[1].trim();
                try {
                    long num = Long.parseLong(val);
                    switch (key) {
                        case "upload": upload = num; break;
                        case "download": download = num; break;
                        case "total": total = num; break;
                        // expire 你暂时不用，可以忽略
                    }
                } catch (NumberFormatException ignored) {
                    // 数字解析失败直接跳过，容错
                }
            }
            System.out.println("upload: " + upload + ", download: " + download + ", total: " + total);
            return new ClashResult(upload, download, total);
        } catch (Exception e) {
            e.printStackTrace();
            return new ClashResult(0, 0, 0);
        }
    }
}
