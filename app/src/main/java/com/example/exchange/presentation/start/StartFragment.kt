package com.example.exchange.presentation.start

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.EditText
import android.widget.Spinner
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import com.example.exchange.R
import com.example.exchange.databinding.FragmentStartBinding
import com.example.exchange.models.Currency
import com.example.exchange.presentation.appComponent
import com.example.exchange.util.Constants.Companion.BYN_FIELD
import com.example.exchange.util.Constants.Companion.EUR
import com.example.exchange.util.Constants.Companion.FIRST_FIELD
import com.example.exchange.util.Constants.Companion.FOURTH_FIELD
import com.example.exchange.util.Constants.Companion.PLN
import com.example.exchange.util.Constants.Companion.RUB
import com.example.exchange.util.Constants.Companion.SECOND_FIELD
import com.example.exchange.util.Constants.Companion.THIRD_FIELD
import com.example.exchange.util.Constants.Companion.USD
import com.example.exchange.util.preference.SharedPreferencesManager
import com.example.exchange.util.spinner.CustomSpinnerAdapter
import javax.inject.Inject


class StartFragment : Fragment() {

    private var _binding: FragmentStartBinding? = null
    private val binding
        get() = requireNotNull(_binding) {
            "View was destroyed"
        }

    @Inject
    lateinit var viewModel: StartViewModel

    @Inject
    lateinit var sharedPreferencesManager: SharedPreferencesManager

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        activity?.appComponent?.inject(this)
        _binding = FragmentStartBinding.inflate(inflater, container, false)

        setEditTextObserver(binding.textByn.editText)
        setCurrencyObserver(binding.spinnerFirst, binding.textFirst.editText, USD)
        setCurrencyObserver(binding.spinnerSecond, binding.textSecond.editText, EUR)
        setCurrencyObserver(binding.spinnerThird, binding.textThird.editText, PLN)
        setCurrencyObserver(binding.spinnerFourth, binding.textFourth.editText, RUB)

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    //Устанавливаем связь между спинером и полем с валютой
    private fun setCurrencyObserver(spinner: Spinner, editText: EditText?, selection: String) {
        setSpinnerObserver(spinner, editText, selection)
        setEditTextObserver(editText)
    }

    //Устанавливаем обсервер спинера
    private fun setSpinnerObserver(spinner: Spinner, editText: EditText?, selection: String) {
        viewModel.currenciesList.observe(viewLifecycleOwner) { list ->
            setSpinnerAdapter(spinner, list.sortedBy { it.abbreviation }, selection)
        }
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                itemSelected: View?, selectedItemPosition: Int, selectedId: Long
            ) {
                val item = spinner.selectedItem as Currency
                updateEditText(editText, item.rate, item.scale)
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {}
        }
    }

    //Устанавливаем адаптер для спинера
    private fun setSpinnerAdapter(spinner: Spinner, list: List<Currency>, selection: String) {
        val adapter = CustomSpinnerAdapter(requireContext(), list)
        spinner.adapter = adapter
        spinner.setSelection(list.indexOf(list.find { it.abbreviation == selection }))
    }

    //Получаем позицию поля для связи с вьюмоделью
    private fun getEditPosition(editText: EditText?): Int {
        return when (editText?.id) {
            R.id.edit_first -> FIRST_FIELD
            R.id.edit_second -> SECOND_FIELD
            R.id.edit_third -> THIRD_FIELD
            R.id.edit_fourth -> FOURTH_FIELD
            R.id.edit_byn -> BYN_FIELD
            else -> -1
        }
    }

    //Обновляем курс для определенного поля
    private fun updateEditText(editText: EditText?, rate: Double, scale: Int) {
        viewModel.updateRate(getEditPosition(editText), rate, scale)
    }

    //Подписываемся на изменения количества валюты в поле
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

    //Устанавливаем слушатель изменения текста в каждом поле
    private fun setEditChangeListener(editText: EditText?) {
        editText?.doAfterTextChanged { text ->
            if (editText.isFocused) {
                try {
                    val amount = text.toString()
                    if (amount.length == 2 && amount[0] == '0') {
                        editText.setText(amount.substring(1))
                    }
                    viewModel.updateOtherFields(amount.toDouble(), getEditPosition(editText))
                } catch (exception: NumberFormatException) {
                    editText.append("0")
                }
            }
        }
    }
}