package whattocook.app

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

class RecipeRecyclerAdapter:
        RecyclerView.Adapter<RecipeRecyclerAdapter.ItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder? {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.recipe_recycler_item, parent, false)

        val vh = ItemViewHolder(v)
        return vh
    }

    override fun getItemCount(): Int {
        return 3
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.head.text = "Яичница с помидорами"
        holder.recipe.text = FoodIndex.allItems.joinToString(", ")
    }

    private inner class ItemViewHolder(v: View) : RecyclerView.ViewHolder(v), View.OnClickListener {
        override fun onClick(v: View) {

        }

        var head: TextView
        var recipe: TextView

        init {
            itemView.isClickable = true
            itemView.setOnClickListener(this)
            head = v.findViewById(R.id.recipe_header) as TextView
            recipe = v.findViewById(R.id.recipe_text) as TextView
        }
    }

}