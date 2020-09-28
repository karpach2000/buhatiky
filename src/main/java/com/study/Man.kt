package com.study

import java.lang.Exception

data class Man(val name: String) {

    var tusingTimes = ArrayList<TusingTime>()
    var purchases = ArrayList<Purchase>()
    var balance = 0L
    var creditors = HashMap<String, Long>()

    override fun equals(other: Any?): Boolean {
        return try {
            (other as Man).name == name
        } catch (ex: Exception) {
            false
        }
    }

    override fun toString(): String {
        var ans =  "name: '$name', balance: $balance\n"
        creditors.forEach { ans = ans+ "    ${it.key} : ${it.value}\n" }
        return ans
    }
}

class Purchase(val name : String, val sum: Long)
{
    var tusingTimes = ArrayList<TusingTime>()
}

open class TusingTime(val id : String)

