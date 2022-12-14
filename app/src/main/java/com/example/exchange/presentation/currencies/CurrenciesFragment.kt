package com.example.exchange.presentation.currencies

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import com.example.exchange.R
import com.example.exchange.databinding.FragmentCurrenciesBinding
import com.example.exchange.presentation.appComponent
import javax.inject.Inject

class CurrenciesFragment : Fragment(R.layout.fragment_currencies) {

    private var _binding: FragmentCurrenciesBinding? = null
    private val binding
        get() = requireNotNull(_binding) {
            "View was destroyed"
        }

    @Inject
    lateinit var viewModel: CurrenciesViewModel

    private val adapter by lazy { CurrencyAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        activity?.appComponent?.inject(this)
        _binding = FragmentCurrenciesBinding.inflate(inflater, container, false)

        binding.currencyList.adapter = adapter
        binding.currencyList.addItemDecoration(DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL))

        viewModel.eventNetworkError.observe(viewLifecycleOwner) { isNetworkError ->
            if (isNetworkError) onNetworkError()
        }

        viewModel.currenciesList.observe(viewLifecycleOwner) { list ->
            adapter.submitItem(list)
        }

        return binding.root
    }

    private fun onNetworkError() {
        if(!viewModel.isNetworkErrorShown.value!!) {
            Toast.makeText(activity, "Network Error", Toast.LENGTH_LONG).show()
            viewModel.onNetworkErrorShown()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}