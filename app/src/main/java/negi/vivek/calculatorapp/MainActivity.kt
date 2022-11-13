package negi.vivek.calculatorapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    private var tvInput:TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tvInput = findViewById(R.id.tvInput)

    }

    fun InDigit(view: View) {
        tvInput?.append((view as Button).text)
    }
    fun clr(view: View){
        tvInput?.text = tvInput?.text?.dropLast(1)
    }
    fun allclr(view: View){
        tvInput?.text=""
    }
}