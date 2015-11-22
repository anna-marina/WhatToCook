package whattocook.app

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    protected override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main)

        var myToolbar = findViewById(R.id.my_toolbar) as Toolbar
        setSupportActionBar(myToolbar)

        if (savedInstanceState == null) {
            // при первом запуске программы
            fragmentManager.beginTransaction()
                    .add(R.id.frag_content, CategoriesFragment())
                    .commit()
        }

        FoodIndex.context = this
    }

    public override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater;
        inflater.inflate(R.menu.appbar, menu);
        return true;
    }

    override fun onBackPressed() {
        if (fragmentManager.backStackEntryCount > 0 ){
            fragmentManager.popBackStack()
        } else {
            super.onBackPressed()
        }
    }

    public override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item?.itemId == R.id.action_done) {
            val intent = Intent(this, RecipesActivity::class.java);
            startActivity(intent);
            return true
        }
        return false
    }

}
