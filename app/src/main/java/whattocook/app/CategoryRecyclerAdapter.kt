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

class CategoryRecyclerAdapter// Конструктор
(private  val mActivity: Activity) :
        RecyclerView.Adapter<CategoryRecyclerAdapter.CategoryViewHolder>() {

    var cts: Array<String>? = null

    // класс view holder-а с помощью которого мы получаем ссылку на каждый элемент
    // отдельного пункта списка
    private inner class CategoryViewHolder(v: View) : RecyclerView.ViewHolder(v), View.OnClickListener {
        override fun onClick(v: View) {
            val ft = mActivity.fragmentManager.beginTransaction()
            val itf = ItemsFragment(mCategory)
            ft.replace(R.id.frag_content, itf)
            ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
            ft.addToBackStack(null);
            ft.commit();
            //chView.toggle()
        }

        // наш пункт состоит только из одного TextView
        var mTextView: TextView
        var chView: BulletView
        var mCategory: Category? = null

        init {
            itemView.isClickable = true
            itemView.setOnClickListener(this)
            mTextView = v.findViewById(R.id.tv_recycler_item) as TextView
            chView = v.findViewById(R.id.bl_recycler_item) as BulletView
        }
    }

    // Создает новые views (вызывается layout manager-ом)
    override fun onCreateViewHolder(parent: ViewGroup,
                                    viewType: Int): CategoryRecyclerAdapter.CategoryViewHolder {
        // create a new view
        val v = LayoutInflater.from(parent.context).inflate(R.layout.group_recycler_item, parent, false)

        // тут можно программно менять атрибуты лэйаута (size, margins, paddings и др.)

        val vh = CategoryViewHolder(v)
        cts = v.resources.getStringArray(R.array.categories_name)
        return vh
    }

    // Заменяет контент отдельного view (вызывается layout manager-ом)
    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        holder.mCategory = FoodIndex.categories[position]
        holder.mTextView.text = holder.mCategory?.display

    }

    // Возвращает размер данных (вызывается layout manager-ом)
    override fun getItemCount(): Int {
        return FoodIndex.categories.size
    }
}
