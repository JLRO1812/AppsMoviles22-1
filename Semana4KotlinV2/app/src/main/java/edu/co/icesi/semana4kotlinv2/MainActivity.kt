package edu.co.icesi.semana4kotlinv2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import edu.co.icesi.semana4kotlinv2.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var newTaskFragment: NewTaskFragment
    private lateinit var taskListFragment: TaskListFragment
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        newTaskFragment = NewTaskFragment.newInstance()
        taskListFragment = TaskListFragment.newInstance()

        //Suscripcion
        newTaskFragment.listener = taskListFragment

        showFragment(newTaskFragment)

        binding.navigator.setOnItemSelectedListener { menuItem->
            if(menuItem.itemId == R.id.newitem){
                showFragment(newTaskFragment)
            }else if(menuItem.itemId == R.id.listitem){
                showFragment(taskListFragment)
            }
            true
        }
    }

    fun showFragment(fragment: Fragment){
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragmentContanier,fragment)
        transaction.commit()
    }
}