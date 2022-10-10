package com.example.exchange.presentation.start

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.EditText
import android.widget.Spinner
import androidx.core.widget.doBeforeTextChanged
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.exchange.R
import com.example.exchange.databinding.FragmentStartBinding
import com.example.exchange.models.Currency
import com.example.exchange.util.spinner.CustomSpinnerAdapter


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

        setEditTextObserver(binding.textByn.editText)
        setCurrencyObserver(binding.spinnerFirst, binding.textFirst.editText)
        setCurrencyObserver(binding.spinnerSecond, binding.textSecond.editText)
        setCurrencyObserver(binding.spinnerThird, binding.textThird.editText)
        setCurrencyObserver(binding.spinnerFourth, binding.textFourth.editText)

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setCurrencyObserver(spinner: Spinner, editText: EditText?) {
        setSpinnerObserver(spinner, editText)
        setEditTextObserver(editText)
    }

    private fun setSpinnerObserver(spinner: Spinner, editText: EditText?) {
        viewModel.currenciesList.observe(viewLifecycleOwner) { list ->
            setSpinnerAdapter(spinner, list.sortedBy { it.abbreviation })
        }
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                itemSelected: View?, selectedItemPosition: Int, selectedId: Long
            ) {
                val item = spinner.selectedItem as Currency
                viewModel.updateEditText(editText, item.rate)
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {}
        }
    }

    private fun setEditTextObserver(editText: EditText?) {
        when (editText?.id) {
            R.id.edit_byn -> viewModel.bynAmount.observe(viewLifecycleOwner) {
                editText.setText(it.toString())
            }
            R.id.edit_first -> viewModel.firstCurrencyAmount.observe(viewLifecycleOwner) {
                editText.setText(it.toString())
            }
            R.id.edit_second -> viewModel.secondCurrencyAmount.observe(viewLifecycleOwner) {
                editText.setText(it.toString())
            }
            R.id.edit_third -> viewModel.thirdCurrencyAmount.observe(viewLifecycleOwner) {
                editText.setText(it.toString())
            }
            R.id.edit_fourth -> viewModel.fourthCurrencyAmount.observe(viewLifecycleOwner) {
                editText.setText(it.toString())
            }
        }
        setEditChangeListener(editText)
    }

    private fun setSpinnerAdapter(spinner: Spinner, list: List<Currency>) {
        val adapter = CustomSpinnerAdapter(requireContext(), list)
        spinner.adapter = adapter
        spinner.setSelection(0)
    }

    private fun setEditChangeListener(editText: EditText?) {
        editText?.doOnTextChanged { text, start, before, count ->
            try {
                viewModel.updateByn(text.toString().toDouble(), editText)
            } catch (exception: NumberFormatException){
                editText.setText("0")
            }
        }
    }
}