package com.example.colorhue

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import kotlin.random.Random

class jogoDificil : AppCompatActivity() {

    lateinit var ivSorteio : ImageView
    lateinit var btnAmarelo : ImageButton
    lateinit var btnVermelho : ImageButton
    lateinit var btnLaranja : ImageButton
    lateinit var btnVerde : ImageButton
    lateinit var btnAzul : ImageButton
    lateinit var btnRoxo : ImageButton
    lateinit var btnMarrom : ImageButton
    lateinit var btnPreto : ImageButton
    lateinit var btnRosa : ImageButton
    lateinit var btnStart : Button

    lateinit var tvTexto : TextView

    val dificuldade = 3
    var pontuacao = 0
    var corAtual = 0
    var corCerta = 0

    var respostasArray = listOf(
        Sequencia("", listOf("amarelo", "azul", "vermelho", "roxo", "laranja", "verde", "marrom", "preto", "rosa")),
        Sequencia("", listOf("amarelo", "azul", "vermelho", "roxo", "laranja", "verde", "marrom", "preto", "rosa")),
        Sequencia("", listOf("amarelo", "azul", "vermelho", "roxo", "laranja", "verde", "marrom", "preto", "rosa")),
        Sequencia("", listOf("amarelo", "azul", "vermelho", "roxo", "laranja", "verde", "marrom", "preto", "rosa")),
        Sequencia("", listOf("amarelo", "azul", "vermelho", "roxo", "laranja", "verde", "marrom", "preto", "rosa")),
        Sequencia("", listOf("amarelo", "azul", "vermelho", "roxo", "laranja", "verde", "marrom", "preto", "rosa")),
        Sequencia("", listOf("amarelo", "azul", "vermelho", "roxo", "laranja", "verde", "marrom", "preto", "rosa")),
        Sequencia("", listOf("amarelo", "azul", "vermelho", "roxo", "laranja", "verde", "marrom", "preto", "rosa")),
        Sequencia("", listOf("amarelo", "azul", "vermelho", "roxo", "laranja", "verde", "marrom", "preto", "rosa")),
        Sequencia("", listOf("amarelo", "azul", "vermelho", "roxo", "laranja", "verde", "marrom", "preto", "rosa")),
        Sequencia("", listOf("amarelo", "azul", "vermelho", "roxo", "laranja", "verde", "marrom", "preto", "rosa")),
        Sequencia("", listOf("amarelo", "azul", "vermelho", "roxo", "laranja", "verde", "marrom", "preto", "rosa"))
    )

    var timer: CountDownTimer? = null
    var pauseTimer: CountDownTimer? = null

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_jogo_dificil)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        ivSorteio = findViewById(R.id.ivSorteioD)
        btnAmarelo = findViewById(R.id.btnAmareloD)
        btnVermelho = findViewById(R.id.btnVermelhoD)
        btnAzul = findViewById(R.id.btnAzulD)
        btnVerde = findViewById(R.id.btnVerdeD)
        btnLaranja = findViewById(R.id.btnLaranjaD)
        btnRoxo = findViewById(R.id.btnRoxoD)
        btnMarrom = findViewById(R.id.btnMarromD)
        btnPreto = findViewById(R.id.btnPretoD)
        btnRosa = findViewById(R.id.btnRosaD)
        btnStart = findViewById(R.id.btnComecarD)

        tvTexto = findViewById(R.id.tvTextoD)

        btnAmarelo.isEnabled = false
        btnAzul.isEnabled = false
        btnRoxo.isEnabled = false
        btnVermelho.isEnabled = false
        btnLaranja.isEnabled = false
        btnVerde.isEnabled = false
        btnMarrom.isEnabled = false
        btnPreto.isEnabled = false
        btnRosa.isEnabled = false

        ivSorteio.setImageResource(R.drawable.cinza)

        btnRoxo.setImageResource(R.drawable.cinza)
        btnAmarelo.setImageResource(R.drawable.cinza)
        btnAzul.setImageResource(R.drawable.cinza)
        btnVermelho.setImageResource(R.drawable.cinza)
        btnLaranja.setImageResource(R.drawable.cinza)
        btnVerde.setImageResource(R.drawable.cinza)
        btnMarrom.setImageResource(R.drawable.cinza)
        btnPreto.setImageResource(R.drawable.cinza)
        btnRosa.setImageResource(R.drawable.cinza)

        btnAmarelo.setOnClickListener {
            verificarResposta(respostasArray[corAtual].coresPossiveis[0])
        }

        btnAzul.setOnClickListener {
            verificarResposta(respostasArray[corAtual].coresPossiveis[1])
        }

        btnVermelho.setOnClickListener {
            verificarResposta(respostasArray[corAtual].coresPossiveis[2])
        }

        btnRoxo.setOnClickListener {
            verificarResposta(respostasArray[corAtual].coresPossiveis[3])
        }

        btnLaranja.setOnClickListener {
            verificarResposta(respostasArray[corAtual].coresPossiveis[4])
        }

        btnVerde.setOnClickListener {
            verificarResposta(respostasArray[corAtual].coresPossiveis[5])
        }

        btnMarrom.setOnClickListener {
            verificarResposta(respostasArray[corAtual].coresPossiveis[6])
        }

        btnPreto.setOnClickListener {
            verificarResposta(respostasArray[corAtual].coresPossiveis[7])
        }

        btnRosa.setOnClickListener {
            verificarResposta(respostasArray[corAtual].coresPossiveis[8])
        }
    }

    fun startGame(view: View){
        if(corAtual < 12){
            startTimer()
            btnStart.isEnabled = false
            btnStart.setBackgroundColor(getColor(R.color.cinza))
            tvTexto.setText("")
        }else{
            val intent = Intent(applicationContext, resultado::class.java)
            intent.putExtra("pontuacao", pontuacao)
            intent.putExtra("dificuldade", dificuldade)
            startActivity(intent)
        }
    }

    fun gerarCor(cor : Int){
        when (cor) {
            0 -> {
                respostasArray[corCerta].corCorreta = "amarelo"
                ivSorteio.setImageResource(R.drawable.amarelo)
            }
            1 -> {
                respostasArray[corCerta].corCorreta = "azul"
                ivSorteio.setImageResource(R.drawable.azul)
            }
            2 -> {
                respostasArray[corCerta].corCorreta = "vermelho"
                ivSorteio.setImageResource(R.drawable.vermelho)
            }
            3 -> {
                respostasArray[corCerta].corCorreta = "roxo"
                ivSorteio.setImageResource(R.drawable.roxo)
            }
            4 -> {
                respostasArray[corCerta].corCorreta = "laranja"
                ivSorteio.setImageResource(R.drawable.laranja)
            }
            5 -> {
                respostasArray[corCerta].corCorreta = "verde"
                ivSorteio.setImageResource(R.drawable.verde)
            }
            6 -> {
                respostasArray[corCerta].corCorreta = "marrom"
                ivSorteio.setImageResource(R.drawable.marrom)
            }
            7 -> {
                respostasArray[corCerta].corCorreta = "preto"
                ivSorteio.setImageResource(R.drawable.preto)
            }
            8 -> {
                respostasArray[corCerta].corCorreta = "rosa"
                ivSorteio.setImageResource(R.drawable.rosa)
            }
        }
    }

    fun verificarResposta(resposta:String){
        if (resposta == respostasArray[corAtual].corCorreta) {
            pontuacao++

        }
        corAtual++

        if(corAtual == 12){
            btnStart.isEnabled = true
            btnStart.setBackgroundColor(getColor(R.color.verde))
            btnStart.setText("VER PONTUAÇÃO")

            tvTexto.setText("Veja sua pontuação")

            btnRoxo.setImageResource(R.drawable.cinza)
            btnAmarelo.setImageResource(R.drawable.cinza)
            btnAzul.setImageResource(R.drawable.cinza)
            btnVermelho.setImageResource(R.drawable.cinza)
            btnLaranja.setImageResource(R.drawable.cinza)
            btnVerde.setImageResource(R.drawable.cinza)
            btnMarrom.setImageResource(R.drawable.cinza)
            btnPreto.setImageResource(R.drawable.cinza)
            btnRosa.setImageResource(R.drawable.cinza)
        }
    }

    fun startTimer(){
        var numCor = 0
        timer = object: CountDownTimer(24000, 2000) {
            override fun onTick(millisUntilFinished: Long) {
                numCor = Random.nextInt(9)
                gerarCor(numCor)
                corCerta++

                pauseTimer()
            }

            override fun onFinish() {
                ivSorteio.setImageResource(R.drawable.cinza)

                btnAmarelo.isEnabled = true
                btnAzul.isEnabled = true
                btnRoxo.isEnabled = true
                btnVermelho.isEnabled = true
                btnLaranja.isEnabled = true
                btnVerde.isEnabled = true
                btnMarrom.isEnabled = true
                btnPreto.isEnabled = true
                btnRosa.isEnabled = true

                tvTexto.setText("Agora reproduza a sequência de cores")

                btnRoxo.setImageResource(R.drawable.roxo)
                btnAzul.setImageResource(R.drawable.azul)
                btnAmarelo.setImageResource(R.drawable.amarelo)
                btnVermelho.setImageResource(R.drawable.vermelho)
                btnLaranja.setImageResource(R.drawable.laranja)
                btnVerde.setImageResource(R.drawable.verde)
                btnMarrom.setImageResource(R.drawable.marrom)
                btnPreto.setImageResource(R.drawable.preto)
                btnRosa.setImageResource(R.drawable.rosa)
            }
        }.start()
    }

    fun pauseTimer(){
        pauseTimer = object: CountDownTimer(1500, 1500) {
            override fun onTick(millisUntilFinished: Long) {
            }

            override fun onFinish() {
                ivSorteio.setImageResource(R.drawable.blank)
            }
        }.start()
    }
}