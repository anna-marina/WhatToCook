package whattocook.app

class Category(id: Int, name: String, public val subItems: Array<Food>)
    : Item(id, name) {

}