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
import com.example.geniusgym.databinding.FragmentCoCalendarMemberRecordAfterCardviewBinding

private lateinit var parentView:Context
class CoCaMeReAfAdapter(private var items: MutableList<SportRecordItem>) :
    RecyclerView.Adapter<CoCaMeReAfAdapter.CoCaMeReAfViewHolder>() {
    class CoCaMeReAfViewHolder(val itemViewBinding: FragmentCoCalendarMemberRecordAfterCardviewBinding) :
        RecyclerView.ViewHolder(itemViewBinding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoCaMeReAfViewHolder {
        val itemViewBinding = FragmentCoCalendarMemberRecordAfterCardviewBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        parentView = parent.context
        itemViewBinding.viewModel = CoCalenderMemberRecordAfterViewModel()
        itemViewBinding.lifecycleOwner = parent.findViewTreeLifecycleOwner()
        return CoCaMeReAfViewHolder(itemViewBinding)

    }

    override fun onBindViewHolder(holder: CoCaMeReAfViewHolder, position: Int) {
        val item = items[position]
        with(holder) {
            itemViewBinding.viewModel?.recordItem?.value = item
            itemViewBinding.ivCoCaReAfCardRemove.setOnClickListener {
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