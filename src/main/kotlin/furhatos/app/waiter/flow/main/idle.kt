package furhatos.app.waiter.flow.main

import furhatos.flow.kotlin.*

val Idle: State = state {
    onEntry {
        when {
            users.hasAny() -> {
                furhat.attend(users.random)
                goto(Greeting)
            }
            else -> {
                furhat.attendNobody()
            }
        }
    }

    onUserEnter {
        when {
            !users.hasAny() -> {
                furhat.attend(it)
                goto(Greeting)
            }
        }
    }
}

