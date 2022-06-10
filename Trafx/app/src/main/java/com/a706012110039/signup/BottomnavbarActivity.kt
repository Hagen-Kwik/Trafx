package com.a706012110039.signup

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.a706012110039.signup.databinding.ActivityBottomnavbarBinding
class BottomnavbarActivity : AppCompatActivity() {
    private lateinit var viewbind: ActivityBottomnavbarBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewbind = ActivityBottomnavbarBinding.inflate(layoutInflater)
        setContentView(viewbind.root)
        supportActionBar?.hide()
        setcurfragment(MenuhomeFragment())

        viewbind.bottomNavigationViewBottomnavbar.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.menu1_home -> setcurfragment(MenuhomeFragment())
                R.id.menu2_market -> setcurfragment(MenumarketFragment())
                R.id.menu2_portfolio -> setcurfragment(MenuportfolioFragment())
            }
            true
        }
    }

    private fun setcurfragment(fragment: Fragment){
        this.supportFragmentManager.beginTransaction().apply {
            replace(viewbind.framelayoutviewBottomnavigationview.id, fragment)
            commit()
        }
    }
}