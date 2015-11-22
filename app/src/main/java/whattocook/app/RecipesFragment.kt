package whattocook.app

import android.app.Fragment
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

class RecipesFragment: Fragment() {

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?): View? {
        val v = inflater.inflate(R.layout.recycler_fragment, null)

        val mRecyclerView = v?.findViewById(R.id.list_recycler_view)
                as RecyclerView

        mRecyclerView.setHasFixedSize(true)
        val mLayoutManager = LinearLayoutManager(activity)
        mRecyclerView.layoutManager = mLayoutManager
        val mAdapter = RecipeRecyclerAdapter()
        mRecyclerView.adapter = mAdapter

        return v
    }
}