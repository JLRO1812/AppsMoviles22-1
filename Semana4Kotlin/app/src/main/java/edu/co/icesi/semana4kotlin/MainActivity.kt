package edu.co.icesi.semana4kotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {


    private lateinit var navigator:BottomNavigationView

    private lateinit var newTaskItemFragment: NewTaskFragment
    private lateinit var ListItemFragment: TaskListFragment


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        newTaskItemFragment = NewTaskFragment.newInstance()
        ListItemFragment = TaskListFragment.newInstance()

        navigator = findViewById(R.id.navigator)

        showFragment(newTaskItemFragment)

        navigator.setOnItemReselectedListener {
            if(menuItem.itemId == R.id.newItem){
                showFragment(newTaskItemFragment)
            }else if(menuItem.ItemId == R.id.listItem){
                showFragment(ListItemFragment)
            }
            true
        }

    }
    fun showFragment(fragment: Fragment){
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragmentContainer)
        transaction.commit()
    }
}