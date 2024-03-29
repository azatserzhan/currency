package kz.azatserzhanov.test.currency.model

data class Currency(val rates: Map<String, Double>, val base: String, val date: String)

data class CurrencyItem(val name: String, val value: Double)