package com.example.exchange.presentation.start

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.exchange.databinding.FragmentStartBinding

class StartFragment : Fragment() {

    private var _binding: FragmentStartBinding? = null
    private val binding
        get() = requireNotNull(_binding) {
            "View was destroyed"
        }

    private val viewModel: StartViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //Подписался на изменения во viewmodel
        viewModel.currenciesList.observe(viewLifecycleOwner) { currency ->
            binding.test.text =
                currency.toString()
        }

        return FragmentStartBinding.inflate(inflater, container, false)
            .also { binding ->
                _binding = binding
            }
            .root
    }
}