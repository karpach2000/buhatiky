package com.study

import java.util.stream.Collectors
import java.util.stream.Stream

/**
 * Считает кто кому сколько должен.
 */
class Masha {

    var manList = ArrayList<Man>()
    //var tusingTimeList = ArrayList<TusingTime>()

    /**
     * Считает кто кому сколько должен.
     *  - Если ты присутсвовал, во  время Purchase ты платишь стоимость Purchase.sum деленное на максимальное
     * количество людей которое при этом присутсвовало
     *  - бухатики разбиваются на временные отрезки, в каждой Purchase содержится лист
     *  временных отрезков когда она использовалась
     */
    fun countCosts()
    {
        //Обнуляем баланс
        manList.parallelStream().map( { man -> man.balance = 0 }).collect(Collectors.toList())
        //Считаем расходы
        //manList.forEach { man ->man.balance-=countManPurhases(man)}
        manList.parallelStream().map { man ->man.balance-=countManPurhases(man)}.collect(Collectors.toList())
        //считаем расходы на человека
        //manList.forEach { man ->man.balance+=countIncome(man)}
        manList.parallelStream().map { man ->man.balance+=countIncome(man)}.collect(Collectors.toList())
        //Получаем список тех кому мы должны денег.
        manList.parallelStream().map { man ->man.creditors=getCreditors(man)}.collect(Collectors.toList())
        //взаимное погашение кредита
        manList.stream().map { man ->mutualCancellationOfDebts(man)}.collect(Collectors.toList())
    }



    /**
     * Считает траты каждого человека.
     */
    private fun countManPurhases(man: Man) : Long{
        var sum = 0L
        //man.purchases.forEach { p -> sum += p.sum }
        man.purchases.stream().map{ p -> sum += p.sum }.collect(Collectors.toList())
        return sum
    }

    /**
     * Считаем сколько общественных средсв мы заюзали.
     */
    private fun countIncome(man: Man): Long
    {
        var sum = 0L
        manList.forEach {m->
                m.purchases.forEach {p ->

                    var maxMan = 0//максимальное количество человек во время тусовки
                    for(tusingTime in p.tusingTimes)
                    {
                        var manInTusingTime = countManInTusingTime(tusingTime)//можно ускорить
                        if(man.tusingTimes.contains(tusingTime) &&  manInTusingTime> maxMan){
                            maxMan = manInTusingTime
                        }//if

                    }//for
                    if(maxMan>0) {
                        sum+=p.sum/maxMan
                    }
                }
        }
        return sum
    }

    /**
     *Получаем список тех кому мы должны денег.
     */
    private fun getCreditors(man: Man): HashMap<String, Long>
    {
        var creditors = HashMap<String, Long>()
        manList.forEach {m->
            m.purchases.forEach {p ->

                var maxMan = 0//максимальное количество человек во время тусовки

                for(tusingTime in p.tusingTimes)
                {
                    var manInTusingTime = countManInTusingTime(tusingTime)//можно ускорить
                    if(man.tusingTimes.contains(tusingTime) &&  manInTusingTime> maxMan){
                        maxMan = manInTusingTime
                    }//if
                }//for
                if(maxMan>0) {
                    if(!creditors.containsKey(m.name))
                        creditors.put(m.name, p.sum/maxMan)
                    else
                        creditors.replace(m.name, creditors.get(m.name)!! +p.sum/maxMan)
                }
            }
        }
        return creditors
    }


    /**
     * @return Количество людей в tusingTime
     */
    private fun countManInTusingTime(tusingTime: TusingTime) :Int
    {
        var countMan = 0
        manList.stream().map { m-> if(m.tusingTimes.contains(tusingTime)){countMan++} }.collect(Collectors.toList())
        return countMan
    }

    /**
     * Взаимное списание долгов.
     */
    private fun mutualCancellationOfDebts(man: Man)
    {
        if(man.creditors.containsKey(man.name))
            man.creditors.remove(man.name)

        manList.forEach { m->
            if(m.creditors.containsKey(man.name) && man.creditors.containsKey(m.name))
            {
                val manCredit = man.creditors.getValue(m.name)
                val mCredit = m.creditors.getValue(man.name)
                if(manCredit!! > mCredit!!)
                {
                    val delta = manCredit - mCredit!!
                    man.creditors.replace(m.name, delta)
                    m.creditors.remove(man.name)
                }
                else  if(manCredit!! < mCredit!!)
                {
                    val delta = -manCredit + mCredit!!
                    m.creditors.replace(man.name, delta)
                    man.creditors.remove(m.name)
                }
                else
                {
                    man.creditors.remove(m.name)
                    m.creditors.remove(man.name)
                }
            }
        }

    }

    override fun toString(): String {
        var line = ""
        manList.forEach {
            line += it.toString()
        }
        return line
    }

}