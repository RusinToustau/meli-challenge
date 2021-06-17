package com.meli.challenge.base

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import com.meli.challenge.databinding.GenericErrorFragmentBinding
import com.meli.challenge.extensions.hideKeyBoard

class GenericErrorFragment : Fragment() {
    private var _binding: GenericErrorFragmentBinding? = null
    private val binding get() = _binding!!

    companion object {

        fun newInstance(
            errorTitle: String? = null,
            errorDescription: String? = null,
            onPrimaryButtonAction: (() -> Unit)? = null,
            primaryButtonText: String? = null,
        ) = GenericErrorFragment().apply {
            this.errorTitle = errorTitle
            this.errorDescription = errorDescription
            this.onPrimaryButtonAction = onPrimaryButtonAction
            this.primaryButtonText = primaryButtonText
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = GenericErrorFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    private var onPrimaryButtonAction: (() -> Unit)? = null
    private var primaryButtonText: String? = null
    private var errorTitle: String? = null
    private var errorDescription: String? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setTitle(errorTitle)
        setDescription(errorDescription)
        setPrimaryButtonText(primaryButtonText)
        setPrimaryButtonAction(onPrimaryButtonAction)
        context?.hideKeyBoard(view)
    }

    private fun setPrimaryButtonText(buttonText: String?) {
        buttonText?.let { text->
            binding.primaryButton.visibility = VISIBLE
            binding.primaryButton.text = text
        }
    }

    private fun setPrimaryButtonAction(onPrimaryButtonAction: (() -> Unit)?) {
        binding.primaryButton.setOnClickListener {
            activity?.supportFragmentManager?.popBackStack()
            onPrimaryButtonAction?.invoke()
        }
    }

    private fun setDescription(description: String?) {
        binding.description.text = description
    }

    private fun setTitle(title: String?) {
        binding.title.text = title
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        val callback: OnBackPressedCallback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() { /*Keep in this Fragment*/ }
        }
        activity?.onBackPressedDispatcher?.addCallback(this,callback)
    }
}