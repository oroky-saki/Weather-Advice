package com.weather.weatheradviceapp.service;

import com.weather.weatheradviceapp.util.JsonReader;
import okhttp3.*;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class AdviceService {

    private final JsonReader jsonReader;

    public AdviceService(JsonReader jsonReader) {
        this.jsonReader = jsonReader;
    }

    public String getAdvice(String weather) {

        String url = "https://api.openai.com/v1/chat/completions";

        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(100, TimeUnit.SECONDS)
                .callTimeout(100, TimeUnit.SECONDS)
                .readTimeout(100, TimeUnit.SECONDS)
                .writeTimeout(100, TimeUnit.SECONDS)
                .build();

        String responseBody = "Default response body by chatGTP";

        Request request = new Request.Builder()
                .url(url)
                .header("Authorization", "Bearer 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")
                .header("Content-Type", "application/json")
                .post(buildRequestBody(weather))
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (response.isSuccessful()) {
                responseBody = jsonReader.readContent(response.body().string());
                return responseBody;
            }
        } catch (Exception e) {
            return e.getMessage();
        }

        return responseBody;

    }

    public RequestBody buildRequestBody(String weather) {

        String content = "Проанализируй данные о погоде и дай краткий совет на русском как лучше одеться в 50 словах: " + weather;

        String requestBody = "{\n" +
                "    \"model\": \"gpt-3.5-turbo\",\n" +
                "    \"messages\": [\n" +
                "      {\n" +
                "        \"role\": \"system\",\n" +
                "        \"content\": \"" + content + "\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"role\": \"user\",\n" +
                "        \"content\": \"Hello!\"\n" +
                "      }\n" +
                "    ]\n" +
                "}";

        MediaType mediaType = MediaType.parse("application/json; charset=utf-8");

        return RequestBody.create(requestBody, mediaType);
    }
}
