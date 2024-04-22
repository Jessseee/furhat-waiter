package furhatos.app.waiter.flow.main

import furhatos.app.WaiterType
import furhatos.app.nlu.*
import furhatos.app.waiterType
import furhatos.flow.kotlin.*
import furhatos.gestures.Gestures
import furhatos.nlu.common.*
import furhatos.util.Language

fun acceptOrder(dish: Dish?, drink: Drink?) = utterance {
    random {
        +Gestures.Nod
        +Gestures.Smile
    }
    +confirm
    if (dish != null)
        +"the $dish"
    if (dish != null && drink != null)
        +" and "
    if (drink != null)
        +"a $drink"
}

val confirm = utterance {
    random {
        +"Alright"
        +"Okay"
        +""
    }
}

val TakeOrder: State = state(Attend) {
    init {
        furhat.ask {
            +confirm
            random {
                +"what may it be?"
                +"What would you like to order?"
            }
        }
    }

    onEntry {
        furhat.ask ("And what would you like to order?")
    }

    onResponse<Explain> {
        val dish = it.intent.dish
        val dishEnum = dish.toString().replace(" ", "_").uppercase()
        furhat.say {
            random {
                +"Ah, yes."
                +""
            }
            +"The $dish is"
            when {
                waiterType == WaiterType.BAD ->
                    +DishDescriptor.valueOf(dishEnum).badDescriptions[(0..2).random()]
                waiterType == WaiterType.GOOD ->
                    +DishDescriptor.valueOf(dishEnum).goodDescriptions[(0..2).random()]
            }
        }
        furhat.askYN({
            random {
                +"Would you like to try it?"
                +"Does this sound good to you?"
            }
        }) {
            onResponse<Yes> {
                users.current.order.dish = dish
                users.current.order.hasOrdered = true
                furhat.say(acceptOrder(dish, null))
                goto(amendOrder)
            }
            onResponse<No> {
                furhat.ask{
                    +confirm
                    random {
                        +"Is there anything else to your liking?"
                        +"Would you like to try something else?"
                        +"What would you like instead?"
                    }
                }
            }
        }
    }

    onResponse<Order> {
        var dish = it.intent.dish
        val drink = it.intent.drink

        var oldDish: String? = dish?.text?.lowercase()
        var newDish: String? = null

        if (waiterType == WaiterType.BAD) {  // Randomize the dish
            newDish = Dish().getEnum(Language.ENGLISH_US).random()
            dish?.text = newDish
            dish?.value = newDish
        }

        users.current.order.dish = dish
        users.current.order.drink = drink
        users.current.order.hasOrdered = true

        goto(finishOrder(oldDish, newDish))
    }

    onNoResponse {
        furhat.askYN("Do you need more time?") {
            onResponse<Yes> {
                furhat.say(confirm)
                goto(nextOrder)
            }
            onResponse<Enough> {
                furhat.say(confirm)
                goto(nextOrder)
            }
            onResponse<No> {
                furhat.say(confirm)
                reentry()
            }
        }
    }
}

fun finishOrder(oldDish: String? = null, newDish: String? = null): State = state(Attend) {
    onEntry {
        val dish = users.current.order.dish
        val drink = users.current.order.drink
        furhat.say(acceptOrder(dish, drink), interruptable=true)
        if (newDish != null && newDish != oldDish)
            furhat.listen(1500)
        goto(amendOrder)
    }

    onResponse<Incorrect> {
        var dish = it.intent.dish
        when {
            dish != null -> furhat.say("Sorry, the $dish")
            else -> {
                val order = furhat.askFor<Order>("Sorry, what would you like instead?")
                dish = order?.dish
            }
        }
        users.current.order.dish = dish
        goto(amendOrder)
    }

    onNoResponse {
        goto(amendOrder)
    }
}

val amendOrder: State = state(Attend) {
    onEntry {
        when {
            users.current.order.drink == null -> furhat.ask("Would you also like a drink with that?")
            users.current.order.dish == null -> furhat.ask("Would you also like something to eat?")
        }
    }

    onResponse<Order> {
        val newDish = it.intent.dish
        val newDrink = it.intent.drink
        when {
            newDish != null -> users.current.order.dish = newDish
            newDrink != null -> users.current.order.drink = newDrink
        }
        furhat.say(confirm)
        goto(nextOrder)
    }

    onResponse<No> {
        furhat.say(confirm)
        if (users.current.order.dish == null) {
            furhat.say {
                random {
                    +"Just"
                    +"Only"
                }
                random {
                    +"a drink"
                    +"a ${users.current.order.drink}"
                }
            }
        }
        goto(nextOrder)
    }
}

val nextOrder: State = state(Attend) {
    onEntry {
        val everyoneOrdered = users.list.all { user -> user.order.hasOrdered }
        when {
            !everyoneOrdered -> {
                val usersWithoutOrder = users.list.filter { user -> user.order.dish == null }
                furhat.attend(usersWithoutOrder.random())
                goto(TakeOrder)
            } else -> {
                goto(ListOrders)
            }
        }
    }
}
