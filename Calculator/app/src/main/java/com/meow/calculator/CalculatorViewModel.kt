package com.meow.calculator

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import net.objecthunter.exp4j.ExpressionBuilder

class CalculatorViewModel: ViewModel() {
    private val _displayData=MutableLiveData<String>("")
    val displayData:LiveData<String> =_displayData
    private var currentExpression=""

    fun onBackClick(){
        if (currentExpression!=""){
            currentExpression=currentExpression.substring(0,currentExpression.length-1)
            _displayData.value=currentExpression
        }
    }
    fun onNumberClicked(num:String){
        currentExpression+=num
        _displayData.value=currentExpression
    }
    fun onOperatorClicked(opr:String){
        currentExpression+="$opr"
        _displayData.value=currentExpression
    }
    fun onClearClicked(){
        currentExpression=""
        _displayData.value=currentExpression
    }
    fun onEqalstoClicked(){
        try {
            val result=evaluateResult(currentExpression)
            currentExpression=result.toString()
            _displayData.value=currentExpression
        }catch (e:Exception){
            currentExpression="Error"
            _displayData.value=currentExpression
        }
    }
    private fun evaluateResult(expression:String):Double{
        val expr=ExpressionBuilder(expression).build()
        return expr.evaluate()
    }

}