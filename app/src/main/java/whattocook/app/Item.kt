package whattocook.app

abstract class Item(public val id: Int, public val name: String) {

    val display: String?
        public get() = FoodIndex.getDisplayName(name)

}