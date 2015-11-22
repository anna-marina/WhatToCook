package whattocook.app

import android.app.Activity
import android.app.Application
import android.content.Context
import android.content.res.Resources
import java.util.*

object FoodIndex {

    var context: Context? = null;
        set(c) {
            field = c
            res = context?.resources
            x = HashMap()
        }
    private var res: Resources? = null
    private var x: HashMap<String, BooleanArray> = HashMap()
    public  var allItems = LinkedList<String>()

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