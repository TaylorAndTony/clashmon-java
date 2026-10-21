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
                .addInterceptor(new RetryInterceptor(3, 3000))
                .build();
        Request req = new Request.Builder()
                .url(url)
                .get()
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
            String userInfo = resp.header("subscription-userinfo");
            String disp = resp.header("content-disposition");
            System.out.println("\nsubscription-userinfo: " + userInfo);
            System.out.println("content-disposition: " + disp);
            /**
             *
             * upload=799532839; download=44214770768; total=322122547200; expire=1792652654
             *
             */
            final Pattern pattern = Pattern.compile("upload=(\\d+); download=(\\d+); total=(\\d+); expire=(\\d+)");
            Matcher matcher = pattern.matcher(userInfo);
            if (matcher.find()) {
                long upload = Long.parseLong(matcher.group(1));
                long download = Long.parseLong(matcher.group(2));
                long total = Long.parseLong(matcher.group(3));
                long expire = Long.parseLong(matcher.group(4));
                System.out.println("upload: " + upload + ", download: " + download + ", total: " + total + ", expire: " + expire);
                return new ClashResult(upload, download, total);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
