package com.example.colorhue

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.Window
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class resultado : AppCompatActivity() {

    // TELA DE RESULTADOS

    lateinit var tvDificuldade : TextView
    lateinit var tvAcertos : TextView
    lateinit var tvErros : TextView
    lateinit var tvPontuacao : TextView

    var difi = 0
    var acertos = 0
    var dificuldade = ""
    var pontuacao = ""

    lateinit var btnSalvar : Button
    lateinit var databaseReference: DatabaseReference

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_resultado)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Aqui foram criadas variáveis para receber o valor enviado pela intent do último jogo a respeito da pontuação e dificuldade.
        val dados: Bundle? = intent.extras
        val resultadoJogo = dados?.getInt("pontuacao")
        val dificuldadeJogo = dados?.getInt("dificuldade")

        // Pegando os dados da jogada para serem gravados no BD posteriormente
        if (dificuldadeJogo != null && resultadoJogo != null) {
            difi = dificuldadeJogo
            acertos = resultadoJogo
            pontuacao = (resultadoJogo*100).toString()
        }

        tvDificuldade = findViewById(R.id.tvDificuldade)
        tvAcertos = findViewById(R.id.tvAcertos)
        tvErros = findViewById(R.id.tvErros)
        tvPontuacao = findViewById(R.id.tvPontuacao)
        btnSalvar = findViewById(R.id.btnSalvar)

        // Além de mostrar a pontuação obtida, foi criado também mecanismo para exibir a quantidade de acertos e de erros em cada partida, para cada nível de dificuldade.
        if (resultadoJogo != null) {
            tvPontuacao.setText("Pontuação: " + pontuacao)
            tvAcertos.setText("Acertos: " + resultadoJogo.toString())
        }

        if(dificuldadeJogo == 1){
            tvDificuldade.setText("Dificuldade Jogada: Fácil")
            if (resultadoJogo != null) {
                tvErros.setText("Erros: " + (3-resultadoJogo).toString())
            }
            dificuldade = "Fácil"
        }else if(dificuldadeJogo == 2){
            tvDificuldade.setText("Dificuldade Jogada: Médio")
            if (resultadoJogo != null) {
                tvErros.setText("Erros: " + (6-resultadoJogo).toString())
            }
            dificuldade = "Médio"
        }else if(dificuldadeJogo == 3){
            tvDificuldade.setText("Dificuldade Jogada: Difícil")
            if (resultadoJogo != null) {
                tvErros.setText("Erros: " + (12-resultadoJogo).toString())
            }
            dificuldade = "Difícil"
        }else{
            tvDificuldade.setText("Erro")
            tvPontuacao.setText("Erro")
            tvAcertos.setText("Erro")
            tvErros.setText("Erro")
        }

        // Botão para voltar para tela inicial
        btnSalvar.setOnClickListener {
            abrirPopUp()
        }
    }

    // Função que abre a PopUp e permite a gravação do resultado do jogo no ranking
    private fun abrirPopUp() {
        // Definição e configuração do Dialog
        val dialog = Dialog(this)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(false)
        dialog.setContentView(R.layout.layout_popup_salvarresult)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        val btnSim : Button = dialog.findViewById(R.id.btnSim)
        val btnNao : Button = dialog.findViewById(R.id.btnNao)

        // Caso seja clicado o botão para salvar o resultado
        btnSim.setOnClickListener {

            // Pega o nome que o usuário quer gravar no BD
            val etNome : EditText = dialog.findViewById(R.id.etNomeJogada)
            val nome = etNome.text.toString()

            // Verifica se o campo foi preenchido
            if (nome.isBlank()) {
                Toast.makeText(this, "Digite um nome para salvar!", Toast.LENGTH_SHORT).show()

            } else {

                // Faz uma chamada no BD para verificar se o nome digitado já foi cadastrado
                databaseReference = FirebaseDatabase.getInstance().getReference("Ranking")
                databaseReference.child(nome).get().addOnSuccessListener {

                    // Se já estiver cadastrado, retorna erro
                    if (it.exists()){
                        Toast.makeText(this, "Nome já usado!", Toast.LENGTH_SHORT).show()
                    }else{
                        // Define os valores
                        val jogada = UserData(nome, dificuldade, pontuacao)

                        // Grava os valores no banco
                        databaseReference.child(nome).setValue(jogada).addOnSuccessListener {
                            Toast.makeText(this, "Salvo com Sucesso!", Toast.LENGTH_SHORT).show()

                            // Direciona para a tela de ranking
                            val intent = Intent(this, ranking::class.java)
                            startActivity(intent)
                            finish()

                        }.addOnFailureListener() {
                            Toast.makeText(this, "Erro ao Salvar!", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            }
        }

        // Caso seja clicado o botão para não salvar o resultado
        btnNao.setOnClickListener {
            val intent = Intent(applicationContext, MainActivity::class.java)
            startActivity(intent)
        }

        dialog.show()
    }
}