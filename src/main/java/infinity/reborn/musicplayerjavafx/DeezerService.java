package infinity.reborn.musicplayerjavafx;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

public class DeezerService {
    private static final String API_KEY = "dc2f31cdb3mshf1cdbeb63da2320p1e6815jsn60a1e28fb3d7";
    private final OkHttpClient client = new OkHttpClient();

    public String getDeezerInfo() throws IOException {
        Request request = new Request.Builder()
                .url("https://deezerdevs-deezer.p.rapidapi.com/infos")
                .get()
                .addHeader("X-RapidAPI-Key", API_KEY)
                .addHeader("X-RapidAPI-Host", "deezerdevs-deezer.p.rapidapi.com")
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);
            return response.body().string();
        }
    }

    public String searchTracks(String query) throws IOException {
        Request request = new Request.Builder()
                .url("https://deezerdevs-deezer.p.rapidapi.com/search?q=" + query)
                .get()
                .addHeader("X-RapidAPI-Key", API_KEY)
                .addHeader("X-RapidAPI-Host", "deezerdevs-deezer.p.rapidapi.com")
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);
            return response.body().string();
        }
    }
}
