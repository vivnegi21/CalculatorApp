package negi.vivek.calculatorapp

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    private var tvInput:TextView? = null
    var lastNum:Boolean = false
    var lastDot:Boolean = false
    private var operatorInUse:String? = null
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var btnEnter :Button = findViewById(R.id.btnEqual)
        tvInput = findViewById(R.id.tvInput)

        var btnExit :Button=findViewById(R.id.btnExit)
        var btnPercentage:Button = findViewById(R.id.btnPercentage)
        btnExit.setOnClickListener {
            finish()
        }

        btnPercentage.setOnClickListener {
            tvInput?.text?.let {
                if(lastNum && !isOperatorAdded(it.toString())){
                    var value = it.toString()
                    var prefix = "1"
                    if(it.startsWith("-")){
                        value=value.substring(1)
                        prefix="-1"
                    }
                    tvInput?.text = ((prefix.toDouble()) * (value.toDouble())*0.01).toString()
                }else if(lastNum){
                    var tvVal = tvInput?.text.toString()
                    var prefix = "1"
                    if(tvVal.startsWith("-")){
                        prefix = "-1"
                        tvVal = tvVal.substring(1)
                    }

                    operatorInUse?.let {

                        val splitVal = tvVal.split(operatorInUse!!)
                        var one = splitVal[0]
                        var two = splitVal[1]
                        two  = (two.toDouble()*one.toDouble()*0.01).toString()

                        tvInput?.text = "$one"+"$operatorInUse"+"$two"

                    }
                }
            }
        }
        btnEnter.setOnClickListener {
            if(lastNum){
                var tvVal = tvInput?.text.toString()
                var prefix = "1"
                if(tvVal.startsWith("-")){
                    prefix = "-1"
                    tvVal = tvVal.substring(1)
                }
                try{
                    if(tvVal.contains("*")){
                        val splitVal = tvVal.split("*")
                        var one = splitVal[0]
                        var two = splitVal[1]

                        tvInput?.text = (((prefix.toDouble()) * (one.toDouble())) * two.toDouble()).toString()
                    }else if(tvVal.contains("/")){
                        val splitVal = tvVal.split("/")
                        var one = splitVal[0]
                        var two = splitVal[1]

                        tvInput?.text = (((prefix.toDouble()) * (one.toDouble())) / two.toDouble()).toString()
                    }else if(tvVal.contains("+")){
                        val splitVal = tvVal.split("-")
                        var one = splitVal[0]
                        var two = splitVal[1]

                        tvInput?.text = (((prefix.toDouble()) * (one.toDouble())) + two.toDouble()).toString()
                    }else if(tvVal.contains("-")){
                        val splitVal = tvVal.split("-")
                        var one = splitVal[0]
                        var two = splitVal[1]

                        tvInput?.text = (((prefix.toDouble()) * (one.toDouble())) - two.toDouble()).toString()
                    }
                    operatorInUse = null

                }catch (e:ArithmeticException){
                    e.printStackTrace()
                }
            }
        }


    }
    fun onOperator(view: View){
        tvInput?.text?.let {
            if (lastNum && !isOperatorAdded((it.toString()))) {
                tvInput?.append((view as Button).text)
                operatorInUse = (view as Button).text as String
                lastNum = false
                lastDot = false
            }
        }
    }

    private fun isOperatorAdded(value:String): Boolean {

        return if(value.startsWith("-")){
            false
        } else{
            value.contains("/") ||value.contains("*") ||value.contains("-") ||value.contains("+")
        }

    }
    fun inDigit(view: View) {
        tvInput?.append((view as Button).text)
        lastNum = true
        lastDot = false
    }
    fun clr(view: View){
        tvInput?.text = tvInput?.text?.dropLast(1)
    }
    fun allclr(view: View){
        tvInput?.text=""
    }
    fun onDecimalPoint(view: View){
        if(lastNum && !lastDot){
            tvInput?.append(".")
            lastNum = false
            lastDot = true
        }
    }

}