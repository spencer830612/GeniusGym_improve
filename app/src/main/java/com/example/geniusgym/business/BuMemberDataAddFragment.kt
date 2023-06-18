package com.example.geniusgym.business

import android.app.Activity
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Intent
import android.graphics.Color
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.databinding.adapters.TextViewBindingAdapter.setText
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.example.geniusgym.business.viewModel.BuMemberDataAddViewModel
import com.example.geniusgym.R
import com.example.geniusgym.databinding.FragmentBuMemberDataAddBinding
import com.example.geniusgym.sharedata.MeShareData.javaWebUrl
import com.google.gson.JsonObject
import tw.idv.william.androidwebserver.core.service.requestTask
import java.sql.Timestamp
import java.text.SimpleDateFormat
import java.util.*

class BuMemberDataAddFragment : Fragment() {
    private lateinit var binding: FragmentBuMemberDataAddBinding
    private val calendar = Calendar.getInstance()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentBuMemberDataAddBinding.inflate(inflater, container, false)
        val viewModel: BuMemberDataAddViewModel by viewModels()
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        with(binding){
            tietBuAddMemDataGen.setText(viewModel?.genToString())


            tietBuAddMemDataExpireDate.setOnClickListener {
                tietBuAddMemDataExpireDate.showSoftInputOnFocus = false
                openDateTimeDialogs()
                tietBuAddMemDataExpireDate.text = viewModel?.timeToString()
            }

            btBuAddMemDataCancel.setOnClickListener {
                Navigation.findNavController(it).popBackStack()
            }

            ivBuAddMemDataPic.setOnClickListener {
                val intent = Intent(
                    Intent.ACTION_PICK,
                    MediaStore.Images.Media.EXTERNAL_CONTENT_URI
                )
                pickPictureLauncher.launch(intent)
            }

            btBuAddMemDataSave.setOnClickListener {
                viewModel?.member?.value.run{
                    val m_gender = tietBuAddMemDataGen.text.toString().trim()

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
                        //println(gender)

                        val m_date = tietBuAddMemDataExpireDate.text.toString().trim()
                        val timestamp = Timestamp.valueOf(m_date)
                        viewModel?.member?.value?.m_ed_date = timestamp

                        val url = javaWebUrl + "buMember"

                        val respbody = requestTask<JsonObject>(url, "POST", viewModel?.member?.value)
                        //println(viewModel?.member?.value.toString())
                        //println(viewModel?.member?.value?.m_ed_date)
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
        binding.tietBuAddMemDataExpireDate.text = dateTime
        binding.tietBuAddMemDataExpireDate.setTextColor(Color.BLACK)
    }



    private var pickPictureLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                result.data?.data?.let { uri -> binding.ivBuAddMemDataPic.setImageURI(uri) }
            }
        }
}