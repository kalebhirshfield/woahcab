package com.kalebhirshfield.woahcab.utils;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import okhttp3.*;

import java.io.IOException;

public class SupabaseAuth {
    private static final String SUPABASE_URL = "https://glrdugvzjprdipllqdch.supabase.co";
    private static final String SUPABASE_ANON_KEY = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6ImdscmR1Z3Z6anByZGlwbGxxZGNoIiwicm9sZSI6ImFub24iLCJpYXQiOjE3NjI4ODMxNzQsImV4cCI6MjA3ODQ1OTE3NH0.tdDqJKrTWfr_-7g3ouReb-xjKbwQ6tt9ugxMKKoaveQ";
    private static final MediaType JSON = MediaType.get("application/json");

    private static final OkHttpClient client = new OkHttpClient();
    private static final Gson gson = new Gson();

    private static String accessToken;
    private static String userId;

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

        return isValidResponse(request);
    }

    public static boolean signUp(String username, String email, String password) {
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

        if (isValidResponse(request)) {
            JsonObject user = new JsonObject();
            user.addProperty("name", username);

            try {
                SupabaseClient.insert("profiles", user, accessToken);
                return true;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return false;
    }

    public static void signOut() {
        Request request = new Request.Builder()
                .url(SUPABASE_URL + "/auth/v1/logout")
                .post(RequestBody.create(new byte[0], null))
                .addHeader("apikey", SUPABASE_ANON_KEY)
                .addHeader("Authorization", "Bearer " + accessToken)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (response.isSuccessful()) {
                accessToken = null;
                userId = null;
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static boolean isValidResponse(Request request) {
        try (Response response = client.newCall(request).execute()) {
            if (response.isSuccessful()) {
                JsonObject result = gson.fromJson(response.body().string(), JsonObject.class);
                accessToken = result.get("access_token").getAsString();
                JsonObject user = result.getAsJsonObject("user");
                userId = user.get("id").getAsString();

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

    public static String getUserId() {
        return userId;
    }
}
