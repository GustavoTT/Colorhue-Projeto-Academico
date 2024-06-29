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

class jogoFacil : AppCompatActivity() {

    // DECLARAÇÃO DE VARIÁVEIS

    lateinit var ivSorteio : ImageView
    lateinit var btnAmarelo : ImageButton
    lateinit var btnVermelho : ImageButton
    lateinit var btnAzul : ImageButton
    lateinit var btnRoxo : ImageButton
    lateinit var btnStart : Button

    lateinit var tvTexto : TextView

    val dificuldade = 1
    var pontuacao = 0
    var corAtual = 0
    var corCerta = 0

    // Criação de um array através da classe Sequência, para cada cor da rodada
    var respostasArray = listOf(
        Sequencia("", listOf("amarelo", "azul", "vermelho", "roxo")),
        Sequencia("", listOf("amarelo", "azul", "vermelho", "roxo")),
        Sequencia("", listOf("amarelo", "azul", "vermelho", "roxo"))
    )

    // Declaração dos temporizadores
    var timer: CountDownTimer? = null
    var pauseTimer: CountDownTimer? = null

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_jogo_facil)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        ivSorteio = findViewById(R.id.ivSorteioF)
        btnAmarelo = findViewById(R.id.btnAmareloF)
        btnVermelho = findViewById(R.id.btnVermelhoF)
        btnAzul = findViewById(R.id.btnAzulF)
        btnRoxo = findViewById(R.id.btnRoxoF)
        btnStart = findViewById(R.id.btnComecarF)

        tvTexto = findViewById(R.id.tvTextoF)

        // Inicialmente desabilitamos botoes até que a sequência a ser replicada seja finalizada, só então os botões são ativados.
        btnAmarelo.isEnabled = false
        btnAzul.isEnabled = false
        btnRoxo.isEnabled = false
        btnVermelho.isEnabled = false

        // Remoção das imagens respectivas de cada botão até que a sequência a ser replicada seja finalizada.
        ivSorteio.setImageResource(R.drawable.cinza)

        btnRoxo.setImageResource(R.drawable.cinza)
        btnAmarelo.setImageResource(R.drawable.cinza)
        btnAzul.setImageResource(R.drawable.cinza)
        btnVermelho.setImageResource(R.drawable.cinza)

        // Cada botão colorido, ao ser clicado, chama a função verificarResposta.
        // Depois de acionado,o número referente a cada cor é comparado com o número previamente gravado da sequência correta.
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

    }

    // Função que define a finalidade do botão de começar. Caso o botão de iniciar o jogo não tenha sido ativado, todos os botões de cores ficam desativados,
    // caso contrário, o jogo é iniciado, assim como o mecanismo para a contagem da pontuação é calculada conforme cada cor é selecionada pelo jogador,
    // sendo possível então, somente no final do jogo, o redirecionamento para a tela de pontuação.
    fun startGame(view: View){
        if(corAtual < 3){
            startTimer()
            btnStart.isEnabled = false
            btnStart.setBackgroundColor(getColor(R.color.cinza))
            tvTexto.setText("")
        }else{
            // Passa as informações referente a pontuação e dificuldade do jogo para a tela de pontuação.
            val intent = Intent(applicationContext, resultado::class.java)
            intent.putExtra("pontuacao", pontuacao)
            intent.putExtra("dificuldade", dificuldade)
            startActivity(intent)
        }
    }

    // Função responsável por gerar as cores da sequencia a ser replicada pelo jogador
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
        }
    }

    // Esta função vai comparar o array gerado pelo jogador com o array gerado aleatóriamente pelo jogo, a fim de verificar se as respostas estão corretas.
    // Em caso afirmativo, é somado +1 para cada resposta correta.
    fun verificarResposta(resposta:String){
        if (resposta == respostasArray[corAtual].corCorreta) {
            pontuacao++

        }
        corAtual++
        // Após a seleção de todas as cores, o botão de começar é ativado novamente, entretanto, agora como um botão para visualização da pontuação.
        // Os botões das cores são desativados novamente até que uma nova partida seja iniciada.
        if(corAtual == 3){
            btnStart.isEnabled = true
            btnStart.setBackgroundColor(getColor(R.color.verde))
            btnStart.setText("VER PONTUAÇÃO")

            tvTexto.setText("Veja sua pontuação")

            btnRoxo.setImageResource(R.drawable.cinza)
            btnAmarelo.setImageResource(R.drawable.cinza)
            btnAzul.setImageResource(R.drawable.cinza)
            btnVermelho.setImageResource(R.drawable.cinza)
        }
    }

    // Esta é a função que é responsável pelo funcionamento do jogo de fato.
    // Aqui estabelecemos um timer para que enquanto o jogo gera a sequência aleatória, exista um intervalo ao mudar de uma cor para outra.
    fun startTimer(){
        var numCor = 0
        timer = object: CountDownTimer(10000, 3333) {
            override fun onTick(millisUntilFinished: Long) {
                numCor = Random.nextInt(4)
                gerarCor(numCor)
                corCerta++

                pauseTimer()
            }

            // Ao final da sequência aleatória, os botões são ativados e a vez é dada ao jogador.
            override fun onFinish() {
                ivSorteio.setImageResource(R.drawable.cinza)

                btnAmarelo.isEnabled = true
                btnAzul.isEnabled = true
                btnRoxo.isEnabled = true
                btnVermelho.isEnabled = true

                tvTexto.setText("Agora reproduza a sequência de cores")

                btnRoxo.setImageResource(R.drawable.roxo)
                btnAzul.setImageResource(R.drawable.azul)
                btnAmarelo.setImageResource(R.drawable.amarelo)
                btnVermelho.setImageResource(R.drawable.vermelho)
            }
        }.start()
    }

    // Função responsável por dar o efeito visual de "piscar" a cor na tela, para melhor entendimento da sequência.
    fun pauseTimer(){
        pauseTimer = object: CountDownTimer(3000, 3000) {
            override fun onTick(millisUntilFinished: Long) {
            }

            override fun onFinish() {
                ivSorteio.setImageResource(R.drawable.blank)
            }
        }.start()
    }
}