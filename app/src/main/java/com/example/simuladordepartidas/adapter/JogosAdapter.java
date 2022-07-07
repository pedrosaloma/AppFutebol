package com.example.simuladordepartidas.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.simuladordepartidas.DetalhesJogo;
import com.example.simuladordepartidas.databinding.JogoItemBinding;
import com.example.simuladordepartidas.domain.Jogo;

import java.util.List;

public class JogosAdapter extends RecyclerView.Adapter<JogosAdapter.ViewHolder> {

    private List<Jogo> Jogo;

    public List<Jogo> getJogo() {
        return Jogo;
    }

    public JogosAdapter(List<Jogo> jogo) { // recebe a lista de partidas como parametro
        Jogo = jogo;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext()); // pegando o layout do contexto parent
        JogoItemBinding binding = JogoItemBinding.inflate(layoutInflater,parent, false);// pegando elemtentos de tela através do inflater
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Context context = holder.itemView.getContext(); // pegando o contexto para usar no Glide
        Jogo match = Jogo.get(position);

        Glide.with(context).load(match.getMandante().getImagem()).circleCrop().into(holder.binding.IMVMadante); //set imagem para mandante
        Glide.with(context).load(match.getVisitante().getImagem()).circleCrop().into(holder.binding.IMVVisitante); // set imagem para visiante

        //circleCrop -> deixa as imagens redondas

        holder.binding.TXVMandante.setText(match.getMandante().getNome()); //set nome do mandante
        holder.binding.TXVVisitante.setText(match.getVisitante().getNome());  //set nome do visitante

        if (match.getMandante().getPlacar() != null){
            holder.binding.TXVPlacarMadante.setText(String.valueOf(match.getMandante().getPlacar()));
        }
        if (match.getVisitante().getPlacar() != null){
            holder.binding.TXVPlacarVisitante.setText(String.valueOf(match.getVisitante().getPlacar()));
        }

        holder.itemView.setOnClickListener(view ->{
            Intent intent = new Intent(context, DetalhesJogo.class); // instancia o contexto da classe dos detalhes dos jogos
            intent.putExtra(DetalhesJogo.Extras.MATCH, match); // passa os dados de parametro para a constante na classe de detalhes do jogo
            context.startActivity(intent);
        });

    }

    @Override
    public int getItemCount() {
        return Jogo.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private final JogoItemBinding binding;

        public ViewHolder(JogoItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
