/*
 * Copyright 2021 silot.ai
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package ai.silot.taurus.util;

import ai.silot.taurus.config.Taurus;
import com.google.gson.Gson;

import java.io.IOException;
import java.lang.reflect.Type;

public class TaurusHttpUtil {
    private static String getAuthorization() {
        return "Basic " + Base64.encode(Taurus.apiKey + ":");
    }

    public static <T> T get(String url, Type type) throws IOException {
        String response = HttpRequest.get(url)
                .header("Authorization", getAuthorization())
                .execute().body();
        return new Gson().fromJson(response, type);
    }

    public static <T> T post(String url, Type type, String jsonBody) throws IOException {
        String response = HttpRequest.post(url)
                .header("Authorization", getAuthorization())
                .body(jsonBody)
                .execute().body();
        return new Gson().fromJson(response, type);
    }
}
