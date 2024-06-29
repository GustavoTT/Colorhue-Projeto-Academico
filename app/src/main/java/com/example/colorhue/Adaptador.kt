package com.example.colorhue

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

// Criação do Adapter personalizado, ele pega os dados e converte para uma maneira que o android entenda
class Adaptador(private val ListaPlayers : ArrayList<UserData>) : RecyclerView.Adapter<Adaptador.referenciaJogador>() {

    // Responsável por criar cada linha do RecyclerView
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): referenciaJogador {
        val refrenciaLayout = LayoutInflater.from(parent.context).inflate(R.layout.player_item,parent,false)
        return referenciaJogador(refrenciaLayout)
    }

    // Responsável por definir quantas linhas serão criadas pelo onCreateViewHolder
    override fun getItemCount(): Int {
        return ListaPlayers.size
    }

    // Responsável por exibir as informações, passando os dados obtidos no BD
    override fun onBindViewHolder(referenciaElemento: referenciaJogador, posicaoArray: Int) {
        val jogadorAtual = ListaPlayers[posicaoArray]
        referenciaElemento.Jogador.text = jogadorAtual.nome
        referenciaElemento.Dificuldade.text = jogadorAtual.difi
        referenciaElemento.Pontuacao.text = jogadorAtual.pont
    }

    // Componentes que estão no layout (player_item.xml)
    class  referenciaJogador( itemView : View) : RecyclerView.ViewHolder(itemView){
        val Jogador : TextView = itemView.findViewById(R.id.tvJogador)
        val Dificuldade : TextView = itemView.findViewById(R.id.tvDificuldade)
        val Pontuacao : TextView = itemView.findViewById(R.id.tvPontuacao)
    }
}