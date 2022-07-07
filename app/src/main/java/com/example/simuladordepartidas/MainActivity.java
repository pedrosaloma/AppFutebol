package com.example.simuladordepartidas;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.simuladordepartidas.adapter.JogosAdapter;
import com.example.simuladordepartidas.data.GitHubApi;
import com.example.simuladordepartidas.databinding.ActivityMainBinding;
import com.example.simuladordepartidas.domain.Jogo;
import com.google.android.material.snackbar.Snackbar;

import java.util.Collections;
import java.util.List;
import java.util.Random;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private GitHubApi matchesApi;
    private JogosAdapter jogosAdapter;
    //private MatchesAdapter matchesAdapter = new MatchesAdapter(Collections.emptyList());

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setupHttpClient();
        setupMatchesList();
        setupMatchesRefresh();
        setupFloatingActionButton();
    }

    private void setupHttpClient() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://pedrosaloma.github.io/Simulador_Patidas_API/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        matchesApi = retrofit.create(GitHubApi.class);
    }

    private void setupMatchesList() {
        binding.RCVJogos.setHasFixedSize(true);
        binding.RCVJogos.setLayoutManager(new LinearLayoutManager(this)); // aguarda um contexto
        matchesApi();
    }

    private void matchesApi() {
        binding.SRLJogos.setRefreshing(true);// controle do loading
        matchesApi.getGames().enqueue(new Callback<List<Jogo>>() {
            @Override
            public void onResponse(@NonNull Call<List<Jogo>> call, @NonNull Response<List<Jogo>> response) {
                if (response.isSuccessful()) {
                    List<Jogo> matches = response.body();
                    jogosAdapter = new JogosAdapter(matches);
                    binding.RCVJogos.setAdapter(jogosAdapter);
                } else {
                    showErrorMessage();
                }
                binding.SRLJogos.setRefreshing(false);
            }

            @Override
            public void onFailure(@NonNull Call<List<Jogo>> call, @NonNull Throwable t) {
                showErrorMessage();
                binding.SRLJogos.setRefreshing(false);
            }
        });
    }

    private void setupMatchesRefresh() {
        binding.SRLJogos.setOnRefreshListener(this::matchesApi);
    }

    private void setupFloatingActionButton() {
        binding.FABGerar.setOnClickListener(view -> { // evento de lista para o botão flutuante
            view.animate().rotationBy(360).setDuration(500).setListener(new AnimatorListenerAdapter() { // animação para obotão rodar em determinado tempo
                @Override
                public void onAnimationEnd(Animator animation) {
                    Random random = new Random();
                    for (int i = 0; i < jogosAdapter.getItemCount(); i++) {
                        Jogo jogo = jogosAdapter.getJogo().get(i);
                        jogo.getMandante().setPlacar(random.nextInt(jogo.getMandante().getEstrelas() + 1));
                        jogo.getVisitante().setPlacar(random.nextInt(jogo.getVisitante().getEstrelas() + 1));
                        jogosAdapter.notifyItemChanged(i);
                    }
                }
            });
        });
    }



    private void showErrorMessage() {
        Snackbar.make(binding.FABGerar, R.string.error_api, Snackbar.LENGTH_LONG).show();
    }
}
