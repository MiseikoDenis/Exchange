package com.example.exchange.presentation.currencies

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.exchange.R
import com.example.exchange.databinding.FragmentCurrenciesBinding

class CurrenciesFragment : Fragment(R.layout.fragment_currencies) {

    private var _binding: FragmentCurrenciesBinding? = null
    private val binding
        get() = requireNotNull(_binding) {
            "View was destroyed"
        }

    private val viewModel: CurrenciesViewModel by viewModels()

    val adapter by lazy { CurrencyAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return FragmentCurrenciesBinding.inflate(inflater, container, false)
            .also { binding ->
                _binding = binding
            }
            .root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.currencyList.adapter = adapter

        viewModel.currenciesList.observe(viewLifecycleOwner) { list ->
            adapter.submitItem(list)
        }

    }
}