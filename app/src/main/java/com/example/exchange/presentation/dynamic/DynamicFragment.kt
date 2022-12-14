package com.example.exchange.presentation.dynamic

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.Spinner
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import com.example.exchange.databinding.FragmentDynamicBinding
import com.example.exchange.models.Currency
import com.example.exchange.presentation.appComponent
import com.example.exchange.util.spinner.CustomSpinnerAdapter
import java.util.*
import javax.inject.Inject


class DynamicFragment : Fragment() {

    private var _binding: FragmentDynamicBinding? = null
    private val binding
        get() = requireNotNull(_binding) {
            "View was destroyed"
        }

    @Inject
    lateinit var viewModel: DynamicViewModel

    private val adapter by lazy { DynamicAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        activity?.appComponent?.inject(this)
        _binding = FragmentDynamicBinding.inflate(inflater, container, false)
        binding.dynamicList.adapter = adapter
        binding.dynamicList.addItemDecoration(DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL))

        viewModel.dynamicList.observe(viewLifecycleOwner) { list ->
            adapter.submitItem(list)
        }
        setSpinnerObserver(binding.spinnerCurrency)
        binding.button.setOnClickListener {
            viewModel.refreshDynamicList()
        }
        binding.editTextDate.setOnClickListener {
            buttonSelectDate()
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    @SuppressLint("NewApi", "SetTextI18n")
    private fun buttonSelectDate() {
        val dateListener = DatePickerDialog.OnDateSetListener { _, i, i2, i3 ->
            binding.editTextDate.setText("$i-${i2 + 1}-$i3")
            viewModel.refreshDates(binding.editTextDate.text.toString())
        }
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)
        val datePickerDialog = DatePickerDialog(requireContext(), dateListener, year, month, day)

        datePickerDialog.datePicker.maxDate = calendar.timeInMillis
        calendar.add(Calendar.YEAR, -1)
        datePickerDialog.datePicker.minDate = calendar.timeInMillis
        datePickerDialog.show()
    }

    private fun setSpinnerObserver(spinner: Spinner) {
        viewModel.currenciesList.observe(viewLifecycleOwner) { list ->
            setSpinnerAdapter(spinner, list.sortedBy { it.abbreviation })
        }
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            @SuppressLint("SetTextI18n")
            override fun onItemSelected(
                parent: AdapterView<*>?,
                itemSelected: View?, selectedItemPosition: Int, selectedId: Long
            ) {
                val item = spinner.selectedItem as Currency
                viewModel.refreshId(item.id)
                binding.dynamicQuoteName.text = "/${item.quoteName}"
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {}
        }
    }

    private fun setSpinnerAdapter(spinner: Spinner, list: List<Currency>) {
        val adapter = CustomSpinnerAdapter(requireContext(), list)
        spinner.adapter = adapter
        spinner.setSelection(0)
    }
}