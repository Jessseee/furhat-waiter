package furhatos.app.waiter.flow.main

import furhatos.flow.kotlin.*
import furhatos.gestures.Gestures


val listDishes = utterance {
    +"Okay, I've got "
    for ((index, user) in users.list.withIndex()) {
        if (user.order.dish != null) {
            +attend(user)
            +delay((200..600).random())
            when {
                users.count > 1 && index == users.count - 1 && users.list[index - 1].order.dish != null -> {
                    +"and for you"
                }
                else -> random {
                    +"for you "
                    +""
                }
            }
            when {
                index >= 1 && users.list[index - 1].order.dish == user.order.dish -> random {
                    +"also a ${user.order.dish}"
                    +"a ${user.order.dish} as well"
                }
                else -> +"a ${user.order.dish}"
            }

        }
    }
}

val listDrinks = utterance {
    +"For drinks, I've got "
    for ((index, user) in users.list.withIndex()) {
        if (user.order.drink != null) {
            +attend(user)
            +delay((200..600).random())
            when {
                users.count > 1 && index == users.count - 1 && users.list[index - 1].order.drink != null -> {
                    +"and for you"
                }
                else -> random {
                    +"for you "
                    +""
                }
            }
            when {
                index >= 1 && users.list[index - 1].order.drink == user.order.drink -> random {
                    +"also a ${user.order.drink}"
                    +"a ${user.order.drink} as well"
                }
                else -> +"a ${user.order.drink}"
            }
        }
    }
}

val ListOrders: State = state(Attend) {
    onEntry {
        val anyDishesOrdered = users.list.mapNotNull{ user -> user.order.dish }.isNotEmpty()
        if (anyDishesOrdered)
            furhat.say(listDishes)
        val anyDrinksOrdered = users.list.mapNotNull{ user -> user.order.drink }.isNotEmpty()
        if (anyDrinksOrdered)
            furhat.say(listDrinks)
        furhat.say("Your orders will be coming right up")
        furhat.attendNobody()
        furhat.gesture(Gestures.CloseEyes)
    }
}