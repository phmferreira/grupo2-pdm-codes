package br.edu.ifpb.calculadora

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    private lateinit var resultado : EditText
    private lateinit var novoNumero : EditText
    private lateinit var displayOperacao : TextView

    private var operando1 : Double? = null
    private var operando2 : Double = 0.0
    private var operacaoPendente : String = "="
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        resultado = findViewById(R.id.etnResultado)
        novoNumero = findViewById(R.id.etnNovoNumero)
        displayOperacao = findViewById(R.id.txtOperacao)

        // botoes usados para digitar o número
        val bt0 : Button = findViewById(R.id.bt0)
        val bt1 : Button = findViewById(R.id.bt1)
        val bt2 : Button = findViewById(R.id.bt2)
        val bt3 : Button = findViewById(R.id.bt3)
        val bt4 : Button = findViewById(R.id.bt4)
        val bt5 : Button = findViewById(R.id.bt5)
        val bt6 : Button = findViewById(R.id.bt6)
        val bt7 : Button = findViewById(R.id.bt7)
        val bt8 : Button = findViewById(R.id.bt8)
        val bt9 : Button = findViewById(R.id.bt9)
        val btPonto : Button = findViewById(R.id.btPonto)

        // ação generica de escrever
        val apendarTexto = View.OnClickListener {
            botao ->
            // casting
            val botao = (botao as Button)
            novoNumero.append(botao.text)
        }
        bt0.setOnClickListener(apendarTexto)
        bt1.setOnClickListener(apendarTexto)
        bt2.setOnClickListener(apendarTexto)
        bt3.setOnClickListener(apendarTexto)
        bt4.setOnClickListener(apendarTexto)
        bt5.setOnClickListener(apendarTexto)
        bt6.setOnClickListener(apendarTexto)
        bt7.setOnClickListener(apendarTexto)
        bt8.setOnClickListener(apendarTexto)
        bt9.setOnClickListener(apendarTexto)
        btPonto.setOnClickListener(apendarTexto)

        // botoes de operacao
        val btSoma : Button = findViewById(R.id.btSoma)
        val btSubtracao : Button = findViewById(R.id.btSubtracao)
        val btMultiplicao : Button = findViewById(R.id.btMultiplicao)
        val btDivisao : Button = findViewById(R.id.btDivisao)
        val btresultado : Button = findViewById(R.id.btResultado)

        // ação de realizar operação
        val operacaoListener = View.OnClickListener {
            botao ->
            val operador = (botao as Button).text.toString()
            val valorDigitado = novoNumero.text.toString()
            if (valorDigitado.isNotEmpty()){
                realizarCalculo(valorDigitado)
            }
            operacaoPendente = operador
            displayOperacao.text = operacaoPendente
        }
        btSoma.setOnClickListener(operacaoListener)
        btSubtracao.setOnClickListener(operacaoListener)
        btMultiplicao.setOnClickListener(operacaoListener)
        btDivisao.setOnClickListener(operacaoListener)
        btresultado.setOnClickListener(operacaoListener)
    }

    private fun realizarCalculo(valor : String){
        if(operando1 == null){
            operando1 = valor.toDouble()
        } else {
            operando2 = valor.toDouble()
            // realiza a operacao pendente
            when(operacaoPendente){
                "=" -> operando1 = operando2
                "+" -> operando1 = operando1!! + operando2
                "-" -> operando1 = operando1!! - operando2
                "x" -> operando1 = operando1!! * operando2
                "/" -> if(operando2 == 0.0){
                    operando1 = Double.NaN
                } else {
                    operando1 = operando1!! / operando2
                }
            }
        }
        resultado.setText(operando1.toString())
        novoNumero.setText("")
    }
}







