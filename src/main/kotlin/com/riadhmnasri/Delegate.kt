package com.riadhmnasri

// Delegation
interface Hobby {
    fun name(): String
    fun cost(): String
}

class Tennis : Hobby {
    override fun name(): String = "Play Tennis"
    override fun cost(): String = "Expensive"
}


class Activity(hobby: Hobby) : Hobby by hobby {
    override fun name(): String = "Activity :: Play Tennis"
}