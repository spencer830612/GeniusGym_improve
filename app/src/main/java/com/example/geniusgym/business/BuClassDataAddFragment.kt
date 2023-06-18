package com.example.geniusgym.business

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.example.geniusgym.business.viewModel.BuClassDataAddViewModel
import com.example.geniusgym.R
import com.example.geniusgym.databinding.FragmentBuClassDataAddBinding
import com.example.geniusgym.sharedata.MeShareData
import com.google.gson.JsonObject
import tw.idv.william.androidwebserver.core.service.requestTask
import java.sql.Timestamp
import java.text.SimpleDateFormat
import java.util.*

class BuClassDataAddFragment : Fragment() {
    private lateinit var binding: FragmentBuClassDataAddBinding
    private val calendar = Calendar.getInstance()
    val url = MeShareData.javaWebUrl + "buClass"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentBuClassDataAddBinding.inflate(inflater, container, false)
        val viewModel: BuClassDataAddViewModel by viewModels()
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        with(binding){

            tvBuAddClassDataSportCat.setText(viewModel?.scToString())

            tvBuAddClassDataBranch.setText(viewModel?.bhToString())
//            val ci_cost = viewModel?.classs?.value?.ci_cost
//            if (ci_cost != null) {
//                tietBuAddClassDataCost.setText(ci_cost).toString()
//            }
//
//
//            val ci_limit = viewModel?.classs?.value?.ci_limit
//            if (ci_limit != null) {
//                tietBuAddClassDataCost.setText(ci_limit).toString()
//            }

            tvBuAddClassDataSportCat.setOnClickListener {
                tvBuAddClassDataSportCat.showSoftInputOnFocus = false
                showSportCatSelection()
            }

            tvBuAddClassDataStartTime.setOnClickListener {
                tvBuAddClassDataStartTime.showSoftInputOnFocus = false
                openStartDateTimeDialogs()
                tvBuAddClassDataStartTime.text = viewModel?.startTimeToString()
            }

            tvBuAddClassDataEndTime.setOnClickListener {
                tvBuAddClassDataEndTime.showSoftInputOnFocus = false
                openEnDateTimeDialogs()
                tvBuAddClassDataEndTime.text = viewModel?.endTimeToString()
            }

            tvBuAddClassDataBranch.setOnClickListener {
                tvBuAddClassDataBranch.showSoftInputOnFocus = false
                showBranchSelection()
            }

            tvBuAddClassDataRegiStartTime.setOnClickListener {
                tvBuAddClassDataRegiStartTime.showSoftInputOnFocus = false
                openRegiStartDateTimeDialogs()
                tvBuAddClassDataRegiStartTime.text = viewModel?.regiTimeToString()
            }

            tvBuAddClassDataRegiEndTime.setOnClickListener {
                tvBuAddClassDataRegiEndTime.showSoftInputOnFocus = false
                openRegiEdDateTimeDialogs()
                tvBuAddClassDataRegiEndTime.text = viewModel?.regiEdTimeToString()
            }


            btBuAddClassDataSave.setOnClickListener {
                viewModel?.classs?.value?.run {
                    val bh_name = tvBuAddClassDataBranch.text.toString().trim()
                    val bh_id: Int = when (bh_name) {
                        "緯育店" -> 1
                        "台北店" -> 2
                        "桃園店" -> 3
                        "新竹店" -> 4
                        "南京復興店" -> 5
                        else ->
                            return@setOnClickListener
                    }
                    viewModel?.classs?.value?.bh_id = bh_id

                    val sc_name = tvBuAddClassDataSportCat.text.toString()
                    val sc_id: Int = when(sc_name){
                        "靜態" -> 2
                        "心肺訓練" -> 3
                        "跑步" -> 4
                        "槓鈴肩推" -> 5
                        "啞鈴肩推" -> 6
                        "啞鈴側平舉" -> 7
                        "啞鈴前平舉" -> 8
                        "站姿肩推" -> 9
                        "啞鈴握推" -> 10
                        "槓鈴握推"-> 11
                        "蝴蝶機夾胸" -> 12
                        "繩索下斜夾胸"-> 13
                        "槓鈴斜上推" -> 14
                        "飛輪" ->15
                        else ->
                            return@setOnClickListener
                    }
                    viewModel?.classs?.value?.sc_id = sc_id



//                    viewModel?.classs?.value?.ci_cost = tietBuAddClassDataCost.toInt()
//                    val string_limit = tietBuAddClassDataLimit.text.toString()
//                    val int_limit = string_limit.toInt()
//                    viewModel?.classs?.value?.ci_limit = int_limit

                    val ci_start_time = tvBuAddClassDataStartTime.text.toString().trim()
                    val timestamp1 = Timestamp.valueOf(ci_start_time)
                    viewModel?.classs?.value?.ci_start_time = timestamp1

                    val ci_ed_time =  tvBuAddClassDataEndTime.text.toString().trim()
                    val timestamp2 = Timestamp.valueOf(ci_ed_time)
                    viewModel?.classs?.value?.ci_ed_time = timestamp2

                    val ci_regi_start_time =  tvBuAddClassDataEndTime.text.toString().trim()
                    val timestamp3 = Timestamp.valueOf(ci_regi_start_time)
                    viewModel?.classs?.value?.ci_regi_time = timestamp3

                    val ci_regi_ed_time =  tvBuAddClassDataEndTime.text.toString().trim()
                    val timestamp4 = Timestamp.valueOf(ci_regi_ed_time)
                    viewModel?.classs?.value?.ci_regi_ed_time = timestamp4


                    requestTask<JsonObject>(url, "POST", viewModel?.classs?.value)
                    println(viewModel?.classs?.value)
                }
            }

            btBuAddClassDataCancel.setOnClickListener {
                Navigation.findNavController(it).popBackStack()
            }
        }


    }



    private fun openStartDateTimeDialogs() {
        val dateListener = DatePickerDialog.OnDateSetListener { _, year, month, day ->
            calendar.set(Calendar.YEAR, year)
            calendar.set(Calendar.MONTH, month)
            calendar.set(Calendar.DAY_OF_MONTH, day)
            openStartTimePickerDialog()
        }

        val datePickerDialog = DatePickerDialog(
            requireContext(),
            dateListener,
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        )
        datePickerDialog.show()
    }
    private fun openStartTimePickerDialog() {
        val timeListener = TimePickerDialog.OnTimeSetListener { _, hour, minute ->
            calendar.set(Calendar.HOUR_OF_DAY, hour)
            calendar.set(Calendar.MINUTE, minute)
            updatetvBuAddClassDataStartTime()

        }
        val timePickerDialog = TimePickerDialog(
            requireContext(),
            timeListener,
            calendar.get(Calendar.HOUR_OF_DAY),
            calendar.get(Calendar.MINUTE),
            true
        )
        timePickerDialog.show()
    }
    private fun updatetvBuAddClassDataStartTime() {
        val format = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
        val datetime = format.format(calendar.time)
        binding.tvBuAddClassDataStartTime.text = datetime
    }

    private fun openEnDateTimeDialogs() {
        val dateListener = DatePickerDialog.OnDateSetListener { _, year, month, day ->
            calendar.set(Calendar.YEAR, year)
            calendar.set(Calendar.MONTH, month)
            calendar.set(Calendar.DAY_OF_MONTH, day)
            openEdTimePickerDialog()
        }

        val datePickerDialog = DatePickerDialog(
            requireContext(),
            dateListener,
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        )
        datePickerDialog.show()
    }
    private fun openEdTimePickerDialog() {
        val timeListener = TimePickerDialog.OnTimeSetListener { _, hour, minute ->
            calendar.set(Calendar.HOUR_OF_DAY, hour)
            calendar.set(Calendar.MINUTE, minute)
            updatetvBuAddClassDataEdTime()

        }
        val timePickerDialog = TimePickerDialog(
            requireContext(),
            timeListener,
            calendar.get(Calendar.HOUR_OF_DAY),
            calendar.get(Calendar.MINUTE),
            true
        )
        timePickerDialog.show()
    }
    private fun updatetvBuAddClassDataEdTime() {
        val format = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
        val datetime = format.format(calendar.time)
        binding.tvBuAddClassDataEndTime.text = datetime
    }


    private fun openRegiStartDateTimeDialogs() {
        val dateListener = DatePickerDialog.OnDateSetListener { _, year, month, day ->
            calendar.set(Calendar.YEAR, year)
            calendar.set(Calendar.MONTH, month)
            calendar.set(Calendar.DAY_OF_MONTH, day)
            openRegiStartTimePickerDialog()
        }

        val datePickerDialog = DatePickerDialog(
            requireContext(),
            dateListener,
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        )
        datePickerDialog.show()
    }
    private fun openRegiStartTimePickerDialog() {
        val timeListener = TimePickerDialog.OnTimeSetListener { _, hour, minute ->
            calendar.set(Calendar.HOUR_OF_DAY, hour)
            calendar.set(Calendar.MINUTE, minute)
            updatetvBuAddClassDataRegiStartTime()

        }
        val timePickerDialog = TimePickerDialog(
            requireContext(),
            timeListener,
            calendar.get(Calendar.HOUR_OF_DAY),
            calendar.get(Calendar.MINUTE),
            true
        )
        timePickerDialog.show()
    }
    private fun updatetvBuAddClassDataRegiStartTime() {
        val format = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
        val datetime = format.format(calendar.time)
        binding.tvBuAddClassDataRegiStartTime.text = datetime
    }


    private fun openRegiEdDateTimeDialogs() {
        val dateListener = DatePickerDialog.OnDateSetListener { _, year, month, day ->
            calendar.set(Calendar.YEAR, year)
            calendar.set(Calendar.MONTH, month)
            calendar.set(Calendar.DAY_OF_MONTH, day)
            openRegiEdTimePickerDialog()
        }

        val datePickerDialog = DatePickerDialog(
            requireContext(),
            dateListener,
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        )
        datePickerDialog.show()
    }
    private fun openRegiEdTimePickerDialog() {
        val timeListener = TimePickerDialog.OnTimeSetListener { _, hour, minute ->
            calendar.set(Calendar.HOUR_OF_DAY, hour)
            calendar.set(Calendar.MINUTE, minute)
            updatetvBuAddClassDataRegiEndTime()

        }
        val timePickerDialog = TimePickerDialog(
            requireContext(),
            timeListener,
            calendar.get(Calendar.HOUR_OF_DAY),
            calendar.get(Calendar.MINUTE),
            true
        )
        timePickerDialog.show()
    }
    private fun updatetvBuAddClassDataRegiEndTime() {
        val format = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
        val datetime = format.format(calendar.time)
        binding.tvBuAddClassDataRegiEndTime.text = datetime
    }


    private fun showSportCatSelection(){
        var choice = arrayOf("靜態","心肺訓練","跑步","槓鈴肩推","啞鈴肩推","啞鈴側平舉","啞鈴前平舉","站姿肩推"
                        ,"啞鈴握推","槓鈴握推","蝴蝶機夾胸","繩索下斜夾胸","槓鈴斜上推","飛輪")
        var selectItem = -1


        AlertDialog.Builder(view?.context)
            // 設定標題文字
            .setTitle(R.string.spBuAddChooseBranch)
            .setSingleChoiceItems(choice,selectItem){ _, position->
                selectItem = position
            }
            .setPositiveButton(R.string.bu_add_choose_branch_confirm){ _, _ ->
                if (selectItem != -1) {
                    val selectedBranch = choice[selectItem]
                    binding.tvBuAddClassDataSportCat.text = selectedBranch
                }
            }
            // false代表要點擊按鈕方能關閉，預設為true
            .setCancelable(true)
            .show()
    }

    private fun showBranchSelection(){
        var choice = arrayOf("緯育店","台北店","桃園店","新竹店","南京復興店")
        var selectItem = -1


        AlertDialog.Builder(view?.context)
            // 設定標題文字
            .setTitle(R.string.spBuAddChooseBranch)
            .setSingleChoiceItems(choice,selectItem){ _, position->
                selectItem = position
            }
            .setPositiveButton(R.string.bu_add_choose_branch_confirm){ _, _ ->
                if (selectItem != -1) {
                    val selectedBranch = choice[selectItem]
                    binding.tvBuAddClassDataBranch.text = selectedBranch
                }
            }
            // false代表要點擊按鈕方能關閉，預設為true
            .setCancelable(true)
            .show()
    }



}