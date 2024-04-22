package furhatos.app

import furhatos.app.waiter.flow.Init
import furhatos.skills.Skill
import furhatos.flow.kotlin.*

enum class WaiterType { GOOD, BAD }
var waiterType: WaiterType = WaiterType.GOOD


class WaiterSkill : Skill() {
    override fun start() {
        println(waiterType.name)
        Flow().run(Init)
    }
}

fun main(args: Array<String>) {
    if (args.contains("--bad")) {
        waiterType = WaiterType.BAD
    }
    Skill.main(args)
}
