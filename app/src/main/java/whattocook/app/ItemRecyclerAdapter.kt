package whattocook.app

import android.animation.Animator
import android.app.Activity
import android.app.FragmentTransaction
import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewAnimationUtils
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast

class ItemRecyclerAdapter// Конструктор
(private val cat: Category?) :
        RecyclerView.Adapter<ItemRecyclerAdapter.ItemViewHolder>() {

    // класс view holder-а с помощью которого мы получаем ссылку на каждый элемент
    // отдельного пункта списка
    private inner class ItemViewHolder(v: View) : RecyclerView.ViewHolder(v), View.OnClickListener {
        override fun onClick(v: View) {
            chView.toggle()
            cat!!.subItems[index].toggle()
        }

        // наш пункт состоит только из одного TextView
        var mTextView: TextView
        var chView: CheckView
        public var index: Int = 0
            set(i) {
                field = i
                if (cat!!.subItems[i].selected)
                    chView.set()
                else
                    chView.unset()
            }
        public var cat: Category? = null

        init {
            itemView.isClickable = true
            itemView.setOnClickListener(this)
            mTextView = v.findViewById(R.id.tv_recycler_item) as TextView
            chView = v.findViewById(R.id.cv_recycler_item) as CheckView
        }
    }

    // Создает новые views (вызывается layout manager-ом)
    override fun onCreateViewHolder(parent: ViewGroup,
                                    viewType: Int): ItemRecyclerAdapter.ItemViewHolder {
        // create a new view
        val v = LayoutInflater.from(parent.context).inflate(R.layout.element_recycler_item, parent, false)

        // тут можно программно менять атрибуты лэйаута (size, margins, paddings и др.)

        val vh = ItemViewHolder(v)
        return vh
    }

    // Заменяет контент отдельного view (вызывается layout manager-ом)
    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.cat = cat
        holder.index = position
        holder.mTextView.text = cat!!.subItems[position].display
    }

    // Возвращает размер данных (вызывается layout manager-ом)
    override fun getItemCount(): Int {
        return cat!!.subItems.size
    }
}
