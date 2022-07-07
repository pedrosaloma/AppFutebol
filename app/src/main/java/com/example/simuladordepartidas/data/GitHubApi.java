package com.example.simuladordepartidas.data;

import com.example.simuladordepartidas.domain.Jogo;

import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;

public interface GitHubApi {

    @GET("partidas.json")
    Call<List<Jogo>> getGames();

}
