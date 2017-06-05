/*
 * Copyright 2017 Manuel Wrage
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package com.ivianuu.spotifyextensions;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Interceptor which automatically handles rate limiting
 */

public class RateLimitingInterceptor implements Interceptor {

    private static final String RETRY_HEADER = "Retry-After";

    private int maxRetries;

    private RateLimitingInterceptor(Builder builder) {
        this.maxRetries = builder.maxRetries;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        // execute request
        Request request = chain.request();
        Response response = chain.proceed(request);

        int retry = 1;
        while (response.code() == 429 && retry <= maxRetries) {
            // extract seconds to wait from header
            int secondsToWait = 0;
            try {
                secondsToWait = Integer.valueOf(response.header(RETRY_HEADER)) + 1; // just to be on the save side
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }

            try {
                Thread.sleep(secondsToWait * 1000); // wait
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            // execute request again
            response = chain.proceed(request);

            // increase retry count
            retry++;
        }

        return response;
    }

    public static class Builder {

        private int maxRetries = 5; // default value

        /**
         * Max retries per request
         */
        public Builder maxRetries(int maxRetries) {
            this.maxRetries = maxRetries;
            return this;
        }

        public RateLimitingInterceptor build() {
            return new RateLimitingInterceptor(this);
        }
    }
}
