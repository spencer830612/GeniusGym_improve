package com.example.geniusgym.coach

import com.example.geniusgym.coach.calendarMemberList.model.ClassItem
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.geniusgym.coach.calendarMemberList.controller.MemberItemAdapter
import com.example.geniusgym.coach.calendarMemberList.viewmodel.CoCalendarMemberListViewModel
import com.example.geniusgym.databinding.FragmentCoCalendarMemberListBinding

class CoCalendarMemberListFragment : Fragment() {

    private lateinit var binding:FragmentCoCalendarMemberListBinding
    private lateinit var classid:String
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val viewModel : CoCalendarMemberListViewModel by viewModels()
        binding = FragmentCoCalendarMemberListBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        with(binding){
            arguments?.let{bundle ->
                bundle.getSerializable("Class")?.let{
                    val classItem = it as ClassItem
                    val classId = classItem.ci_id
                    viewModel?.search(classId)
                    rvCoCaMe.layoutManager = LinearLayoutManager(requireContext())
                    val coActivity = requireActivity() as CoActivity
                    viewModel?.items?.observe(viewLifecycleOwner){items ->
                        if(rvCoCaMe.adapter == null){
                            rvCoCaMe.adapter = MemberItemAdapter(items, coActivity)
                        }else{
                            (rvCoCaMe.adapter as MemberItemAdapter).updateItem(items)
                        }
                    }
                }
            }
        }
    }
}