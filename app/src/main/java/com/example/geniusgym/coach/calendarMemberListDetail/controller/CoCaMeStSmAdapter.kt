package com.example.geniusgym.coach.calendarMemberListDetail.controller

import android.content.Context
import android.content.DialogInterface
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.findViewTreeLifecycleOwner
import androidx.recyclerview.widget.RecyclerView
import com.example.geniusgym.R
import com.example.geniusgym.coach.calendarMemberListDetail.model.SportRecordItem
import com.example.geniusgym.coach.calendarMemberListDetail.viewmodel.CoCalenderMemberRecordAfterViewModel
import com.example.geniusgym.coach.calendarMemberListDetail.viewmodel.CoCalenderMemberStaticSmallViewModel
import com.example.geniusgym.databinding.FragmentCoCalendarMemberRecordAfterCardviewBinding
import com.example.geniusgym.databinding.FragmentCoCalendarMemberStaticSmallCardviewBinding

private lateinit var parentView: Context
class CoCaMeStSmAdapter(private var items: MutableList<SportRecordItem>) :
    RecyclerView.Adapter<CoCaMeStSmAdapter.CoCaMeStSmViewHolder>() {
    class CoCaMeStSmViewHolder(val itemViewBinding: FragmentCoCalendarMemberStaticSmallCardviewBinding) :
        RecyclerView.ViewHolder(itemViewBinding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoCaMeStSmViewHolder {
        val itemViewBinding = FragmentCoCalendarMemberStaticSmallCardviewBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        parentView = parent.context
        itemViewBinding.viewModel = CoCalenderMemberStaticSmallViewModel()
        itemViewBinding.lifecycleOwner = parent.findViewTreeLifecycleOwner()
        return CoCaMeStSmViewHolder(itemViewBinding)

    }

    override fun onBindViewHolder(holder: CoCaMeStSmViewHolder, position: Int) {
        val item = items[position]
        with(holder) {
            itemViewBinding.viewModel?.recordItem?.value = item
            itemViewBinding.ivCoCaMeStSmallCardRemove.setOnClickListener {
                val onClickListener = DialogInterface.OnClickListener { dialog, which ->
                    if (which == AlertDialog.BUTTON_POSITIVE) {
                        items.remove(item)
                        notifyDataSetChanged()
                    }
                    dialog.cancel()
                }
                android.app.AlertDialog.Builder(parentView)
                    .setMessage(R.string.txtCoCaMeReAfAdapterDialogMessage)
                    .setPositiveButton(R.string.txtCoCaMeReAfAdapterYes, onClickListener)
                    .setNegativeButton(R.string.txtCoCaMeReAfAdapterCancel, onClickListener)
                    // false代表要點擊按鈕方能關閉，預設為true
                    .setCancelable(true)
                    .show()
            }
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun update(items: MutableList<SportRecordItem>) {
        this.items = items
        notifyDataSetChanged()
    }
}