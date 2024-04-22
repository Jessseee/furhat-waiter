package furhatos.app.nlu

import furhatos.nlu.EnumEntity
import furhatos.util.Language

enum class DishDescriptor(
    val goodDescriptions: List<String>,
    val badDescriptions: List<String>
) {
    WHISPERING_FOREST(
        listOf(
            "A hearty stew filled with foraged mushrooms, root vegetables, and whispers of herbs, reminiscent of a quiet stroll through a misty forest.",
            "A comforting blend of woodland flavors, featuring earthy mushrooms, tender vegetables, and a hint of mystery, like a secret shared among the trees.",
            "An enchanting stew that captures the essence of the forest, brimming with aromatic herbs, wild mushrooms, and the gentle rustle of leaves, evoking the tranquility of nature's embrace."
        ),
        listOf(
            "A hearty stew that seems to whisper secrets of the forest with every spoonful, filled with mysterious ingredients like foraged mushrooms and elusive herbs.",
            "An intriguing stew that beckons you to explore its depths, with flavors reminiscent of a quiet walk through a misty woodland, featuring elements like wild mushrooms and earthy roots.",
            "A comforting stew that hints at its woodland origins, with flavors that evoke images of ancient trees and hidden clearings, blending hearty mushrooms and earthy vegetables in a magical pot."
        )
    ),

    MOONLIT_SERENADE(
        listOf(
            "A celestial-inspired pasta dish bathed in a creamy sauce, studded with tender chicken or savory mushrooms, and kissed by the gentle glow of moonlight.",
            "A pasta creation that dances with celestial flavors, intertwining strands of noodles with a velvety sauce, accented by celestial seasonings and a touch of lunar luminescence.",
            "A pasta symphony under the moon's spell, featuring al dente noodles swirled in a silky sauce, harmonizing with celestial herbs and the ethereal glow of moonlight, serenading the senses with every bite."
        ),
        listOf(
            "A dish that seems to have been touched by moonbeams, with a creamy sauce that shimmers like starlight.",
            "An enchanting creation that sings a serenade to the moon and ingredients that dance in the moon's glow.",
            "A dish that whispers of moonlit adventures, featuring a sauce as creamy as the night sky, inviting you to indulge in its nocturnal charm."
        )
    ),

    SUNDOWN_SURPRISE(
        listOf(
            "A refreshing salad bursting with vibrant colors and flavors, showcasing seasonal fruits, crisp greens, and a surprise medley of crunchy nuts or tangy cheese, capturing the essence of a sunset picnic.",
            "A salad that unfolds like the changing hues of a sunset, with a kaleidoscope of fresh ingredients mingling harmoniously, crowned with a surprise twist that tantalizes the taste buds.",
            "A delightful salad that captures the magic of dusk, combining crisp greens with bursts of seasonal fruits, toasted nuts, and an unexpected surprise, a culinary ode to the fading rays of the sun."
        ),
        listOf(
            "A dish that holds the promise of a surprise at every bite, with a kaleidoscope of colors and flavors from seasonal ingredients",
            "A dish that captures the essence of a sundown surprise, with bursts of color and flavor in every mouthful, with unexpectedly vibrant ingredients.",
            "A dish that seems to whisper secrets of the setting sun, with vibrant hues and fresh flavors mingling in every forkful, hiding a surprise blend ingredients."
        )
    ),

    MISTY_MOUNTAIN(
        listOf(
            "A dish inspired by the misty peaks, featuring a medley of hearty grains, roasted vegetables, and savory proteins, blanketed in a velvety sauce that whispers of high-altitude adventure.",
            "A culinary journey to the misty heights, where layers of roasted vegetables, grains, and savory delights converge, enveloped in a mist-like sauce that carries the essence of mountain air.",
            "An elevated dish echoing the whispers of the mountain breeze, with a medley of flavors and textures, from roasted vegetables to grains and protein, all united under a misty veil of savory sauce, evoking the majesty of towering peaks."
        ),
        listOf(
            "A dish that seems to evoke the mystique of misty peaks, with a blend of exciting ingredients all blanketed in a velvety sauce.",
            "A culinary journey that promises much but leaves much to the imagination, with layers of delights that seem to hint at high-altitude adventures.",
            "An elevated dish that echoes the whispers of the mountain breeze, with a medley of flavors and textures that evoke images of misty peaks and mountain air."
        )
    ),

    ETERNAL_DELIGHT(
        listOf(
            "A decadent cake that echoes through time, with layers of moist sponge, luscious frosting, and a hidden surprise nestled within, inviting you to savor the sweetness of eternity.",
            "A cake that captures the essence of forever, with its layers of tender cake, luxurious frosting, and a hidden depth that resonates with every bite, a sweet echo of eternity.",
            "A divine creation that transcends time, with layers of heavenly cake, ethereal frosting, and a secret surprise that whispers of eternity, inviting you to indulge in the everlasting flavors of bliss."
        ),
        listOf(
            "A dish that seems to hold the promise of timeless delight, with ingredients that seem to echo through eternity, hiding a surprise within its sweet embrace.",
            "A divine creation that hints at the eternity of indulgence, with layers that resonate with every bite, whispering secrets of sweetness beyond time.",
            "A dish that promises to transcend the bounds of time, with layers that echo with the promise of everlasting bliss, inviting you to savor the eternity of its flavors."
        )
    );
}

class Dish: EnumEntity(stemming = true, speechRecPhrases = true) {
    override fun getEnum(lang: Language): List<String> {
        val dishes = DishDescriptor.values().map { it.name.replace("_", " ").lowercase() }
        return dishes
    }
}