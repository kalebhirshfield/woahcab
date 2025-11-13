package com.kalebhirshfield.woahcab.utils;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import okhttp3.*;

import java.io.IOException;

public class SupabaseClient {
    private static final String SUPABASE_URL = "https://glrdugvzjprdipllqdch.supabase.co";
    private static final String SUPABASE_ANON_KEY = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6ImdscmR1Z3Z6anByZGlwbGxxZGNoIiwicm9sZSI6ImFub24iLCJpYXQiOjE3NjI4ODMxNzQsImV4cCI6MjA3ODQ1OTE3NH0.tdDqJKrTWfr_-7g3ouReb-xjKbwQ6tt9ugxMKKoaveQ";
    private static final MediaType JSON = MediaType.get("application/json");

    private static final OkHttpClient client = new OkHttpClient();
    private static final Gson gson = new Gson();

    public static JsonArray select(String table, String accessToken) throws IOException {
        Request request = new Request.Builder()
                .url(SUPABASE_URL + "/rest/v1/" + table)
                .get()
                .addHeader("apikey", SUPABASE_ANON_KEY)
                .addHeader("Authorization", "Bearer " + accessToken)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (response.isSuccessful() && response.body() != null) {
                return gson.fromJson(response.body().string(), JsonArray.class);
            }
            throw new IOException("Request failed: " + response.code());
        }
    }

    public static JsonObject insert(String table, JsonObject data, String accessToken) throws IOException {
        RequestBody body = RequestBody.create(data.toString(), JSON);
        Request request = new Request.Builder()
                .url(SUPABASE_URL + "/rest/v1/" + table)
                .post(body)
                .addHeader("apikey", SUPABASE_ANON_KEY)
                .addHeader("Authorization", "Bearer " + accessToken)
                .addHeader("Content-Type", "application/json")
                .addHeader("Prefer", "return=representation")
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (response.isSuccessful() && response.body() != null) {
                return gson.fromJson(response.body().string(), JsonObject.class);
            }
            throw new IOException("Insert failed: " + response.code());
        }
    }

    public static void delete(String table, String filter, String accessToken) throws IOException {
        Request request = new Request.Builder()
                .url(SUPABASE_URL + "/rest/v1/" + table + "?" + filter)
                .delete()
                .addHeader("apikey", SUPABASE_ANON_KEY)
                .addHeader("Authorization", "Bearer " + accessToken)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new IOException("Delete failed: " + response.code());
            }
        }
    }
}
