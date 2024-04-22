package furhatos.app.nlu

import furhatos.nlu.*
import furhatos.util.Language

class ReadyToOrder: Intent() {
    override fun getExamples(lang: Language): List<String> {
        return listOf("we are ready to order", "could we order now", "we would like to order", "we are ready")
    }
}

class Explain(var dish: Dish? = null): Intent() {
    override fun getExamples(lang: Language): List<String> {
        return listOf("Could you explain @dish", "What is @dish", "I am not sure what @dish is")
    }
}

class Enough: Intent() {
    override fun getExamples(lang: Language): List<String> {
        return listOf("No, that is it", "Enough", "No more", "That is all")
    }
}

class Restart: Intent() {
    override fun getExamples(lang: Language): List<String> {
        return listOf("From the start", "Try that again", "Shut up")
    }
}

class Incorrect(var dish: Dish? = null): Intent() {
    override fun getExamples(lang: Language): List<String> {
        return listOf("No that is not right", "No, I would like the @dish", "No, the @dish")
    }
}

class Order(var dish: Dish? = null, var drink: Drink? = null): Intent() {
    override fun getExamples(lang: Language): List<String> {
        return listOf(
            "@dish",
            "the @dish",
            "Could I have the @dish",
            "I would like the @dish",
            "I want @dish",
            "@drink",
            "a @drink",
            "Could I have a @drink",
            "I would like a @drink",
            "I want @drink",
            "@dish, @drink",
            "the @dish and a @drink",
            "a @drink and the @dish",
            "I would like a @drink and the @dish",
            "I would like the @dish and a @drink",
            "Could I have a @drink and the @dish",
            "Could I have the @dish and a @drink"
        )
    }
}
