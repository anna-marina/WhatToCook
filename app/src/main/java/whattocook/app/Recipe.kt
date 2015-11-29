package whattocook.app

data class Recipe(
    val ingridients: Array<Int>,
    val title: String,
    val preparation: String,
    val actions: String
)