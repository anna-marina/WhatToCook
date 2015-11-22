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
            x = HashMap()
            fetchCategories()
        }
    private var res: Resources? = null
    private var x: HashMap<String, BooleanArray> = HashMap()
    public  var allItems = LinkedList<String>()
    public  var categories = arrayOf<Category>(); private set

    private fun fetchCategories() {
        val inputStream = res?.openRawResource(R.raw.categories);
        val br = BufferedReader(InputStreamReader(inputStream))
        var res = ""
        while (true) {
            val line = br.readLine()
            if (line != null)
                res += line + '\n';
            else
                break;
        }
        buildCategories(res)
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

    public fun getArray(name: String): Array<String>? {
        val id = res?.getIdentifier(name, "array", context?.packageName)
        if (id != null)
         return res?.getStringArray(id)
        return null
    }

    public fun getItem(name: String, index: Int): String {
        val a = getArray(name)
        return a!![index]
    }

    public fun getChecked(name: String, i: Int): Boolean {
        if (x.containsKey(name) && i < x[name]?.size!!) {
            return x[name]!![i]
        } else {
            return false
        }
    }

    public fun toggle(name: String, i: Int) {
        if (!x.containsKey(name)) {
            val a = getArray(name)
            x[name] = BooleanArray(a!!.size)
        }
        x[name]!![i] = !x[name]!![i]
        val item = getItem(name, i)
        if (x[name]!![i])
            allItems.add(item)
        else if (allItems.contains(item))
            allItems.remove(item)
    }
}