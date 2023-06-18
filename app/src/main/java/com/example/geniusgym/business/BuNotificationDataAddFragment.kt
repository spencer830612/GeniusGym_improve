package com.example.geniusgym.business

import android.Manifest
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.example.geniusgym.business.viewModel.BuNotificationDataAddViewModel
import com.example.geniusgym.databinding.FragmentBuNotificationAddBinding
import java.util.*

class BuNotificationDataAddFragment : Fragment() {
    private lateinit var binding: FragmentBuNotificationAddBinding
    private val calendar = Calendar.getInstance()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentBuNotificationAddBinding.inflate(inflater, container, false)
        val viewModel: BuNotificationDataAddViewModel by viewModels()
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        with(binding){
//            spBuAddChooseBranch.setOnClickListener {
//                spBuAddChooseBranch.showSoftInputOnFocus = false
//                showBranchSelection()
//            }
//
//            spBuAddChooseTarget.setOnClickListener {
//                spBuAddChooseTarget.showSoftInputOnFocus = false
//                showTargetSelection()
//            }
//
//            tvBuAddChooseDate.setOnClickListener {
//                tvBuAddChooseDate.showSoftInputOnFocus = false
//                openDateTimeDialogs()
//            }


            btBuAddNotiCancel.setOnClickListener {
                Navigation.findNavController(it).popBackStack()
            }

            btBuAddNotiSaveNoti.setOnClickListener {
                Toast.makeText(activity, "發送成功", Toast.LENGTH_LONG).show()
                //todo btBuAddNotiSaveNoti的資料儲存與頁面跳轉
            }

        }
    }

    override fun onStart() {
        super.onStart()
        // API 33開始需要加上requestPermissionLauncher
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            requestPermissionLauncher.launch(Manifest.permission.POST_NOTIFICATIONS)
        }
    }

    private var requestPermissionLauncher =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { granted ->
            Log.d("myTag", "granted: $granted")
        }


//    private fun showBranchSelection(){
//        var choice = arrayOf("分店A","分店B","分店C","分店D")
//        var selectchoice = booleanArrayOf(false,false,false,false)
//
//
//        AlertDialog.Builder(view?.context)
//            // 設定標題文字
//            .setTitle(R.string.spBuAddChooseBranch)
//            .setMultiChoiceItems(choice,selectchoice){ _, position, checked ->
//                selectchoice[position] = checked
//            }
//            .setPositiveButton(R.string.bu_add_choose_branch_confirm){ _, _ ->
//                val selectedBranches = mutableListOf<String>()
//                for (i in choice.indices) {
//                    if (selectchoice[i]) {
//                        selectedBranches.add(choice[i])
//                    }
//                }
//                updateSpBuAddChooseBranch(selectedBranches)
//            }
//            // false代表要點擊按鈕方能關閉，預設為true
//            .setCancelable(true)
//            .show()
//    }
//    private fun updateSpBuAddChooseBranch(branches: List<String>) {
//        binding.spBuAddChooseBranch.text = branches.joinToString("、")
//        binding.spBuAddChooseBranch.setTextColor(Color.BLACK)
//    }



//    private fun showTargetSelection(){
//        var choice = arrayOf("會員","教練","員工")
//        var selectchoice = booleanArrayOf(false,false,false)
//
//
//        AlertDialog.Builder(view?.context)
//            // 設定標題文字
//            .setTitle(R.string.spBuAddChooseTarget)
//            .setMultiChoiceItems(choice,selectchoice){ _, position, checked ->
//                selectchoice[position] = checked
//            }.setPositiveButton(R.string.bu_add_choose_branch_confirm){ _, _ ->
//                val selectedTargets = mutableListOf<String>()
//                for (i in choice.indices) {
//                    if (selectchoice[i]) {
//                        selectedTargets.add(choice[i])
//                    }
//                }
//                updateSpBuAddChooseTarget(selectedTargets)
//
//            }
//            // false代表要點擊按鈕方能關閉，預設為true
//            .setCancelable(true)
//            .show()
//    }
//    private fun updateSpBuAddChooseTarget(targets: List<String>) {
//        binding.spBuAddChooseTarget.text = targets.joinToString("、")
//        binding.spBuAddChooseTarget.setTextColor(Color.BLACK)
//    }



//    private fun openDateTimeDialogs() {
//        val dateListener = DatePickerDialog.OnDateSetListener { _, year, month, day ->
//            calendar.set(Calendar.YEAR, year)
//            calendar.set(Calendar.MONTH, month)
//            calendar.set(Calendar.DAY_OF_MONTH, day)
//            openTimePickerDialog()
//        }
//
//        val datePickerDialog = DatePickerDialog(
//            requireContext(),
//            dateListener,
//            calendar.get(Calendar.YEAR),
//            calendar.get(Calendar.MONTH),
//            calendar.get(Calendar.DAY_OF_MONTH)
//        )
//        datePickerDialog.show()
//    }
//    private fun openTimePickerDialog() {
//        val timeListener = TimePickerDialog.OnTimeSetListener { _, hour, minute ->
//            calendar.set(Calendar.HOUR_OF_DAY, hour)
//            calendar.set(Calendar.MINUTE, minute)
//            updateTvBuAddChooseDate()
//        }
//
//        val timePickerDialog = TimePickerDialog(
//            requireContext(),
//            timeListener,
//            calendar.get(Calendar.HOUR_OF_DAY),
//            calendar.get(Calendar.MINUTE),
//            true
//        )
//        timePickerDialog.show()
//    }
//    private fun updateTvBuAddChooseDate() {
//        val format = SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.getDefault())
//        val dateTime = format.format(calendar.time)
//        binding.tvBuAddChooseDate.text = dateTime
//        binding.tvBuAddChooseDate.setTextColor(Color.BLACK)
//    }
}