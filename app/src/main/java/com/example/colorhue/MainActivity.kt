package com.example.colorhue

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    // TELA INICIAL

    // Declaração das variáveis para botões de dificuldade do jogo
    lateinit var  btnFacil : Button
    lateinit var  btnMedio : Button
    lateinit var  btnDificil : Button
    lateinit var  btnRegra : Button
    lateinit var  btnRanking : Button

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Link das variáveis com botões
        btnFacil = findViewById(R.id.btnFacil)
        btnMedio = findViewById(R.id.btnMedio)
        btnDificil = findViewById(R.id.btnDificil)
        btnRegra = findViewById(R.id.btnRegra)
        btnRanking = findViewById(R.id.btnRanking)

        // Colocamos de dificuldade para iniciar o jogo, onde o jogador é direcionado para a tela da respectiva dificuldade escolhida.
        // OU caso deseje, o jogador é direcionado para a tela de regras do jogo.
        btnFacil.setOnClickListener {
            val intent = Intent(applicationContext, jogoFacil::class.java)
            startActivity(intent)

        }

        btnMedio.setOnClickListener {
            val intent = Intent(applicationContext, jogoMedio::class.java)
            startActivity(intent)

        }

        btnDificil.setOnClickListener {
            val intent = Intent(applicationContext, jogoDificil::class.java)
            startActivity(intent)

        }

        btnRegra.setOnClickListener {
            val intent = Intent(applicationContext, regras::class.java)
            startActivity(intent)

        }

        btnRanking.setOnClickListener {
            val intent = Intent(applicationContext, ranking::class.java)
            startActivity(intent)

        }

    }
}
