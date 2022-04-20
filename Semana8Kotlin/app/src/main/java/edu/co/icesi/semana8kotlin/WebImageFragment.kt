package edu.co.icesi.semana8kotlin

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import edu.co.icesi.semana8kotlin.databinding.FragmentWebImageBinding

class WebImageFragment : DialogFragment() {

    private lateinit var binding: FragmentWebImageBinding

    var listener:OnURLListener? = null

    override fun onStart() {
        super.onStart()
        dialog?.window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //Inflate the layout for this fragment
        binding = FragmentWebImageBinding.inflate(inflater, container, false)

        binding.showBtn.setOnClickListener {
            listener?.onURL(binding.urlET.text.toString())
            dismiss()
        }

        return binding.root
    }

    interface OnURLListener{
        fun onURL(url:String)
    }


    companion object{
        @JvmStatic
        fun newInstance() = WebImageFragment()
    }
}