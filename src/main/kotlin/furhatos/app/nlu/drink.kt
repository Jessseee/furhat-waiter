package furhatos.app.nlu

import furhatos.nlu.EnumEntity
import furhatos.util.Language

class Drink: EnumEntity(stemming = true, speechRecPhrases = true) {
    override fun getEnum(lang: Language): List<String> {
        return listOf("cola", "beer", "wine")
    }
}