package edu.co.icesi.semana4kotlinv2

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import edu.co.icesi.semana4kotlinv2.databinding.FragmentTaskListBinding
import java.util.*

class TaskListFragment : Fragment(), NewTaskFragment.OnNewTaskListener {

    private  var _binding:FragmentTaskListBinding?= null
    private val binding get() = _binding!!

    //State
    private val adapter = TasksAdapter()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentTaskListBinding.inflate(inflater, container,false)
        val view = binding.root

        //Recrear el estado
        val taskRecycler = binding.taskRecylcer
        taskRecycler.setHasFixedSize(true)
        taskRecycler.layoutManager = LinearLayoutManager(activity)
        taskRecycler.adapter = adapter

        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        @JvmStatic
        fun newInstance() = TaskListFragment()
    }

    //Método que se ejecuta desde el NewFragment
    override fun onNewTask(task: String) {
        //Modificamos el estado
        val newTask = Task(UUID.randomUUID().toString(), task)
        adapter.addTask(newTask)
    }
}