package com.example.colorhue

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class ranking : AppCompatActivity() {

    lateinit var btnVoltar : Button
    lateinit var databaseReference: DatabaseReference
    // Declaração da RecyclerView e do Array que será usado para compor a RV
    lateinit var rankingRecyclerView : RecyclerView
    lateinit var listaJogadores : ArrayList<UserData>

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_ranking)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        btnVoltar = findViewById(R.id.btnVoltarR)
        rankingRecyclerView = findViewById(R.id.rvRanking)

        // Define o layout do RecyclerView, aqui no caso, o padrão vertical com tamanho definido
        rankingRecyclerView.layoutManager = LinearLayoutManager(this)
        rankingRecyclerView.setHasFixedSize(true)

        listaJogadores = arrayListOf<UserData>()
        buscarJogadores()

        // Botão para voltar para o início
        btnVoltar.setOnClickListener {
            val intent = Intent(applicationContext, MainActivity::class.java)
            startActivity(intent)
        }
    }

    // Função que busca o ranking no BD
    private fun buscarJogadores() {

        // Acessa o BD e pega como referência o nó "Ranking"
        databaseReference = FirebaseDatabase.getInstance().getReference("Ranking")

        // Resposta do BD
        databaseReference.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {

                // Verifica se a chamada retornou algo
                if(snapshot.exists()){

                    // Coloca no Array todos os dados coletados
                    for(jogadorSnapshot in snapshot.children){

                        // Cria um objeto com as informações do jogador
                        val jogador = jogadorSnapshot.getValue(UserData::class.java)
                        // Adiciona o jogador não nulo ao Array
                        listaJogadores.add(jogador!!)
                    }

                    // Ordena o Array pela pontuação, da maior para a menor
                    listaJogadores.sortByDescending {
                        it.pont
                    }

                    // Converte o dado do jogador, através da associação do adapter com o RecyclerView, e mostra no ranking
                    rankingRecyclerView.adapter = Adaptador(listaJogadores)
                }
            }

            override fun onCancelled(error: DatabaseError) {}
        })
    }
}