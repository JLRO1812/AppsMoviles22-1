package edu.co.icesi.semana4kotlinv2

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import edu.co.icesi.semana4kotlinv2.databinding.FragmentNewTaskBinding

class NewTaskFragment : Fragment() {

    private var _binding: FragmentNewTaskBinding? = null
    private val binding get() = _binding!!

    //Listener
    var listener: OnNewTaskListener? = null
    //_bindig -> nullable
    //biding   -> non-nullable


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.e(">>>","onCreateView")

        _binding = FragmentNewTaskBinding.inflate(inflater, container, false)
        val view = binding.root


        binding.addBtn.setOnClickListener {
            val text = binding.newItemET.text.toString()
            Toast.makeText(activity, text, Toast.LENGTH_LONG).show()

            listener?.let {
                it.onNewTask(text)
            }
        }

        return view
    }

    //Control + O
    override fun onDestroyView() {
        Log.e(">>>","onDestroyView")
        super.onDestroyView()
        _binding = null
    }

    interface OnNewTaskListener{
        fun onNewTask(task:String)
    }



    companion object {
        @JvmStatic
    fun newInstance() = NewTaskFragment()

    }


}