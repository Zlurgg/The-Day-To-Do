package uk.co.zlurgg.thedaytodo.domain.utils

sealed class OrderType {
    data object Ascending: OrderType()
    data object Descending: OrderType()
}