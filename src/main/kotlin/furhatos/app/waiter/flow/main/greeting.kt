package furhatos.app.waiter.flow.main

import furhatos.app.nlu.*
import furhatos.flow.kotlin.*
import furhatos.gestures.Gestures
import furhatos.nlu.common.*

val Greeting: State = state(Attend) {
    onEntry {
        furhat.gesture(Gestures.BrowRaise)
        furhat.askYN("Are you ready to order?")
        {
            onResponse<Yes> {
                goto(TakeOrder)
            }

            onResponse<No> {
                furhat.say("Let me know when you are ready.")
                furhat.listen(60000) // listen for 1 minute
                reentry()
            }
        }
    }

    onResponse<ReadyToOrder> {
        goto(TakeOrder)
    }
}