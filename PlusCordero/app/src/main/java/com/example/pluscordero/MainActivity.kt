package com.example.pluscordero

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity(), View.OnClickListener {
    override fun onClick(v: View?) {
        btnSelected(v as Button)
    }

    private var cells= mutableMapOf<Int,String>()
    private var isX=true
    private var winner:String=""
    private val totalCell=36
    private lateinit var txtResult:TextView
    private val x="X"
    private val o="0"
    private var btns= arrayOfNulls<Button>(totalCell)
    private val combinations:Array<IntArray> = arrayOf(
        intArrayOf(0,1,2,3),
        intArrayOf(7,8,9,10),
        intArrayOf(12,13,14,15),
        intArrayOf(18,19,20,21),
        intArrayOf(24,25,26,27),
        intArrayOf(30,31,32,33),
        intArrayOf(2,3,4,5),
        intArrayOf(8,9,10,11),
        intArrayOf(20,21,22,23),
        intArrayOf(26,27,28,29),
        intArrayOf(32,33,34,35),
        intArrayOf(0,6,12,18),
        intArrayOf(1,7,13,19),
        intArrayOf(2,8,14,20),
        intArrayOf(3,9,15,21),
        intArrayOf(4,10,16,22),
        intArrayOf(5,11,17,23),
        intArrayOf(12,18,24,30),
        intArrayOf(13,19,25,31),
        intArrayOf(14,20,26,32),
        intArrayOf(15,21,27,33),
        intArrayOf(16,22,28,34),
        intArrayOf(17,23,29,35),
        intArrayOf(2,9,11,23),
        intArrayOf(1,8,15,22),
        intArrayOf(0,7,14,21),
        intArrayOf(6,13,20,27),
        intArrayOf(12,19,26,33),
        intArrayOf(3,8,13,18),
        intArrayOf(4,9,14,19),
        intArrayOf(5,10,15,20),
        intArrayOf(11,16,21,26),
        intArrayOf(17,22,27,32),
        intArrayOf(9,14,19,24),
        intArrayOf(15,20,25,30),
        intArrayOf(16,21,26,31),
        intArrayOf(8,15,22,29),
        intArrayOf(14,21,28,35),
        intArrayOf(13,20,27,34),
        intArrayOf(6,12,18,24),
        intArrayOf(7,13,19,25),
        intArrayOf(8,14,20,26),
        intArrayOf(9,15,21,27),
        intArrayOf(10,16,22,28),
        intArrayOf(11,17,23,29),
        intArrayOf(1,2,3,4),
        intArrayOf(7,8,9,10),
        intArrayOf(13,14,15,16),
        intArrayOf(19,20,21,22),
        intArrayOf(25,26,27,28),
        intArrayOf(31,32,33,34),
        intArrayOf(7,14,21,28),
        intArrayOf(10,15,20,25),

        )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        txtResult=findViewById(R.id.txtResult)

        for(i in 1..totalCell){
            var button=findViewById<Button>(resources.getIdentifier("btn$i", "id", packageName ))
            button.setOnClickListener(this)
            btns[i-1]=button
        }
    }
    private fun btnSelected(button: Button){
        var index=0
        when(button.id){
            R.id.btn1->index=0
            R.id.btn2->index=1
            R.id.btn3->index=2
            R.id.btn4->index=3
            R.id.btn5->index=4
            R.id.btn6->index=5
            R.id.btn7->index=6
            R.id.btn8->index=7
            R.id.btn9->index=8
            R.id.btn10->index=9
            R.id.btn11->index=10
            R.id.btn12->index=11
            R.id.btn13->index=12
            R.id.btn14->index=13
            R.id.btn15->index=14
            R.id.btn16->index=15
            R.id.btn17->index=16
            R.id.btn18->index=17
            R.id.btn19->index=18
            R.id.btn20->index=19
            R.id.btn21->index=20
            R.id.btn22->index=21
            R.id.btn23->index=22
            R.id.btn24->index=23
            R.id.btn25->index=24
            R.id.btn26->index=25
            R.id.btn27->index=26
            R.id.btn28->index=27
            R.id.btn29->index=28
            R.id.btn30->index=29
            R.id.btn31->index=30
            R.id.btn32->index=31
            R.id.btn33->index=32
            R.id.btn34->index=33
            R.id.btn35->index=34
            R.id.btn36->index=35
        }
        playGame(index,button)
        chechkWinner()
        update()
    }
    private fun chechkWinner(){
        if(cells.isNotEmpty()) {
            for (combination in combinations) {
                var (a, b, c, d) = combination

                if (cells[a] != null && cells[a] == cells[b] && cells[a] == cells[c] && cells[a] == cells[d]) {
                    this.winner = cells[a].toString()
                }
            }
        }
    }
    private fun update(){
        when{
            winner.isNotEmpty()->{
                txtResult.text=resources.getString(R.string.winner,winner)
                txtResult.setTextColor(Color.GREEN)
            }
            cells.size==totalCell->{
                txtResult.text="Empate"
                txtResult.setTextColor(Color.GREEN)
            }
            else->{
                txtResult.text=resources.getString(R.string.next_player,if(isX)x else o)
            }
        }
    }


    private fun playGame(index:Int,button: Button){
        if(!winner.isNullOrEmpty()){
            Toast.makeText(this,"Juego finalizado", Toast.LENGTH_LONG).show()
            return
        }
        when{
            isX->cells[index]=x
            else->cells[index]=o
        }
        button.text=cells[index]
        button.isEnabled=false
        isX=!isX
    }
    fun resetButton(){
        for(i in 1..totalCell){
            var button=btns[i-1]
            button?.text=""
            button?.isEnabled=true
        }
    }
    fun newGame(){
        cells= mutableMapOf()
        isX=true
        winner=""
        txtResult.text=resources.getString(R.string.next_player, x)
        txtResult.setTextColor(Color.BLACK)
        resetButton()
    }
    fun reset(View: View) {
        newGame()
    }
    
}