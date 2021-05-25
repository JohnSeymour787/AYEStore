package models

interface PaymentMethod
{
    fun processPayment(): String
}