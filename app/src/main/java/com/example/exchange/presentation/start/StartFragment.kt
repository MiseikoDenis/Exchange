package com.example.exchange.presentation.start

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.EditText
import android.widget.Spinner
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.exchange.R
import com.example.exchange.databinding.FragmentStartBinding
import com.example.exchange.models.Currency


class StartFragment : Fragment() {

    private var _binding: FragmentStartBinding? = null
    private val binding
        get() = requireNotNull(_binding) {
            "View was destroyed"
        }

    private val viewModel: StartViewModel by lazy {
        val activity = requireNotNull(this.activity) {
            "You can only access the viewModel after onActivityCreated()"
        }
        ViewModelProvider(this, StartViewModel.Factory(activity.application))
            .get(StartViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentStartBinding.inflate(inflater, container, false)

//        setEditObserver(binding.editByn.editText, 1.0)

        setSpinnerObserver(binding.spinnerFirst, binding.editFirst.editText)

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setSpinnerObserver(spinner: Spinner, editText: EditText?) {
        viewModel.currenciesList.observe(viewLifecycleOwner) { list ->
            setSpinnerAdapter(spinner, list)
        }
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                itemSelected: View?, selectedItemPosition: Int, selectedId: Long
            ) {
                val item = spinner.selectedItem as Currency
                setEditObserver(editText, item.rate)
//                setEditChangeListener(editText, item)
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {}
        }
    }

    private fun setSpinnerAdapter(spinner: Spinner, list: List<Currency>) {
        val adapter = CustomSpinnerAdapter(requireContext(), list)
        spinner.adapter = adapter
        spinner.setSelection(0)

    }

    private fun setEditObserver(editText: EditText?, rate: Double) {
        when(editText?.id){
            R.id.edit_first -> viewModel.firstCurrencyAmount.observe(viewLifecycleOwner){
                editText.setText(it.toString())
            }
        }
        viewModel.updateRate(editText, rate)
    }

    private fun setEditChangeListener(editText: EditText?, currency: Currency) {
        editText?.doOnTextChanged { text, _, _, _ ->
            viewModel.updateByn(text.toString().toDouble(), currency)
        }
    }
}