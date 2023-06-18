package com.example.geniusgym.member.controller

import android.os.Binder
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.geniusgym.R
import com.example.geniusgym.databinding.FragmentMePointsBinding
import com.example.geniusgym.member.MePointsViewModel
import com.example.geniusgym.member.adapter.MePointsAdapter
import com.example.geniusgym.sharedata.MeShareData


class MePointsFragment : Fragment() {
    private lateinit var binding : FragmentMePointsBinding
    private lateinit var adapter: MePointsAdapter
//    private lateinit var items : MutableList<MePointBean>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val viewModel = ViewModelProvider(this)[MePointsViewModel::class.java]
        binding = FragmentMePointsBinding.inflate(inflater,container,false)
        binding.viewmodel = viewModel
        viewModel.pointsLiveData.value = MeShareData.personPoint
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = MePointsAdapter(binding.viewmodel!!.mepointitem)
        binding.ptRecyclerlist.layoutManager = LinearLayoutManager(requireContext())
        binding.ptRecyclerlist.adapter = adapter


        val navController = findNavController()
        binding.btTopup.setOnClickListener {
            navController.navigate(R.id.meBuyPointsFragment2)

        }
    }
}


  /* viewModel.pointsum.observe(viewLifecycleOwner,{ pointsum ->
    val newItem = PointItem(此處要放下一頁的點數購買對應)
    adpter.addData(newItem) })

    return inflater.inflate(R.layout.fragment_a, container, false)
    }
}
    }*/












