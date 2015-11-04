package whattocook.app

import android.animation.Animator
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewAnimationUtils
import android.view.ViewGroup
import android.widget.TextView

class RecyclerAdapter// Конструктор
(private val mDataset: Array<String>) : RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {

    // класс view holder-а с помощью которого мы получаем ссылку на каждый элемент
    // отдельного пункта списка
    class ViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        // наш пункт состоит только из одного TextView
        var mTextView: TextView

        init {
            mTextView = v.findViewById(R.id.tv_recycler_item) as TextView
        }
    }

    // Создает новые views (вызывается layout manager-ом)
    override fun onCreateViewHolder(parent: ViewGroup,
                                    viewType: Int): RecyclerAdapter.ViewHolder {
        // create a new view
        val v = LayoutInflater.from(parent.context).inflate(R.layout.recycler_item, parent, false)

        // тут можно программно менять атрибуты лэйаута (size, margins, paddings и др.)

        val vh = ViewHolder(v)
        return vh
    }

    // Заменяет контент отдельного view (вызывается layout manager-ом)
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.mTextView.text = mDataset[position]

    }

    // Возвращает размер данных (вызывается layout manager-ом)
    override fun getItemCount(): Int {
        return mDataset.size
    }
}
