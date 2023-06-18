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
import androidx.fragment.app.viewModels
import com.example.geniusgym.R
import com.example.geniusgym.business.model.Coach
import com.example.geniusgym.business.model.testBuCoach
import com.example.geniusgym.business.viewModel.BuCoachViewModel
import com.example.geniusgym.databinding.FragmentBuCoachDataDetailBinding
import com.example.geniusgym.sharedata.MeShareData.javaWebUrl
import com.google.gson.JsonObject
import tw.idv.william.androidwebserver.core.service.requestTask
import java.sql.Timestamp
import java.text.SimpleDateFormat
import java.util.*


class BuCoachDataDetailFragment : Fragment() {
    private lateinit var binding: FragmentBuCoachDataDetailBinding
    private val calendar = Calendar.getInstance()
    val url = javaWebUrl + "buCoach"
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentBuCoachDataDetailBinding.inflate(inflater, container, false)
        val viewModel: BuCoachViewModel by viewModels()
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        with(binding) {
            arguments?.let { bundle ->
                bundle.getSerializable("bucoach")?.let {
                    binding.viewModel?.coach?.value = it as Coach
                    println(bundle)
                }
            }

            tietBuAddCoaDataDetailGen.setText(viewModel?.genToString())

            btBuAddCoaDataDetailModify.setOnClickListener {
                tietBuAddCoaDataDetailName.isEnabled = true
                tietBuAddCoaDataDetailPwd.isEnabled = true
                tietBuAddCoaDataDetailGen.isEnabled = true
                tietBuAddCoaDataDetailCell.isEnabled = true
                tietBuAddCoaDataDetailTwid.isEnabled = true
                tietBuAddCoaDataDetailAddr.isEnabled = true
                tietBuAddCoaDataDetailEmail.isEnabled = true
                tvBuAddCoaDataDetailOBDate.isEnabled = true
                tvBuAddCoaDataDetailOBDate.setOnClickListener {
                    tvBuAddCoaDataDetailOBDate.showSoftInputOnFocus = false
                    openDateTimeDialogs()
                    tvBuAddCoaDataDetailOBDate.text = viewModel?.timeToString()
                }
                tvBuAddCoaDataDetailChooseBranch.setOnClickListener {
                    tvBuAddCoaDataDetailChooseBranch.showSoftInputOnFocus = false
                    showBranchSelection()
                }
                tietBuAddCoaDataDetailIntro.isEnabled = true
                btBuAddCoaDataDetailModify.visibility = View.GONE
                btBuAddCoaDataDetailSave.visibility = View.VISIBLE
            }

            btBuAddCoaDataDetailSave.setOnClickListener {
                tietBuAddCoaDataDetailName.isEnabled = false
                tietBuAddCoaDataDetailID.isEnabled = false
                tietBuAddCoaDataDetailPwd.isEnabled = false
                tietBuAddCoaDataDetailGen.isEnabled = false
                tietBuAddCoaDataDetailCell.isEnabled = false
                tietBuAddCoaDataDetailTwid.isEnabled = false
                tietBuAddCoaDataDetailAddr.isEnabled = false
                tietBuAddCoaDataDetailEmail.isEnabled = false
                tvBuAddCoaDataDetailOBDate.isEnabled = false
                tvBuAddCoaDataDetailChooseBranch.isEnabled = false
                tietBuAddCoaDataDetailIntro.isEnabled = false
                btBuAddCoaDataDetailSave.visibility = View.GONE
                btBuAddCoaDataDetailModify.visibility = View.VISIBLE

                viewModel?.coach?.value.run {
                    val c_gender = tietBuAddCoaDataDetailGen.text.toString().trim()

                    if (c_gender.isEmpty()) {
                        println("空的")
                        return@setOnClickListener
                    } else {
                        val gender: Int = when (c_gender) {
                            "女" -> 0
                            "男" -> 1
                            else ->
                                return@setOnClickListener
                        }
                        viewModel?.coach?.value?.c_gen = gender

                        val c_date = tvBuAddCoaDataDetailOBDate.text.toString().trim()
                        val timestamp = Timestamp.valueOf(c_date)
                        viewModel?.coach?.value?.c_start_date = timestamp


                        requestTask<JsonObject>(url, "PUT", viewModel?.coach?.value)
                        println(viewModel?.coach?.value)
                    }
                }
            }

            if(viewModel?.coach?.value?.c_sus == true){
                btBuAddCoaDataDetailSuspend.visibility = View.VISIBLE
                btBuAddCoaDataDetailActive.visibility = View.GONE
            }else{
                btBuAddCoaDataDetailSuspend.visibility = View.GONE
                btBuAddCoaDataDetailActive.visibility = View.VISIBLE
            }
        }
    }



    private fun openDateTimeDialogs() {
        val dateListener = DatePickerDialog.OnDateSetListener { _, year, month, day ->
            calendar.set(Calendar.YEAR, year)
            calendar.set(Calendar.MONTH, month)
            calendar.set(Calendar.DAY_OF_MONTH, day)
            openTimePickerDialog()
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
    private fun openTimePickerDialog() {
        val timeListener = TimePickerDialog.OnTimeSetListener { _, hour, minute ->
            calendar.set(Calendar.HOUR_OF_DAY, hour)
            calendar.set(Calendar.MINUTE, minute)
            updateTvBuAddChooseDate()
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
    private fun updateTvBuAddChooseDate() {
        val format = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
        val dateTime = format.format(calendar.time)
        binding.tvBuAddCoaDataDetailOBDate.text = dateTime
        binding.tvBuAddCoaDataDetailOBDate.setTextColor(Color.BLACK)
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
                    binding.tvBuAddCoaDataDetailChooseBranch.text = selectedBranch
                }
            }
            // false代表要點擊按鈕方能關閉，預設為true
            .setCancelable(true)
            .show()
    }
//    private fun updateTvBuAddBuzChooseBranch(branch: String) {
//        binding.tvBuAddCoaDataDetailChooseBranch.text = branch
//        binding.tvBuAddCoaDataDetailChooseBranch.setTextColor(Color.BLACK)
//    }

}