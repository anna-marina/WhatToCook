package whattocook.app

import android.os.Bundle
import android.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

class CategoriesFragment : Fragment() {

    public override fun onCreateView(inflater: LayoutInflater?,
                                     container: ViewGroup?,
                                     savedInstanceState: Bundle?): View? {
        val v = inflater?.inflate(R.layout.recycler_fragment, null)

        val mRecyclerView = v?.findViewById(R.id.list_recycler_view) as RecyclerView

        if (mRecyclerView != null) {
            mRecyclerView!!.setHasFixedSize(true)

            val myDataset = resources.getStringArray(R.array.categories_view)
            val mLayoutManager = LinearLayoutManager(activity)
            mRecyclerView!!.layoutManager = mLayoutManager

            val mAdapter = CategoryRecyclerAdapter(myDataset, activity)
            mRecyclerView!!.adapter = mAdapter

            mRecyclerView!!.addItemDecoration(
                    DividerItemDecoration(activity))

            mRecyclerView!!
        }

        return v
    }

}