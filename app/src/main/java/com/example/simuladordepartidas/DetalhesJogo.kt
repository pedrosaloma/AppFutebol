package com.example.simuladordepartidas

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.simuladordepartidas.databinding.ActivityDetalhesJogoBinding
import com.example.simuladordepartidas.domain.Jogo

class DetalhesJogo : AppCompatActivity() {

    object Extras{
        const val MATCH = "EXTRA_MATCH"
    }
    private lateinit var binding: ActivityDetalhesJogoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetalhesJogoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true);

        loadJogosFromExtra()
    }

    private fun loadJogosFromExtra() {
        intent?.extras?.getParcelable<Jogo>(Extras.MATCH)?.let {
            Glide.with(this).load(it.local.imagem).into(binding.ivPlace)
            supportActionBar?.title = it.local.nome

            binding.tvDescription.text = it.descricao

            if (it.mandante.placar != null) {
                binding.tvHomeTeamName.text = it.mandante.nome
                Glide.with(this).load(it.mandante.imagem).into(binding.ivHomeTeam)
                binding.rbHomeTeamStars.rating = it.mandante.estrelas.toFloat()
            }

            if (it.visitante.placar != null) {
                binding.tvAwayTeamName.text = it.visitante.nome
                Glide.with(this).load(it.visitante.imagem).into(binding.ivAwayTeam)
                binding.rbAwayTeamStars.rating = it.visitante.estrelas.toFloat()
            }
        }
    }
}