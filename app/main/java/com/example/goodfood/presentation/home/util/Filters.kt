package com.example.goodfood.presentation.home.util

enum class Filter(val value: String) {
    FASTDELIVERY("Livraison rapide"),
    DISCOUNT("50% de Remise"),
    GREATDEALS("Bonnes Affaires"),
    NONVEG("Non-Veg")

}

fun getAllTags(): List<Filter> {
    return listOf(
        Filter.FASTDELIVERY, Filter.DISCOUNT, Filter.GREATDEALS,
        Filter.NONVEG
    )
}

fun getTag(value: String): Filter? {
    val map = Filter.values().associateBy(Filter::value)
    return map[value]
}

