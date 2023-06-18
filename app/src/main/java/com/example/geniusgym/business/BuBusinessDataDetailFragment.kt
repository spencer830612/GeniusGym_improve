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
import com.example.geniusgym.business.model.Business
import com.example.geniusgym.business.model.testBuBusiness
import com.example.geniusgym.business.viewModel.BuBusinessViewModel
import com.example.geniusgym.databinding.FragmentBuBusinessDataDetailBinding
import com.google.gson.JsonObject
import tw.idv.william.androidwebserver.core.service.requestTask
import java.sql.Timestamp
import java.text.SimpleDateFormat
import java.util.*

class BuBusinessDataDetailFragment : Fragment() {
    private lateinit var binding: FragmentBuBusinessDataDetailBinding
    private val calendar = Calendar.getInstance()
    val url = "http://10.0.2.2:8080/geninusgym_bg/buBuz"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentBuBusinessDataDetailBinding.inflate(inflater, container, false)
        val viewModel: BuBusinessViewModel by viewModels()
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        with(binding){
            arguments?.let { bundle ->
                bundle.getSerializable("bubuz")?.let {
                    binding.viewModel?.buz?.value = it as Business
                    println(bundle)
                }
            }

            tietBuAddBuzDataDetailGen.setText(viewModel?.genToString())

            btBuAddBuzDataDetailModify.setOnClickListener {
                tietBuAddBuzDataDetailName.isEnabled = true
                tietBuAddBuzDataDetailPwd.isEnabled = true
                tietBuAddBuzDataDetailGen.isEnabled = true
                tietBuAddBuzDataDetailCell.isEnabled = true
                tietBuAddBuzDataDetailTwid.isEnabled = true
                tietBuAddBuzDataDetailAddr.isEnabled = true
                tietBuAddBuzDataDetailEmail.isEnabled = true
                tvBuAddBuzDataDetailOBDate.isEnabled = true
                tvBuAddBuzDataDetailOBDate.setOnClickListener {
                    tvBuAddBuzDataDetailOBDate.showSoftInputOnFocus = false
                    openDateTimeDialogs()
                    tvBuAddBuzDataDetailOBDate.text = viewModel?.timeToString()
                }
                tvBuAddBuzDataDetailBranch.setOnClickListener {
                    tvBuAddBuzDataDetailBranch.showSoftInputOnFocus = false
                    showBranchSelection()
                }
                btBuAddBuzDataDetailModify.visibility = View.GONE
                btBuAddBuzDataDetailSave.visibility = View.VISIBLE
            }


            btBuAddBuzDataDetailSave.setOnClickListener {
                tietBuAddBuzDataDetailName.isEnabled = false
                tietBuAddBuzDataDetailID.isEnabled = false
                tietBuAddBuzDataDetailPwd.isEnabled = false
                tietBuAddBuzDataDetailGen.isEnabled = false
                tietBuAddBuzDataDetailCell.isEnabled = false
                tietBuAddBuzDataDetailTwid.isEnabled = false
                tietBuAddBuzDataDetailAddr.isEnabled = false
                tietBuAddBuzDataDetailEmail.isEnabled = false
                tvBuAddBuzDataDetailOBDate.isEnabled = false
                tvBuAddBuzDataDetailBranch.isEnabled = false
                btBuAddBuzDataDetailSave.visibility = View.GONE
                btBuAddBuzDataDetailModify.visibility = View.VISIBLE

                viewModel?.buz?.value.run {
                    val c_gender = tietBuAddBuzDataDetailGen.text.toString().trim()

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
                        viewModel?.buz?.value?.b_gen = gender

                        val c_date = tvBuAddBuzDataDetailOBDate.text.toString().trim()
                        val timestamp = Timestamp.valueOf(c_date)
                        viewModel?.buz?.value?.b_start_date = timestamp


                        requestTask<JsonObject>(url, "PUT", viewModel?.buz?.value)
                        println(viewModel?.buz?.value)
                    }
                }
            }

            if(viewModel?.buz?.value?.b_sus == true){
                btBuAddBuzDataDetailSuspend.visibility = View.VISIBLE
                btBuAddBuzDataDetailActive.visibility = View.GONE
            }else{
                btBuAddBuzDataDetailSuspend.visibility = View.GONE
                btBuAddBuzDataDetailActive.visibility = View.VISIBLE
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
        binding.tvBuAddBuzDataDetailOBDate.text = dateTime

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
                    binding.tvBuAddBuzDataDetailBranch.text = selectedBranch
                }
            }
            // false代表要點擊按鈕方能關閉，預設為true
            .setCancelable(true)
            .show()
    }

}