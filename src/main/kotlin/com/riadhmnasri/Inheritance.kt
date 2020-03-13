package com.riadhmnasri

// Inheritance
open class Vehicle {
    open fun name(): String {
        return "Vehicle"
    }
}

class Car : Vehicle() {
    override fun name(): String {
        return "Car"
    }
}
