package furhatos.app.waiter.flow.main

import furhatos.app.nlu.*
import furhatos.flow.kotlin.*

val Attend: State = state {
    onUserEnter(instant = true) {
        when {
            furhat.isAttendingUser -> furhat.glance(it)
            !furhat.isAttendingUser -> furhat.attend(it)
        }
    }

    onUserLeave(instant = true) {
        when {
            !users.hasAny() -> {
                furhat.attendNobody()
                goto(Idle)
            }
            furhat.isAttending(it) -> furhat.attend(users.other)
            !furhat.isAttending(it) -> furhat.glance(it.head.location)
        }
    }

    onResponse<Restart> {
        goto(Greeting)
    }
}

