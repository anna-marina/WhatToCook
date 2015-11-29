package whattocook.app

import android.content.Context
import android.content.res.Resources
import org.json.JSONArray
import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*

object FoodIndex {

    var context: Context? = null;
        set(c) {
            field = c
            res = context?.resources
            sel = HashSet()
            fetchCategories()
        }
    private var res: Resources? = null
    private var sel: Set<Int> = HashSet()
    public  var allItems = LinkedList<String>()
    public  var categories = arrayOf<Category>(); private set

    private fun readRaw(id: Int): String {
        val inputStream = res?.openRawResource(id);
        val br = BufferedReader(InputStreamReader(inputStream))
        var res = ""
        while (true) {
            val line = br.readLine()
            if (line != null)
                res += line + '\n';
            else
                break;
        }
        return res
    }

    private fun fetchCategories() {
        buildCategories(readRaw(R.raw.categories))
    }

    private fun buildCategories(json: String) {
        val jsonCategories = JSONArray(json)
        var categoryList: List<Category> = LinkedList()
        for (i in 0..jsonCategories.length() - 1) {
            val jsonCategory = jsonCategories.getJSONObject(i)
            val jsonFoods = jsonCategory.optJSONArray("items")
            var foodList: List<Food> = LinkedList()
            for (j in 0..jsonFoods.length() - 1) {
                val jsonFood = jsonFoods.getJSONObject(j)
                val itemId = jsonFood.getInt("id")
                val itemName = jsonFood.getString("name")
                foodList += Food(itemId, itemName, false)
            }
            val itemArray = foodList.toTypedArray()
            val categoryId = jsonCategory.getInt("id")
            val categoryName = jsonCategory.getString("name")
            categoryList += Category(categoryId, categoryName, itemArray)
        }
        categories = categoryList.toTypedArray()
    }

    public fun getDisplayName(name: String): String? {
        val id = res?.getIdentifier(name, "string", context?.packageName)
        if (id != null)
            return res?.getString(id)
        return null
    }

    public fun add(id: Int) {
        sel += id
    }

    public fun remove(id: Int) {
        sel -= id
    }

    public fun composeRecipes(): Array<Recipe> {
        val jsonRecipes = JSONArray(readRaw(R.raw.recipes))
        var recipesList: List<Recipe> = LinkedList()
        for (i in 0..jsonRecipes.length() - 1) {
            val jsonRecipe = jsonRecipes.getJSONObject(i)
            val jsonRecipeIn = jsonRecipe.getJSONArray("ingridients")
            var recipeIn: List<Int> = LinkedList()
            for (j in 0..jsonRecipeIn.length() - 1)
                recipeIn += jsonRecipeIn.getInt(j)
            if (!sel.containsAll(recipeIn))
                continue
            val recipeInA = recipeIn.toTypedArray()
            val recipeTitle = jsonRecipe.getString("title")
            val recipePrep = jsonRecipe.getString("preparation")
            val recipeAct = jsonRecipe.getString("actions")
            val recipe = Recipe(recipeInA, recipeTitle, recipePrep, recipeAct)
            recipesList += recipe
        }
        return recipesList.toTypedArray()
    }
}