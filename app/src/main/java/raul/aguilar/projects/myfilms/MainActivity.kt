package raul.aguilar.projects.myfilms

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val bottomNavigationView:BottomNavigationView = findViewById(R.id.bottom_navigation)

        createFragment(PopularFilmsFragment())

        bottomNavigationView.setOnNavigationItemSelectedListener {item ->

            when(item.itemId){

                R.id.popular_tab ->{
                    val popularFilmsFragment = DetailFilmFragment()
                    createFragment(popularFilmsFragment)
                    true
                }
                R.id.search_tab ->{
                    val searchFilmsFragment = SearchFilmsFragment()
                    createFragment(searchFilmsFragment)
                    true
                }
                R.id.my_lists_tab ->{
                    val myListsFilmsFragment = MyListsFilmsFragment()
                    createFragment(myListsFilmsFragment)
                    true
                }

                else -> false
            }


        }

    }

    fun createFragment(fragment:Fragment){

       val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.container,fragment)
        fragmentTransaction.addToBackStack(null)
        fragmentTransaction.commit()

    }
}