package com.example.geniusgym.coach.calendarMemberListDetail.controller

import android.app.DatePickerDialog
import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.geniusgym.R
import com.example.geniusgym.coach.CoActivity
import com.example.geniusgym.coach.calendarMemberListDetail.model.SportRecordBigItem
import com.example.geniusgym.coach.calendarMemberListDetail.viewmodel.CoCalenderMemberStaticViewModel
import com.example.geniusgym.databinding.FragmentCoCalenderMemberStaticBinding
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.net.ConnectException
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.temporal.TemporalAdjusters
import java.util.*

class CoCalenderMemberStaticFragment : Fragment(), View.OnClickListener {
    private lateinit var binding: FragmentCoCalenderMemberStaticBinding
    private lateinit var weekList: MutableList<weekDay>
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val viewModel: CoCalenderMemberStaticViewModel by viewModels()
        binding = FragmentCoCalenderMemberStaticBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        val coActivity = requireActivity() as CoActivity
        with(binding) {
            try {
                viewModel?.loadStatistic()
                loadPreferences()
            }catch (e: ConnectException){
                e.printStackTrace()
                Toast.makeText(requireContext(),"連線失敗",Toast.LENGTH_SHORT)
            }

        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val date = LocalDate.now()
        val firstDayOrWeek = date.with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY))
        val coActivity = requireActivity() as CoActivity

        with(binding) {
            val member = coActivity.binding.viewModel?.member?.value
            viewModel?.member?.value = member
            viewModel?.textDate?.value =
                LocalDate.now().format(DateTimeFormatter.ISO_LOCAL_DATE).toString()
            weekList = mutableListOf(
                weekDay(tvCoCaMeStExerciseDayOf1, firstDayOrWeek),
                weekDay(tvCoCaMeStExerciseDayOf2, firstDayOrWeek.plusDays(1)),
                weekDay(tvCoCaMeStExerciseDayOf3, firstDayOrWeek.plusDays(2)),
                weekDay(tvCoCaMeStExerciseDayOf4, firstDayOrWeek.plusDays(3)),
                weekDay(tvCoCaMeStExerciseDayOf5, firstDayOrWeek.plusDays(4)),
                weekDay(tvCoCaMeStExerciseDayOf6, firstDayOrWeek.plusDays(5)),
                weekDay(tvCoCaMeStExerciseDayOf7, firstDayOrWeek.plusDays(6))
            )
            tvCoCaMeStecerciseRecordDate.setOnClickListener(this@CoCalenderMemberStaticFragment)
            for (day in weekList) {
                day.textview.setOnClickListener(this@CoCalenderMemberStaticFragment)
            }

            val dayOfWeek = date.dayOfWeek.value
            selectDay(dayOfWeek)
            val coActivity = requireActivity() as CoActivity
            rvCoCaMeSportStatistic.layoutManager = LinearLayoutManager(requireContext())
            viewModel?.sportRecordBigItems?.observe(viewLifecycleOwner) { items ->
                if (rvCoCaMeSportStatistic.adapter == null) {
                    rvCoCaMeSportStatistic.adapter = StatisticAdapter(items, coActivity)
                    println("rvCoCaMeSportStatistic.adapter = StatisticAdapter(items)")
                } else {
                    (rvCoCaMeSportStatistic.adapter as StatisticAdapter).updateItem(items)
                }
            }
        }
    }

    private class weekDay(var textview: TextView, var date: LocalDate)

    private fun selectDay(index: Int) {
        for (day in weekList) {
            day.textview.setBackgroundColor(Color.parseColor("#1C1B1F"))
        }
        weekList[index - 1].textview.setBackgroundResource(R.color.teal_700)
        binding.viewModel?.textDate?.value = weekList[index - 1].date.toString()
        binding.viewModel?.search(
            binding.viewModel?.member?.value?.memberId,
            binding.viewModel?.textDate?.value
        )
        println("Haha")
    }

    override fun onClick(v: View?) {
        with(binding) {
            when (v?.id) {
                R.id.tvCoCaMeStecerciseRecordDate -> {
                    val calendar = Calendar.getInstance()
                    val datePickerDialog = DatePickerDialog(
                        requireContext(),
                        { _, year, month, day ->
                            viewModel?.textDate?.value = "$year-${pad(month + 1)}-${pad(day)}"
                            viewModel?.date?.value =
                                LocalDate.parse("$year-${pad(month + 1)}-${pad(day)}")
                            val firstDayOfWeek = viewModel?.date?.value?.with(
                                TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY)
                            )
                            firstDayOfWeek?.let {
                                for (i in 0..6) {
                                    weekList[i].date = it.plusDays(i.toLong())
                                }
                            }
                            val dayOfWeek = viewModel?.date?.value?.dayOfWeek?.value
                            if (dayOfWeek != null) {
                                selectDay(dayOfWeek)
                            }
                        },
                        calendar.get(Calendar.YEAR),
                        calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DAY_OF_MONTH)
                    )
                    datePickerDialog.show()
                }
                R.id.tvCoCaMeStExerciseDayOf1 -> selectDay(1)
                R.id.tvCoCaMeStExerciseDayOf2 -> selectDay(2)
                R.id.tvCoCaMeStExerciseDayOf3 -> selectDay(3)
                R.id.tvCoCaMeStExerciseDayOf4 -> selectDay(4)
                R.id.tvCoCaMeStExerciseDayOf5 -> selectDay(5)
                R.id.tvCoCaMeStExerciseDayOf6 -> selectDay(6)
                R.id.tvCoCaMeStExerciseDayOf7 -> selectDay(7)
                else -> {}
            }
            val coActivity = requireActivity() as CoActivity
            viewModel?.sportRecordBigItems?.observe(viewLifecycleOwner) { items ->
                if (rvCoCaMeSportStatistic.adapter == null) {
                    rvCoCaMeSportStatistic.adapter = StatisticAdapter(items, coActivity)
                } else {
                    (rvCoCaMeSportStatistic.adapter as StatisticAdapter).updateItem(items)
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

    private fun loadPreferences() {
        with(binding) {
            val gson = Gson()
            val preferences = requireActivity().getPreferences(Context.MODE_PRIVATE)
            val sportRecordItemsJson =
                preferences.getString(viewModel?.member?.value?.memberId, "")
            // 沒有儲存帳號，就無需顯示已經載入
            if (sportRecordItemsJson!!.isEmpty()) {
                return
            }
            val collectionType = object : TypeToken<MutableList<SportRecordBigItem>>() {}.type
            val sportRecordItems =
                (gson.fromJson<MutableList<SportRecordBigItem>>(
                    sportRecordItemsJson,
                    collectionType
                ))
            val coActivity = requireActivity() as CoActivity
            coActivity.memberSportRecord = sportRecordItems
            viewModel?.load(sportRecordItems)
            // viewModel?.sportRecordBigLists?.value = sportRecordItems
            binding.viewModel?.search(
                binding.viewModel?.member?.value?.memberId,
                binding.viewModel?.textDate?.value
            )
            println(viewModel?.sportRecordBigItems?.value)
        }
    }
}