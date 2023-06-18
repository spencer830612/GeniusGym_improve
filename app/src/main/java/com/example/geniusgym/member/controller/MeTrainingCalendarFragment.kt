package com.example.geniusgym.member.controller

import android.app.DatePickerDialog
import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.geniusgym.R
import com.example.geniusgym.databinding.FragmentMeTrainingCalendarBinding
import com.example.geniusgym.member.adapter.TrainingItemAdapter
import com.example.geniusgym.member.viewmodel.MeTrainingCalendarViewModel
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.temporal.TemporalAdjusters
import java.util.*

class MeTrainingCalendarFragment : Fragment(), View.OnClickListener {
    private lateinit var binding: FragmentMeTrainingCalendarBinding
    private lateinit var trainingWeekList: MutableList<trainingWeekDay>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val viewModel: MeTrainingCalendarViewModel by viewModels()
        binding = FragmentMeTrainingCalendarBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val trainingDate = LocalDate.now()
        val firstTrainingDayOfWeek =
            trainingDate.with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY))

        with(binding) {
            viewModel?.trainingTextDate?.value =
                LocalDate.now().format(DateTimeFormatter.ISO_LOCAL_DATE).toString()
            trainingWeekList = mutableListOf(
                trainingWeekDay(tvMeTrainingDay1, firstTrainingDayOfWeek),
                trainingWeekDay(tvMeTrainingDay2, firstTrainingDayOfWeek.plusDays(1)),
                trainingWeekDay(tvMeTrainingDay3, firstTrainingDayOfWeek.plusDays(2)),
                trainingWeekDay(tvMeTrainingDay4, firstTrainingDayOfWeek.plusDays(3)),
                trainingWeekDay(tvMeTrainingDay5, firstTrainingDayOfWeek.plusDays(4)),
                trainingWeekDay(tvMeTrainingDay6, firstTrainingDayOfWeek.plusDays(5)),
                trainingWeekDay(tvMeTrainingDay7, firstTrainingDayOfWeek.plusDays(6))
            )

            ivTrainingCalendar.setOnClickListener(this@MeTrainingCalendarFragment)
            for (textview in trainingWeekList) {
                textview.textview.setOnClickListener(this@MeTrainingCalendarFragment)
            }

            val trainingDayOfWeek = trainingDate.dayOfWeek.value
            trainingSelectDay(trainingDayOfWeek)

            rvTrainingList.layoutManager = LinearLayoutManager(requireContext())
            viewModel?.trainingItems?.observe(viewLifecycleOwner) { trainingItems ->
                if (rvTrainingList.adapter == null) {
                    rvTrainingList.adapter = TrainingItemAdapter(trainingItems)
                } else {
                    (rvTrainingList.adapter as TrainingItemAdapter).updateItem(trainingItems)
                }
            }
        }
    }

    private fun trainingPad(number: Int): String {
        return if (number >= 10) {
            number.toString()
        } else {
            "0$number"
        }
    }

    override fun onClick(v: View?) {
        with(binding) {
            when (v?.id) {
                R.id.ivTrainingCalendar -> {
                    val trainingCalendar = Calendar.getInstance()
                    val trainDatePickerDialog = DatePickerDialog(
                        requireContext(),
                        { _, year, month, day ->
                            viewModel?.trainingTextDate?.value =
                                "$year-${trainingPad(month + 1)}-${trainingPad(day)}"
                            viewModel?.trainingDate?.value =
                                LocalDate.parse("$year-${trainingPad(month + 1)}-${trainingPad(day)}")
                            val trainingFirstDayOfWeek = viewModel?.trainingDate?.value?.with(
                                TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY)
                            )
                            trainingFirstDayOfWeek?.let {
                                for (a in 0..6) {
                                    trainingWeekList[a].date = it.plusDays(a.toLong())
                                }
                            }
                            val trainingDayOfWeek = viewModel?.trainingDate?.value?.dayOfWeek?.value
                            if (trainingDayOfWeek != null) {
                                trainingSelectDay(trainingDayOfWeek)
                            }
                        },
                        trainingCalendar.get(Calendar.YEAR),
                        trainingCalendar.get(Calendar.MONTH),
                        trainingCalendar.get(Calendar.DAY_OF_MONTH)
                    )

                    trainDatePickerDialog.show()
                    println("Whoa")
                }
                R.id.tvMeTrainingDay1 -> trainingSelectDay(1)
                R.id.tvMeTrainingDay2 -> trainingSelectDay(2)
                R.id.tvMeTrainingDay3 -> trainingSelectDay(3)
                R.id.tvMeTrainingDay4 -> trainingSelectDay(4)
                R.id.tvMeTrainingDay5 -> trainingSelectDay(5)
                R.id.tvMeTrainingDay6 -> trainingSelectDay(6)
                R.id.tvMeTrainingDay7 -> trainingSelectDay(7)
                else -> {}
            }
            viewModel?.trainingItems?.observe(viewLifecycleOwner) { trainingItems ->
                if (rvTrainingList.adapter == null) {
                    rvTrainingList.adapter = TrainingItemAdapter(trainingItems)
                } else {
                    (rvTrainingList.adapter as TrainingItemAdapter).updateItem(trainingItems)
                }
            }
        }
    }

    private fun trainingSelectDay(index: Int) {
        for (day in trainingWeekList) {
            day.textview.setBackgroundColor(Color.parseColor("#1C1B1F"))
        }
        trainingWeekList[index - 1].textview.setBackgroundResource(R.color.teal_700)
        binding.viewModel?.trainingTextDate?.value = trainingWeekList[index - 1].date.toString()
        binding.viewModel?.trainingSearch(binding.viewModel?.trainingTextDate?.value)
    }

    private class trainingWeekDay(var textview: TextView, var date: LocalDate)
}