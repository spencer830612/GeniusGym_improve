package com.example.geniusgym.coach.calendarMemberListDetail.controller

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
import com.example.geniusgym.coach.calendarMemberListDetail.viewmodel.CoCalenderMemberStaticSmallViewModel
import com.example.geniusgym.databinding.FragmentCoCalenderMemberStaticSmallBinding
import com.google.gson.GsonBuilder
import kotlinx.coroutines.runBlocking
import java.net.ConnectException

class CoCalenderMemberStaticSmallFragment : Fragment() {
    private lateinit var binding: FragmentCoCalenderMemberStaticSmallBinding
    private lateinit var item: SportRecordBigItem

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCoCalenderMemberStaticSmallBinding.inflate(
            inflater, container, false
        )
        val viewModel: CoCalenderMemberStaticSmallViewModel by viewModels()
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val coActivity = requireActivity() as CoActivity
        with(binding) {
            coActivity.memberSportBigRecord?.let {
                item = it
                viewModel?.textDate?.value = item.time
                viewModel?.sc_id = (item.data)[0].sc_id
                viewModel?.sportName?.value = (item.data)[0].sc_name
                viewModel?.recordItems?.value = item.data

                val member = coActivity.binding.viewModel?.member?.value
                member?.let {
                    viewModel?.m_id = member.memberId
                    viewModel?.name?.value = member.name
                }

                rvCoCaMeStSm.layoutManager = LinearLayoutManager(requireContext())
                viewModel?.recordItems?.observe(viewLifecycleOwner) { items ->
                    if (rvCoCaMeStSm.adapter == null) {
                        rvCoCaMeStSm.adapter = CoCaMeStSmAdapter(items)
                    } else {
                        (rvCoCaMeStSm.adapter as CoCaMeStSmAdapter).update(items)
                    }
                }
            }
            viewModel?.setID?.value = viewModel?.recordItems?.value?.get(0)?.sd_bigid
            btCoCaMeStSmCancel.setOnClickListener {
                Navigation.findNavController(it).popBackStack()
            }
            btCoCaMeStSmDelete.setOnClickListener {
                val onClickListener = DialogInterface.OnClickListener { dialog, which ->
                    if (which == AlertDialog.BUTTON_POSITIVE) {
                        viewModel?.m_id?.let {
                            remove(it)
                            runBlocking {
                                try {
                                    viewModel?.sportDataDeleteBigid()
                                }catch (e:ConnectException){
                                    e.printStackTrace()
                                    Toast.makeText(requireContext(),"連線失敗",Toast.LENGTH_SHORT)
                                }
                            }
                        }
                    }
                    Navigation.findNavController(view).popBackStack()
                    dialog.cancel()
                }

                android.app.AlertDialog.Builder(view.context)
                    .setMessage(R.string.txtCoCaMeStSmDeleteSendMessage)
                    .setPositiveButton(R.string.txtCoCaMeReAfAdapterYes, onClickListener)
                    .setNegativeButton(R.string.txtCoCaMeReAfAdapterCancel, onClickListener)
                    // false代表要點擊按鈕方能關閉，預設為true
                    .setCancelable(true)
                    .show()
            }
            btCoCaMeStSmSend.setOnClickListener {
                val onClickListener = DialogInterface.OnClickListener { dialog, which ->
                    if (which == AlertDialog.BUTTON_POSITIVE) {
                        if (viewModel?.recordItems?.value?.size == 0) {
                            viewModel?.m_id?.let {
                                remove(it)
                                runBlocking {
                                    try {
                                        viewModel?.sportDataDeleteBigid()
                                    }catch (e:ConnectException){
                                        e.printStackTrace()
                                        Toast.makeText(requireContext(),"連線失敗",Toast.LENGTH_SHORT)
                                    }
                                }
                            }
                        } else {
                            viewModel?.recordItems?.value?.let {
                                item.data = it
                                item.count = it.size.toString()
                                item.time = viewModel?.textDate?.value
                                runBlocking {
                                    try {
                                        viewModel?.sportDataDeleteBigid()
                                        viewModel?.sportDataUpload()
                                    }catch (e:ConnectException){
                                        e.printStackTrace()
                                        Toast.makeText(requireContext(),"連線失敗",Toast.LENGTH_SHORT)
                                    }

                                }
                                val gson = GsonBuilder().create()
                                val jsonStr = gson.toJson(coActivity.memberSportRecord)
                                requireActivity().getPreferences(Context.MODE_PRIVATE).edit()
                                    //.putString(coActivity.memberSportRecord[0].m_id, jsonStr)
                                    .putString(coActivity.binding.viewModel?.member?.value?.memberId, jsonStr)
                                    .apply()
                            }
                        }
                    }

                    Navigation.findNavController(view).popBackStack()
                    dialog.cancel()
                }

                val sendText =
                    if (viewModel?.recordItems?.value?.size == 0) {
                        R.string.txtCoCaMeStSmEmptySendMessage
                    } else {
                        R.string.txtCoCaMeStSmSendMessage
                    }
                android.app.AlertDialog.Builder(view.context)
                    .setMessage(sendText)
                    .setPositiveButton(R.string.txtCoCaMeReAfAdapterYes, onClickListener)
                    .setNegativeButton(R.string.txtCoCaMeReAfAdapterCancel, onClickListener)
                    // false代表要點擊按鈕方能關閉，預設為true
                    .setCancelable(true)
                    .show()
            }
        }
    }


    private fun remove(id: String) {
        val coActivity = requireActivity() as CoActivity
        coActivity.memberSportRecord.remove(coActivity.memberSportBigRecord)
        val gson = GsonBuilder().create()
        val jsonStr = gson.toJson(coActivity.memberSportRecord)
        if (jsonStr == "[]") {
            requireActivity().getPreferences(Context.MODE_PRIVATE).edit()
                .remove(id)
                .apply()
        } else {
            requireActivity().getPreferences(Context.MODE_PRIVATE).edit()
                .putString(id, jsonStr)
                .apply()
        }
    }

}