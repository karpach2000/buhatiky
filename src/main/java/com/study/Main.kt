package com.study

/**
 * Считает кто кому сколько должен.
 * (по крайней мере он так говорит)
 */
val cleverMasha = Masha()

fun main()
{
    //интервалы времени
    val saturdayTime = TusingTime("saturday")
    val sundayTime = TusingTime("sunday")
    val afterTime = TusingTime("after")

    //покупки
    val purchaseKolaSatSun = Purchase("Коля сб вс", 1000)
    purchaseKolaSatSun.tusingTimes.add(saturdayTime)
    purchaseKolaSatSun.tusingTimes.add(sundayTime)
    purchaseKolaSatSun.tusingTimes.add(afterTime)

    val purchaseKolaAfter = Purchase("Коля после", 1500)
    purchaseKolaAfter.tusingTimes.add(afterTime)

    val purchaseMasha = Purchase("Маша", 1500)
    purchaseMasha.tusingTimes.add(saturdayTime)
    purchaseMasha.tusingTimes.add(sundayTime)
    purchaseMasha.tusingTimes.add(afterTime)

    val purchaseIra = Purchase("Ира", 1000)
    purchaseIra.tusingTimes.add(saturdayTime)
    purchaseIra.tusingTimes.add(sundayTime)
    purchaseIra.tusingTimes.add(afterTime)

    val purchaseKirillSat = Purchase("Кирилл субота", 2800)
    purchaseKirillSat.tusingTimes.add(saturdayTime)

    val purchaseKirill = Purchase("Кирилл", 800)
    purchaseKirill.tusingTimes.add(saturdayTime)
    purchaseKirill.tusingTimes.add(sundayTime)
    purchaseKirill.tusingTimes.add(afterTime)

    val purchaseSasha = Purchase("Саша", 800)
    purchaseSasha.tusingTimes.add(saturdayTime)
    purchaseSasha.tusingTimes.add(sundayTime)
    purchaseSasha.tusingTimes.add(afterTime)

    //люди
    val kirill = Man("Кирилл")
    kirill.tusingTimes.add(saturdayTime)
    kirill.tusingTimes.add(sundayTime)
    kirill.tusingTimes.add(afterTime)
    kirill.purchases.add(purchaseKirillSat)
    kirill.purchases.add(purchaseKirill)

    val elina = Man("Элина")
    elina.tusingTimes.add(saturdayTime)
    elina.tusingTimes.add(sundayTime)

    val angelina = Man("Ангелина")
    angelina.tusingTimes.add(sundayTime)


    val sasha = Man("Саша")
    sasha.tusingTimes.add(sundayTime)
    sasha.purchases.add(purchaseSasha)

    val ira = Man("Ира")
    ira.tusingTimes.add(saturdayTime)
    ira.tusingTimes.add(sundayTime)
    ira.purchases.add(purchaseIra)

    val masha = Man("Маша")
    masha.tusingTimes.add(saturdayTime)
    masha.tusingTimes.add(sundayTime)
    masha.tusingTimes.add(afterTime)
    masha.purchases.add(purchaseMasha)

    val dima = Man("Дима")
    dima.tusingTimes.add(saturdayTime)
    dima.tusingTimes.add(sundayTime)

    val kola = Man("Коля")
    kola.tusingTimes.add(sundayTime)
    kola.tusingTimes.add(afterTime)
    kola.purchases.add(purchaseKolaAfter)
    kola.purchases.add(purchaseKolaSatSun)

    val ola = Man("Оля")
    ola.tusingTimes.add(sundayTime)
    ola.tusingTimes.add(afterTime)


    cleverMasha.manList.add(kirill)
    cleverMasha.manList.add(elina)
    cleverMasha.manList.add(angelina)
    cleverMasha.manList.add(sasha)
    cleverMasha.manList.add(ira)
    cleverMasha.manList.add(masha)
    cleverMasha.manList.add(dima)
    cleverMasha.manList.add(kola)
    cleverMasha.manList.add(ola)

    cleverMasha.countCosts()
    println(cleverMasha.toString())










}