package furhatos.app.waiter.flow.main

import furhatos.app.nlu.*
import furhatos.records.User

class OrderData(
    var dish: Dish? = null,
    var drink: Drink? = null,
    var hasOrdered: Boolean = false
)

val User.order : OrderData
    get() =
        data.getOrPut(OrderData::class.qualifiedName, OrderData())