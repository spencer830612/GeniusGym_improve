package com.example.geniusgym.coach.calendarMemberListDetail.controller

import android.app.DatePickerDialog
import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.geniusgym.R
import com.example.geniusgym.coach.CoActivity
import com.example.geniusgym.coach.calendarMemberListDetail.model.SportRecordBigItem
import com.example.geniusgym.coach.calendarMemberListDetail.model.SportSmallItem
import com.example.geniusgym.coach.calendarMemberListDetail.viewmodel.CoCalenderMemberRecordAfterViewModel
import com.example.geniusgym.databinding.FragmentCoCalenderMemberRecordAfterBinding
import com.google.gson.GsonBuilder
import kotlinx.coroutines.runBlocking
import java.net.ConnectException
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

class CoCalenderMemberRecordAfterFragment : Fragment() {

    private lateinit var binding: FragmentCoCalenderMemberRecordAfterBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentCoCalenderMemberRecordAfterBinding.inflate(
            inflater, container, false
        )
        val viewModel: CoCalenderMemberRecordAfterViewModel by viewModels()
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val coActivity = requireActivity() as CoActivity

        with(binding) {

            val date = LocalDate.now()
            viewModel?.textDate?.value = date.format(DateTimeFormatter.ISO_LOCAL_DATE).toString()

            arguments?.let { bundle ->
                bundle.getSerializable("item")?.let {
                    viewModel?.sc_id = (it as SportSmallItem).sc_id
                    viewModel?.sportName?.value = it.sc_name
                }
            }
            val member = coActivity.binding.viewModel?.member?.value
            member?.let {
                viewModel?.m_id = member.memberId
                viewModel?.name?.value = member.name
            }
            val formatter = DateTimeFormatter.ofPattern("yyyyMMdd HH:mm:ss")
            val str = LocalDateTime.now().format(formatter).toString()
            viewModel?.setID?.value = str.hashCode().toString()
            rvCoCaMeReAf.layoutManager = LinearLayoutManager(requireContext())
            viewModel?.recordItems?.observe(viewLifecycleOwner) { items ->
                if (rvCoCaMeReAf.adapter == null) {
                    rvCoCaMeReAf.adapter = CoCaMeReAfAdapter(items)
                } else {
                    (rvCoCaMeReAf.adapter as CoCaMeReAfAdapter).update(items)
                }
            }


            btCoCaMeReAfSend.setOnClickListener { view ->
                val coActivity = requireActivity() as CoActivity
                val onClickListener = DialogInterface.OnClickListener { dialog, which ->
                    if (which == AlertDialog.BUTTON_POSITIVE) {
                        viewModel?.recordItems?.value?.let {
                            coActivity.memberSportRecord.add(
                                SportRecordBigItem(
                                    it,
                                    viewModel?.m_id,
                                    viewModel?.sportName?.value,
                                    viewModel?.textDate?.value,
                                    it.size.toString()
                                )
                            )
                            val gson = GsonBuilder().create()
                            val jsonStr = gson.toJson(coActivity.memberSportRecord)

                            requireActivity().getPreferences(Context.MODE_PRIVATE).edit()
                                .putString(viewModel?.m_id, jsonStr).apply()
                            runBlocking {
                                try {
                                    viewModel?.sportDataUpload()
                                } catch (e: ConnectException) {
                                    e.printStackTrace()
                                    Toast.makeText(requireContext(),"連線失敗", Toast.LENGTH_SHORT)
                                }
                            }
                            Navigation.findNavController(view).popBackStack()
                        }
                    }
                    dialog.cancel()
                }
                viewModel?.recordItems?.value?.let { recordItems ->

                    android.app.AlertDialog.Builder(view.context)
                        .setMessage(R.string.txtCoCaMeReAfSendDialogMessage)
                        .setPositiveButton(R.string.txtCoCaMeReAfAdapterYes, onClickListener)
                        .setNegativeButton(R.string.txtCoCaMeReAfAdapterCancel, onClickListener)
                        // false代表要點擊按鈕方能關閉，預設為true
                        .setCancelable(true).show()
                }
            }

            tvCoCaMeReAfDate.setOnClickListener {
                val calendar = Calendar.getInstance()
                val datePickerDialog = DatePickerDialog(
                    requireContext(),
                    { _, year, month, day ->
                        viewModel?.textDate?.value = "$year-${pad(month + 1)}-${pad(day)}"
                    },
                    calendar.get(Calendar.YEAR),
                    calendar.get(Calendar.MONTH),
                    calendar.get(Calendar.DAY_OF_MONTH)
                )
                datePickerDialog.show()
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
}