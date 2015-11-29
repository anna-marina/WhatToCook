package whattocook.app

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.Menu

class RecipesActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.recipes_activity)

        var myToolbar = findViewById(R.id.my_toolbar) as Toolbar
        setSupportActionBar(myToolbar)

        val found = FoodIndex.composeRecipes()

        val tr = fragmentManager.beginTransaction()
        val frag =
            if (found.size == 0)
                NoResultsFragment()
            else
                RecipesFragment(found)
        if (savedInstanceState == null)
            tr.add(R.id.frag_content, frag).commit()
        else
            tr.replace(R.id.frag_content, frag).commit()
    }

    public override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater;
        inflater.inflate(R.menu.appbar, menu);
        return true;
    }

}