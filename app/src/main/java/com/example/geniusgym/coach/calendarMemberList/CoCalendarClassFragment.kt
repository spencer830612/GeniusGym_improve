package com.example.geniusgym.coach.calendarMemberList

import android.app.DatePickerDialog
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.geniusgym.R
import com.example.geniusgym.coach.calendarMemberList.controller.ClassItemAdapter
import com.example.geniusgym.coach.calendarMemberList.viewmodel.CoCalendarClassViewModel
import com.example.geniusgym.databinding.FragmentCoCalendarClassBinding
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.temporal.TemporalAdjusters
import java.util.*

class CoCalendarClassFragment : Fragment(), View.OnClickListener {
    private lateinit var binding: FragmentCoCalendarClassBinding
    private lateinit var weekList: MutableList<weekDay>
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {

        val viewModel: CoCalendarClassViewModel by viewModels()

        binding = FragmentCoCalendarClassBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val date = LocalDate.now()
        val firstDayOrWeek = date.with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY))


        with(binding) {
            viewModel?.textDate?.value =
                date.format(DateTimeFormatter.ISO_LOCAL_DATE).toString()
            weekList = mutableListOf(
                weekDay(tvCoCaDayOf1t, firstDayOrWeek),
                weekDay(tvCoCaDayOf2t, firstDayOrWeek.plusDays(1)),
                weekDay(tvCoCaDayOf3t, firstDayOrWeek.plusDays(2)),
                weekDay(tvCoCaDayOf4t, firstDayOrWeek.plusDays(3)),
                weekDay(tvCoCaDayOf5t, firstDayOrWeek.plusDays(4)),
                weekDay(tvCoCaDayOf6t, firstDayOrWeek.plusDays(5)),
                weekDay(tvCoCaDayOf7t, firstDayOrWeek.plusDays(6))
            )
            tvDate.setOnClickListener(this@CoCalendarClassFragment)
            for (textview in weekList) {
                textview.textview.setOnClickListener(this@CoCalendarClassFragment)
            }
            val dayOfWeek = date.dayOfWeek.value
            selectDay(dayOfWeek)

            rvClassListt.layoutManager = LinearLayoutManager(requireContext())
            // 當發現到 items 的值有變動時，進行下面的動作:
            viewModel?.items?.observe(viewLifecycleOwner) { items ->
                if (rvClassListt.adapter == null) {
                    rvClassListt.adapter = ClassItemAdapter(items)
                    // 詳情請看我的 adapter 裡怎麼寫
                } else {
                    (rvClassListt.adapter as ClassItemAdapter).updateItem(items)
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
                R.id.tvDate -> {
                    val calendar = Calendar.getInstance()
                    val datePickerDialog = DatePickerDialog(
                        requireContext(),
                        { _, year, month, day ->
                            viewModel?.textDate?.value = "$year-${pad(month + 1)}-${pad(day)}"
                            viewModel?.Date?.value =
                                LocalDate.parse(viewModel?.textDate?.value)
                            val firstDayOfWeek = viewModel?.Date?.value?.with(
                                TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY)
                            )
                            firstDayOfWeek?.let {
                                for (i in 0..6) {
                                    weekList[i].date = it.plusDays(i.toLong())
                                }
                            }
                            val dayOfWeek = viewModel?.Date?.value?.dayOfWeek?.value
                            if (dayOfWeek != null) {
                                selectDay(dayOfWeek)
                            }
                        },
                        calendar.get(Calendar.YEAR),
                        calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DAY_OF_MONTH)
                    )

                    datePickerDialog.show()
                    println("Haha")
                }
                R.id.tvCoCaDayOf1t -> selectDay(1)
                R.id.tvCoCaDayOf2t -> selectDay(2)
                R.id.tvCoCaDayOf3t -> selectDay(3)
                R.id.tvCoCaDayOf4t -> selectDay(4)
                R.id.tvCoCaDayOf5t -> selectDay(5)
                R.id.tvCoCaDayOf6t -> selectDay(6)
                R.id.tvCoCaDayOf7t -> selectDay(7)
                else -> {}
            }
            viewModel?.items?.observe(viewLifecycleOwner) { items ->
                if (rvClassListt.adapter == null) {
                    rvClassListt.adapter = ClassItemAdapter(items)
                } else {
                    (rvClassListt.adapter as ClassItemAdapter).updateItem(items)
                }
            }
        }
    }

    private fun selectDay(index: Int) {
        for (day in weekList) {
            day.textview.setBackgroundColor(Color.parseColor("#1C1B1F"))
        }
        weekList[index - 1].textview.setBackgroundResource(R.color.teal_700)
        binding.viewModel?.textDate?.value = weekList[index - 1].date.toString()
        binding.viewModel?.search(binding.viewModel?.textDate?.value)
    }

    private class weekDay(var textview: TextView, var date: LocalDate)
}