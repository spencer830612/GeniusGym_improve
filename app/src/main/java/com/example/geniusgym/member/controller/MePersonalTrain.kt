package com.example.geniusgym.member.controller

import android.app.DatePickerDialog
import android.graphics.Color
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.geniusgym.R
import com.example.geniusgym.databinding.FragmentMePersonalTrainBinding
import com.example.geniusgym.member.MePersonalTrainViewModel
import com.example.geniusgym.member.adapter.MeTrainShowAdapter
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.temporal.TemporalAdjusters
import java.util.*

class MePersonalTrain : Fragment(), View.OnClickListener {
    private lateinit var binding: FragmentMePersonalTrainBinding
    private lateinit var classlist: MutableList<weeklyDay>
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {

        val viewModel: MePersonalTrainViewModel by viewModels()

        binding = FragmentMePersonalTrainBinding.inflate(inflater, container, false)
        binding.viewmodel = viewModel
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val date = LocalDate.now()
        val WeekDayOne = date.with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY))

        with(binding) {
            viewmodel?.classname?.value =
                LocalDate.now().format(DateTimeFormatter.ISO_LOCAL_DATE).toString()
            classlist = mutableListOf(
                weeklyDay(tvPerTrainDay1, WeekDayOne),
                weeklyDay(tvPerTrainDay2, WeekDayOne.plusDays(1)),
                weeklyDay(tvPerTrainDay3, WeekDayOne.plusDays(2)),
                weeklyDay(tvPerTrainDay4, WeekDayOne.plusDays(3)),
                weeklyDay(tvPerTrainDay5, WeekDayOne.plusDays(4)),
                weeklyDay(tvPerTrainDay6, WeekDayOne.plusDays(5)),
                weeklyDay(tvPerTrainDay7, WeekDayOne.plusDays(6))
            )
            tvPersonalTrainDate.setOnClickListener(this@MePersonalTrain)
            for (textview in classlist) {
                textview.textview.setOnClickListener(this@MePersonalTrain)
            }
            val dayOfWeek = date.dayOfWeek.value
            selectDays(dayOfWeek)

            rvPersonalTrain.layoutManager = LinearLayoutManager(requireContext())
            viewmodel?.metrainitem?.observe(viewLifecycleOwner){ items ->
                if (rvPersonalTrain.adapter == null){
                    rvPersonalTrain.adapter = MeTrainShowAdapter(items)
                }else {
                    (rvPersonalTrain.adapter as MeTrainShowAdapter).updateItem(items)
                }
            }
        }
    }

    private fun pad(number: Int): String {
        return if (number >= 10) {
            number.toString()
        } else {
            "0$number"
        }
    }

    override fun onClick(v: View?) {
        with(binding) {
            when (v?.id) {
                R.id.tvPersonalTrainDate -> {
                    val calendar = Calendar.getInstance()
                    val datePickerDialog = DatePickerDialog(
                        requireContext(),
                        { _, year, month, day ->
                            viewmodel?.classname?.value = "$year-${pad(month + 1)}-${pad(day)}"
                            viewmodel?.classtime?.value =
                                LocalDate.parse("$year-${pad(month + 1)}-${pad(day)}")
                            val firstDayOfWeek = viewmodel?.classtime?.value?.with(
                                TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY)
                            )
                            firstDayOfWeek?.let {
                                for (i in 0..6) {
                                    classlist[i].date = it.plusDays(i.toLong())
                                }
                            }
                            val dayOfWeek = viewmodel?.classtime?.value?.dayOfWeek?.value
                            if (dayOfWeek != null) {
                                selectDays(dayOfWeek)
                            }
                        },
                        calendar.get(Calendar.YEAR),
                        calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DAY_OF_MONTH)
                    )
                    datePickerDialog.show()
                }
                R.id.tvPerTrainDay1 -> selectDays(1)
                R.id.tvPerTrainDay2 -> selectDays(2)
                R.id.tvPerTrainDay3 -> selectDays(3)
                R.id.tvPerTrainDay4 -> selectDays(4)
                R.id.tvPerTrainDay5 -> selectDays(5)
                R.id.tvPerTrainDay6 -> selectDays(6)
                R.id.tvPerTrainDay7 -> selectDays(7)
            }
            viewmodel?.metrainitem?.observe(viewLifecycleOwner) { items ->
                if (rvPersonalTrain.adapter == null) {
                    rvPersonalTrain.adapter = MeTrainShowAdapter(items)
                } else {
                    (rvPersonalTrain.adapter as MeTrainShowAdapter).updateItem(items)
                }
            }
        }
    }


    private fun selectDays(index: Int) {
        for (day in classlist) {
            day.textview.setBackgroundColor(Color.parseColor("#1C1B1F"))
        }
        classlist[index - 1].textview.setBackgroundResource(R.color.teal_700)
        binding.viewmodel?.classname?.value = classlist[index - 1].date.toString()
        binding.viewmodel?.search(binding.viewmodel?.classname?.value)
    }

    class weeklyDay(var textview: TextView, var date: LocalDate)


}

