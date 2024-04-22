package furhatos.app.waiter.flow

import furhatos.app.waiter.flow.main.*
import furhatos.app.setting.*
import furhatos.flow.kotlin.*

val Init: State = state() {
    init {
        users.setSimpleEngagementPolicy(distanceToEngage, maxNumberOfUsers)
    }

    onEntry {
        goto(Idle)
    }
}
