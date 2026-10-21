package com.mine.clashmon.util;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

public class RetryInterceptor implements Interceptor {
    private final int maxRetry;
    private final long sleepMs;

    public RetryInterceptor(int maxRetry, long sleepMs) {
        this.maxRetry = maxRetry;
        this.sleepMs = sleepMs;
    }

    @NotNull
    @Override
    public Response intercept(@NotNull Chain chain) throws IOException {
        Request request = chain.request();
        Response response = null;
        IOException exception = null;

        for (int i = 0; i <= maxRetry; i++) {
            try {
                response = chain.proceed(request);
                // 5xx 服务错误，需要重试
                if (response.code() < 500) {
                    return response;
                }
                // 5xx，关闭response，准备重试
                response.close();
            } catch (IOException e) {
                exception = e;
            }
            // 不是最后一次，休眠后重试
            if (i < maxRetry) {
                try {
                    Thread.sleep(sleepMs);
                } catch (InterruptedException ie) {
                    Thread.currentThread().interrupt();
                    throw new IOException(ie);
                }
            }
        }
        // 重试耗尽
        if (exception != null) {
            throw exception;
        }
        return response;
    }
}