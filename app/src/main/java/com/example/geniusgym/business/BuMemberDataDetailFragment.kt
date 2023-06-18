package com.example.geniusgym.business

import android.app.Activity
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.provider.MediaStore
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.viewModels
import com.example.geniusgym.business.model.Member
import com.example.geniusgym.business.model.testBuMember
import com.example.geniusgym.business.viewModel.BuMemberViewModel
import com.example.geniusgym.databinding.FragmentBuMemberDataDetailBinding
import com.example.geniusgym.sharedata.MeShareData.javaWebUrl
import com.google.gson.JsonObject
import tw.idv.william.androidwebserver.core.service.requestTask
import java.sql.Timestamp
import java.text.SimpleDateFormat
import java.util.*

class BuMemberDataDetailFragment : Fragment() {
    private lateinit var binding: FragmentBuMemberDataDetailBinding
    private val calendar = Calendar.getInstance()
    val url = javaWebUrl + "buMember"
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentBuMemberDataDetailBinding.inflate(inflater, container, false)
        val viewModel: BuMemberViewModel by viewModels()
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        with(binding) {
            arguments?.let { bundle ->
                bundle.getSerializable("bumember")?.let {
                    viewModel?.member?.value = it as Member
                }
            }

            tietBuAddMemDataDetailGen.setText(viewModel?.genToString())

            btBuAddMemDataDetailModify.setOnClickListener {
//                ivBuAddMemDataDetailPic.setOnClickListener {
//                    val intent = Intent(
//                        Intent.ACTION_PICK,
//                        MediaStore.Images.Media.EXTERNAL_CONTENT_URI
//                    )
//                    pickPictureLauncher.launch(intent)
//                }
                tietBuAddMemDataDetailName.isEnabled = true
                tietBuAddMemDataDetailPwd.isEnabled = true
                tietBuAddMemDataDetailGen.isEnabled = true
                tietBuAddMemDataDetailCell.isEnabled = true
                tietBuAddMemDataDetailTwid.isEnabled = true
                tietBuAddMemDataDetailAddr.isEnabled = true
                tietBuAddMemDataDetailEmail.isEnabled = true
                tietBuAddMemDataDetailExpireDate.isEnabled = true
                tietBuAddMemDataDetailExpireDate.setOnClickListener {
                    tietBuAddMemDataDetailExpireDate.showSoftInputOnFocus = false
                    openDateTimeDialogs()
                    tietBuAddMemDataDetailExpireDate.text = viewModel?.timeToString()
                }
                btBuAddMemDataDetailModify.visibility = View.GONE
                btBuAddMemDataDetailSave.visibility = View.VISIBLE
            }

            btBuAddMemDataDetailSave.setOnClickListener {
                tietBuAddMemDataDetailName.isEnabled = false
                tietBuAddMemDataDetailID.isEnabled = false
                tietBuAddMemDataDetailPwd.isEnabled = false
                tietBuAddMemDataDetailGen.isEnabled = false
                tietBuAddMemDataDetailCell.isEnabled = false
                tietBuAddMemDataDetailTwid.isEnabled = false
                tietBuAddMemDataDetailAddr.isEnabled = false
                tietBuAddMemDataDetailEmail.isEnabled = false
                tietBuAddMemDataDetailExpireDate.isEnabled = false
                btBuAddMemDataDetailSave.visibility = View.GONE
                btBuAddMemDataDetailModify.visibility = View.VISIBLE

                viewModel?.member?.value.run {
                    val m_gender = tietBuAddMemDataDetailGen.text.toString().trim()

                    if (m_gender.isEmpty()) {
                        println("空的")
                        return@setOnClickListener
                    } else {
                        val gender: Int = when (m_gender) {
                            "女" -> 0
                            "男" -> 1
                            else ->
                                return@setOnClickListener
                        }
                        viewModel?.member?.value?.m_gen = gender

                        val m_date = tietBuAddMemDataDetailExpireDate.text.toString().trim()
                        val timestamp = Timestamp.valueOf(m_date)
                        viewModel?.member?.value?.m_ed_date = timestamp


                        requestTask<JsonObject>(url, "PUT", viewModel?.member?.value)
                        println(viewModel?.member?.value)
                    }
                }
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
        binding.tietBuAddMemDataDetailExpireDate.text = dateTime
        binding.tietBuAddMemDataDetailExpireDate.setTextColor(Color.BLACK)
    }


//    private var pickPictureLauncher =
//        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
//            if (result.resultCode == Activity.RESULT_OK) {
//                result.data?.data?.let { uri -> binding.ivBuAddMemDataDetailPic.setImageURI(uri) }
//            }
//        }
}