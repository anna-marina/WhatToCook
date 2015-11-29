package whattocook.app

class Food(id: Int, name: String, initial: Boolean = false):
        Item(id, name) {

    public var selected = initial; private set

    public fun toggle() {
        selected = !selected

        if (selected)
            FoodIndex.add(id)
        else
            FoodIndex.remove(id)
    }

}