package com.kalebhirshfield.woahcab.utils;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import okhttp3.*;

import java.io.IOException;

public class SupabaseAuth {
    private static final String SUPABASE_URL = "https://glrdugvzjprdipllqdch.supabase.co";
    private static final String SUPABASE_ANON_KEY = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6ImdscmR1Z3Z6anByZGlwbGxxZGNoIiwicm9sZSI6ImFub24iLCJpYXQiOjE3NjI4ODMxNzQsImV4cCI6MjA3ODQ1OTE3NH0.tdDqJKrTWfr_-7g3ouReb-xjKbwQ6tt9ugxMKKoaveQ";
    private static final MediaType JSON = MediaType.get("application/json");

    private static OkHttpClient client = new OkHttpClient();
    private static final Gson gson = new Gson();

    private static String accessToken;
    private static String refreshToken;

    public static boolean signIn(String email, String password) {
        JsonObject json = new JsonObject();
        json.addProperty("email", email);
        json.addProperty("password", password);

        RequestBody body = RequestBody.create(json.toString(), JSON);
        Request request = new Request.Builder()
                .url(SUPABASE_URL + "/auth/v1/token?grant_type=password")
                .post(body)
                .addHeader("apikey", SUPABASE_ANON_KEY)
                .addHeader("Content-Type", "application/json")
                .build();

        return Response(request);
    }

    public static boolean signUp(String email, String password) {
        JsonObject json = new JsonObject();
        json.addProperty("email", email);
        json.addProperty("password", password);

        RequestBody body = RequestBody.create(json.toString(), JSON);
        Request request = new Request.Builder()
                .url(SUPABASE_URL + "/auth/v1/signup")
                .post(body)
                .addHeader("apikey", SUPABASE_ANON_KEY)
                .addHeader("Content-Type", "application/json")
                .build();

        return Response(request);
    }

    private static boolean Response(Request request) {
        try (Response response = client.newCall(request).execute()) {
            if (response.isSuccessful() && response.body() != null) {
                JsonObject result = gson.fromJson(response.body().string(), JsonObject.class);
                accessToken = result.get("access_token").getAsString();
                refreshToken = result.get("refresh_token").getAsString();
                return true;
            }
            return false;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static String getAccessToken() {
        return accessToken;
    }

    public static String getRefreshToken() {
        return refreshToken;
    }
}
