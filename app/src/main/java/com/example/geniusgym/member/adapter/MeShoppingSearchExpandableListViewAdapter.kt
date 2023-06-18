package com.example.geniusgym.member.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseExpandableListAdapter
import com.example.geniusgym.databinding.RecycleCellMeShoppingSearchGroupBinding
import com.example.geniusgym.databinding.RecycleCellMeShoppingSearchSubBinding
import com.example.geniusgym.member.model.SportBigCat
import com.example.geniusgym.member.model.SportCat

class MeShoppingSearchExpandableListViewAdapter(private val context: Context,
                                                private val LessonKind: List<SportBigCat>,
                                                private val LessonSubKind : List<List<SportCat>>)
    : BaseExpandableListAdapter() {

    companion object{
        private var AllKindId = mutableSetOf<Int>()
//        處理錯位問題，因為點擊大項目時，會整個資料刷新，因此放在靜態，讓資料不會被刷新掉
        private val allKindText = mutableSetOf<String>()
    }


    override fun getGroupCount(): Int {
        return LessonKind.size
    }

    override fun getChildrenCount(groupPosition: Int): Int {
        return LessonSubKind[groupPosition].size
    }

    override fun getGroup(groupPosition: Int): Any {
        return LessonKind[groupPosition]
    }

    override fun getChild(groupPosition: Int, childPosition: Int): Any {
        return LessonSubKind[groupPosition][childPosition]
    }

    override fun getGroupId(groupPosition: Int): Long {
        return groupPosition.toLong()
    }

    override fun getChildId(groupPosition: Int, childPosition: Int): Long {
        return (groupPosition * 100 + childPosition).toLong()
    }

    override fun hasStableIds(): Boolean {
        return true
    }

    override fun getGroupView(
        groupPosition: Int,
        isExpanded: Boolean,
        convertView: View?,
        parent: ViewGroup?
    ): View {
        val bindingGroup : RecycleCellMeShoppingSearchGroupBinding = RecycleCellMeShoppingSearchGroupBinding.inflate(
            LayoutInflater.from(context), parent, false)
        convertView?.visibility = View.INVISIBLE
        bindingGroup.tvMeSearchKind.text = LessonKind[groupPosition].sb_name
        return bindingGroup.root
    }

    override fun getChildView(
        groupPosition: Int,
        childPosition: Int,
        isLastChild: Boolean,
        convertView: View?,
        parent: ViewGroup?
    ): View {
        val bindingSub : RecycleCellMeShoppingSearchSubBinding = RecycleCellMeShoppingSearchSubBinding.inflate(
            LayoutInflater.from(context), parent, false
        )

        val kindText = LessonSubKind[groupPosition][childPosition].sc_name
        bindingSub.checkBox.text = kindText
//        重設check的點擊狀態
        if (allKindText.contains(kindText)){
            bindingSub.checkBox.isChecked = true
            Log.d("textTest", allKindText.toString())
        }
        bindingSub.checkBox.setOnClickListener {
            val id = LessonSubKind[groupPosition][childPosition].sc_id
            if (bindingSub.checkBox.isChecked){
                allKindText.add(kindText)
                AllKindId.add(id)
            }else{
                allKindText.remove(kindText)
                AllKindId.remove(id)
            }
        }
        return bindingSub.root
    }

    override fun isChildSelectable(groupPosition: Int, childPosition: Int): Boolean {
        return true
    }

    fun getAllKindId() : Set<Int>{
        return AllKindId
    }

    fun clearSet(){
        AllKindId.clear()
        allKindText.clear()

    }



}
