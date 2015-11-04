package whattocook.app

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private var mRecyclerView: RecyclerView? = null
    private var mAdapter: RecyclerView.Adapter<RecyclerAdapter.ViewHolder>? = null
    private var mLayoutManager: RecyclerView.LayoutManager? = null

    protected override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main)

        val myDataset = resources.getStringArray(R.array.categories_view)

        mRecyclerView = findViewById(R.id.my_recycler_view) as RecyclerView

        // если мы уверены, что изменения в контенте не изменят размер layout-а RecyclerView
        // передаем параметр true - это увеличивает производительность
        mRecyclerView!!.setHasFixedSize(true)

        // используем linear layout manager
        mLayoutManager = LinearLayoutManager(this)
        mRecyclerView!!.layoutManager = mLayoutManager
        // создаем адаптер
        mAdapter = RecyclerAdapter(myDataset)
        mRecyclerView!!.adapter = mAdapter

        mRecyclerView!!.addItemDecoration(
                DividerItemDecoration(this))
    }

}
