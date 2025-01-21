package org.example.foodie.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.*;
import org.example.foodie.dto.TransactionDto;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class BankApiService {
    private static final MediaType JSON = MediaType.get("application/json; charset=utf-8");
    private static final String BASE_URL = "http://localhost:8081";
    private static final String json = "{\"usernameOrEmail\":\"mary\", \"password\":\"12345\"}";
    private static final OkHttpClient client = new OkHttpClient();
    private String apiKey;

    public BankApiService() {
        RequestBody body = RequestBody.create(json, JSON);

        Request request = new Request.Builder()
                .url(BASE_URL+ "/auth/login")
                .post(body)
                .build();

        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            public void onResponse(Call call, Response response)
                    throws IOException {
                System.out.println("Response Code: " + response.code());
                apiKey = response.body().string();
                System.out.println("Response Body: " + apiKey);
            }

            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }
        });
    }

    public void generateOtp(int cardId) {
        Request request = new Request.Builder()
                .addHeader("Authorization", "Bearer " + apiKey)
                .url(BASE_URL+ "/api/bank/generate/" + cardId)
                .build();

        makeCall(request);
    }

    public void doTransaction(TransactionDto transactionDto) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();

        String json = objectMapper.writeValueAsString(transactionDto);
        RequestBody body = RequestBody.create(json, JSON);

        Request request = new Request.Builder()
                .addHeader("Authorization", "Bearer " + apiKey)
                .url(BASE_URL+ "/api/bank/transaction")
                .post(body)
                .build();

        makeCall(request);
    }

    private static void makeCall(Request request) {
        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            public void onResponse(Call call, Response response)
                    throws IOException {
                if(response.isSuccessful()) {
                    System.out.println("Response Code: " + response.code());
                    System.out.println("Response Body: " + response.body().string());
                } else {
                    throw new RuntimeException(response.message());
                }
            }
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }
        });
    }
}
