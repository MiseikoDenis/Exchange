package com.example.exchange.presentation.start

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
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

        setEditObserver(binding.editByn.editText, viewModel.bynAmount)

        setCurrencyObserver(binding.spinnerFirst, binding.editFirst.editText, viewModel.firstCurrencyAmount)

        linkEditViews(binding.editFirst.editText, binding.editByn.editText)

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setCurrencyObserver(spinner: Spinner, editText: EditText?, amount: LiveData<Double>){
        setSpinnerObserver(spinner)
        setEditObserver(editText, amount)
    }

    private fun setSpinnerObserver(spinner: Spinner) {
        viewModel.currenciesList.observe(viewLifecycleOwner) { list ->
            setSpinnerAdapter(spinner, list)
        }
    }

    private fun setSpinnerAdapter(spinner: Spinner, list: List<Currency>) {
        val adapter = CustomAdapter(requireContext(), list)
        spinner.adapter = adapter
        spinner.setSelection(0)
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                itemSelected: View?, selectedItemPosition: Int, selectedId: Long
            ) {
                viewModel.updateRate(spinner.selectedItem as Currency)
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {}
        }
    }

    private fun setEditObserver(editText: EditText?, amount: LiveData<Double>) {
        amount.observe(viewLifecycleOwner) {
            editText?.setText(it.toString())
        }
    }

    private fun linkEditViews(editTextMain: EditText?, editTextSecondary: EditText?) {
        editTextMain?.doOnTextChanged { text, _, _, _ ->
            editTextSecondary?.setText(text)
        }
    }

}

class CustomAdapter(val context: Context, var dataSource: List<Currency>) : BaseAdapter() {

    private val inflater: LayoutInflater =
        context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

    override fun getCount(): Int {
        return dataSource.size
    }

    override fun getItem(p0: Int): Any {
        return dataSource[p0]
    }

    override fun getItemId(p0: Int): Long {
        return p0.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {

        val view: View
        val vh: ItemHolder
        if (convertView == null) {
            view = inflater.inflate(R.layout.custom_item_spinner, parent, false)
            vh = ItemHolder(view)
            view?.tag = vh
        } else {
            view = convertView
            vh = view.tag as ItemHolder
        }
        vh.label.text = dataSource.get(position).abbreviation

        return view
    }

    private class ItemHolder(row: View?) {
        val label: TextView

        init {
            label = row?.findViewById(R.id.text) as TextView
        }
    }

}