package negi.vivek.calculatorapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import java.sql.BatchUpdateException

class MainActivity : AppCompatActivity() {

    private var tvInput:TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tvInput = findViewById(R.id.tvInput)

        var btnExit :Button=findViewById(R.id.btnExit)

        btnExit.setOnClickListener {
            finish()
        }

    }

    fun inDigit(view: View) {
        tvInput?.append((view as Button).text)
    }
    fun clr(view: View){
        tvInput?.text = tvInput?.text?.dropLast(1)
    }
    fun allclr(view: View){
        tvInput?.text=""
    }
}