package com.example.geniusgym.business

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.geniusgym.business.viewModel.BuClassViewModel
import com.example.geniusgym.R
import com.example.geniusgym.business.model.Business
import com.example.geniusgym.business.model.Class_Info
import com.example.geniusgym.business.viewModel.BuBusinessViewModel
import com.example.geniusgym.databinding.FragmentBuClassDataDetailBinding
import com.example.geniusgym.sharedata.MeShareData
import com.google.gson.JsonObject
import tw.idv.william.androidwebserver.core.service.requestTask
import java.sql.Timestamp
import java.text.SimpleDateFormat
import java.util.*

class BuClassDataDetailFragment : Fragment() {
    private lateinit var binding: FragmentBuClassDataDetailBinding
    private val calendar = Calendar.getInstance()
    //val url = "http://10.0.2.2:8080/geninusgym_bg/buClass"
    val url = MeShareData.javaWebUrl + "buClass"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentBuClassDataDetailBinding.inflate(inflater, container, false)
        val viewModel: BuClassViewModel by viewModels()
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        with(binding) {
            arguments?.let { bundle ->
                bundle.getSerializable("classs")?.let {
                    viewModel?.classs?.value = it as Class_Info
                    println(bundle)
                }
            }

            tvBuAddClassDataDetailSportCat.setText(viewModel?.scToString())

            tvBuAddClassDataDetailBranch.setText(viewModel?.bhToString())

            btBuAddClassDataDetailModify.setOnClickListener {
                tietBuAddClassDataDetailName.isEnabled = true
                tvBuAddClassDataDetailSportCat.setOnClickListener {

                }
                tietBuAddClassDataDetailName.isEnabled = true
                tvBuAddClassDataDetailStartTime.setOnClickListener {
                    tvBuAddClassDataDetailStartTime.showSoftInputOnFocus = false
                    openStartDateTimeDialogs()
                    tvBuAddClassDataDetailStartTime.text = viewModel?.startTimeToString()
                }
                tvBuAddClassDataDetailEndTime.setOnClickListener {
                    tvBuAddClassDataDetailEndTime.showSoftInputOnFocus = false
                    openEnDateTimeDialogs()
                    tvBuAddClassDataDetailStartTime.text = viewModel?.endTimeToString()
                }
                tvBuAddClassDataDetailBranch.setOnClickListener {
                    tvBuAddClassDataDetailBranch.showSoftInputOnFocus = false
                    showBranchSelection()
                }
                tietBuAddClassDataDetailPlace.isEnabled = true
                tietBuAddClassDataDetailPoint.isEnabled = true
                tietBuAddClassDataDetailLimit.isEnabled = true
                tvBuAddClassDataDetailRegiStartTime.setOnClickListener {
                    tvBuAddClassDataDetailRegiStartTime.showSoftInputOnFocus = false
                    openRegiStartDateTimeDialogs()
                    tvBuAddClassDataDetailRegiStartTime.text = viewModel?.regiTimeToString()
                }
                tvBuAddClassDataDetailRegiEndTime.setOnClickListener {
                    tvBuAddClassDataDetailRegiEndTime.showSoftInputOnFocus = false
                    openRegiEdDateTimeDialogs()
                    tvBuAddClassDataDetailRegiEndTime.text = viewModel?.regiEdTimeToString()
                }
                btBuAddClassDataDetailModify.visibility = View.GONE
                btBuAddClassDataDetailSave.visibility = View.VISIBLE
            }

            btBuAddClassDataDetailSave.setOnClickListener {
                tietBuAddClassDataDetailName.isEnabled = false
                tvBuAddClassDataDetailSportCat.isEnabled = false
                tietBuAddClassDataDetailName.isEnabled = false
                tvBuAddClassDataDetailStartTime.isEnabled = false
                tvBuAddClassDataDetailEndTime.isEnabled = false
                tvBuAddClassDataDetailBranch.isEnabled = false
                tietBuAddClassDataDetailPlace.isEnabled = false
                tietBuAddClassDataDetailPoint.isEnabled = false
                tietBuAddClassDataDetailLimit.isEnabled = false
                tvBuAddClassDataDetailRegiStartTime.isEnabled = false
                tvBuAddClassDataDetailRegiEndTime.isEnabled = false
                btBuAddClassDataDetailModify.visibility = View.VISIBLE
                btBuAddClassDataDetailSave.visibility = View.GONE

                viewModel?.classs?.value.run {

                    val bh_name = tvBuAddClassDataDetailBranch.text.toString().trim()

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

                    val sc_name = tvBuAddClassDataDetailSportCat.text.toString()
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

                    val ci_start_time = tvBuAddClassDataDetailStartTime.text.toString().trim()
                    val timestamp1 = Timestamp.valueOf(ci_start_time)
                    viewModel?.classs?.value?.ci_start_time = timestamp1

                    val ci_ed_time =  tvBuAddClassDataDetailEndTime.text.toString().trim()
                    val timestamp2 = Timestamp.valueOf(ci_ed_time)
                    viewModel?.classs?.value?.ci_start_time = timestamp2

                    val ci_regi_start_time =  tvBuAddClassDataDetailEndTime.text.toString().trim()
                    val timestamp3 = Timestamp.valueOf(ci_regi_start_time)
                    viewModel?.classs?.value?.ci_start_time = timestamp3

                    val ci_regi_ed_time =  tvBuAddClassDataDetailEndTime.text.toString().trim()
                    val timestamp4 = Timestamp.valueOf(ci_regi_ed_time)
                    viewModel?.classs?.value?.ci_start_time = timestamp4

                    requestTask<JsonObject>(url, "PUT", viewModel?.classs?.value)
                    println(viewModel?.classs?.value)

                    }
                }

                if(viewModel?.classs?.value?.ci_avail == true){
                    btBuAddClassDataDetailUnavailable.visibility = View.VISIBLE
                    btBuAddClassDataDetailAvailable.visibility = View.GONE
                }else{
                    btBuAddClassDataDetailUnavailable.visibility = View.GONE
                    btBuAddClassDataDetailAvailable.visibility = View.VISIBLE
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
            updatetvBuAddClassDataDetailStartTime()

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
    private fun updatetvBuAddClassDataDetailStartTime() {
        val format = SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.getDefault())
        val datetime = format.format(calendar.time)
        binding.tvBuAddClassDataDetailStartTime.text = datetime
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
            updatetvBuAddClassDataDetailEdTime()

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
    private fun updatetvBuAddClassDataDetailEdTime() {
        val format = SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.getDefault())
        val datetime = format.format(calendar.time)
        binding.tvBuAddClassDataDetailEndTime.text = datetime
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
            updatetvBuAddClassDataDetailRegiStartTime()

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
    private fun updatetvBuAddClassDataDetailRegiStartTime() {
        val format = SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.getDefault())
        val datetime = format.format(calendar.time)
        binding.tvBuAddClassDataDetailRegiStartTime.text = datetime
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
            updatetvBuAddClassDataDetailRegiEndTime()

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
    private fun updatetvBuAddClassDataDetailRegiEndTime() {
        val format = SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.getDefault())
        val datetime = format.format(calendar.time)
        binding.tvBuAddClassDataDetailRegiStartTime.text = datetime
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
                    binding.tvBuAddClassDataDetailBranch.text = selectedBranch
                }
            }
            // false代表要點擊按鈕方能關閉，預設為true
            .setCancelable(true)
            .show()
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
                    binding.tvBuAddClassDataDetailSportCat.text = selectedBranch
                }
            }
            // false代表要點擊按鈕方能關閉，預設為true
            .setCancelable(true)
            .show()
    }
}