package com.example.teste

import android.graphics.Color
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Switch
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val labelInformacoes = findViewById<TextView>(R.id.labelInformacoes)
        val labelIMC = findViewById<TextView>(R.id.labelIMC)
        val labelIMCNumero = findViewById<TextView>(R.id.labelIMCNumero)
        val labelIMCDiferenca = findViewById<TextView>(R.id.labelIMCDiferenca)
        val editTextPeso = findViewById<EditText>(R.id.editTextPeso)
        val editTextAltura = findViewById<EditText>(R.id.editTextAltura)
        val editTextNome = findViewById<EditText>(R.id.editTextNome)
        val buttonCalcularIMC = findViewById<Button>(R.id.buttonCalcularIMC)
        val buttonLimpar = findViewById<Button>(R.id.buttonLimpar)
        val swtGenero = findViewById<Switch>(R.id.swtGenero)

        buttonCalcularIMC.setOnClickListener {
            val nome = editTextNome.text.toString()
            val peso = editTextPeso.text.toString().toDoubleOrNull()
            val altura = editTextAltura.text.toString().toDoubleOrNull()
            if(peso != null && altura != null && altura > 0){
                val IMC:Double? = peso / (altura*altura)
                val DiferencaPesoIdealObeso:Double? = peso - ((altura*altura) * 24.9)
                val DiferencaPesoIdealMagro:Double? = peso - ((altura*altura) * 18.5)
                if(IMC != null && DiferencaPesoIdealObeso != null && DiferencaPesoIdealMagro != null) {
                    if (IMC >= 40) {
                        labelIMC.text = "Obesidade Grau III"
                        labelIMCDiferenca.text = "+%.2f do peso ideal".format(DiferencaPesoIdealObeso)
                        labelIMCDiferenca.setTextColor(Color.RED)
                    } else if (IMC >= 35) {
                        labelIMC.text = "Obesidade Grau II"
                        labelIMCDiferenca.text = "+%.2f do peso ideal".format(DiferencaPesoIdealObeso)
                        labelIMCDiferenca.setTextColor(Color.RED)
                    } else if (IMC >= 30) {
                        labelIMC.text = "Obesidade Grau I"
                        labelIMCDiferenca.text = "+%.2f do peso ideal".format(DiferencaPesoIdealObeso)
                        labelIMCDiferenca.setTextColor(Color.RED)
                    } else if (IMC >= 25) {
                        labelIMC.text = "Sobrepeso"
                        labelIMCDiferenca.text = "+%.2f do peso ideal".format(DiferencaPesoIdealObeso)
                        labelIMCDiferenca.setTextColor(Color.RED)
                    } else if (IMC >= 18.5) {
                        labelIMC.text = "Saudável"
                        labelIMCDiferenca.text = "Ótimo!"
                        labelIMCDiferenca.setTextColor(Color.GREEN)
                    } else {
                        labelIMC.text = "Magreza"
                        labelIMCDiferenca.text = "%.2f do peso ideal".format(DiferencaPesoIdealMagro)
                        labelIMCDiferenca.setTextColor(Color.BLUE)
                    }
                    if(swtGenero.isChecked){
                        labelInformacoes.text = "Dados da senhora " + nome
                    }else{
                        labelInformacoes.text = "Dados do senhor " + nome
                    }
                    labelIMCNumero.text = "Valor do IMC: %.2f".format(IMC)
                }
            }else{
                val text = "Insira todos os valores antes de apertar o botão"
                val duration = Toast.LENGTH_SHORT

                val toast = Toast.makeText(this, text, duration) // in Activity
                toast.show()
            }
        }
        buttonLimpar.setOnClickListener {
            editTextNome.setText("")
            editTextPeso.setText("")
            editTextAltura.setText("")
        }
    }
}