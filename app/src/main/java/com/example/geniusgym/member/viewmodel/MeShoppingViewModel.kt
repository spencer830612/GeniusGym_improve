package com.example.geniusgym.member.viewmodel

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Context
import android.text.SpannableString
import android.text.Spanned
import android.text.style.ImageSpan
import android.view.Gravity
import android.view.LayoutInflater
import android.view.Window
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.geniusgym.R
import com.example.geniusgym.databinding.DialogShopitemBinding
import com.example.geniusgym.member.adapter.MeShoppingAdapter
import com.example.geniusgym.member.adapter.MeShoppingSearchExpandableListViewAdapter
import com.example.geniusgym.member.model.ClassInfo
import com.example.geniusgym.member.model.SportBigCat
import com.example.geniusgym.member.model.SportCat


class MeShoppingViewModel : ViewModel() {

    private val _shopitems = mutableListOf<ClassInfo>()
    val shopitems : MutableLiveData<List<ClassInfo>> by lazy { MutableLiveData() }
    val branchName : MutableLiveData<String> by lazy { MutableLiveData() }
    init {
        update()

    }

    private fun search(set: Set<Int>, searchText : String) {
        val setFiltered  = mutableSetOf<ClassInfo>()
        if (set.isEmpty()) {
//           doNothing
        } else {
            set.forEach {filterId ->
                _shopitems.forEach{
                    if (it.sc_id == filterId){
                        setFiltered.add(it)
                    }
                }
            }
            shopitems.value = setFiltered.toList()
        }
        if (searchText.isEmpty() || searchText.isBlank()){
//           doNothing
        }else{
            _shopitems.forEach{
                if (it.ci_name!!.contains(searchText) || it.c_id!!.contains(searchText)){
                    setFiltered.add(it)
                }
            }
            shopitems.value = setFiltered.toList()
        }
    }

    @SuppressLint("ResourceType")
    fun createDiaglog(context : Context, meAdapter: MeShoppingAdapter) : Dialog {
        val dialog = Dialog(context)
        val bindingDialog : DialogShopitemBinding = DialogShopitemBinding.inflate(LayoutInflater.from(context))
//        設定dialog頁面
        val window = dialog.window
        window?.setGravity(Gravity.CENTER)
        window?.setContentView(bindingDialog.root)
        window?.setWindowAnimations(R.style.dialog_style)
//        TODO:動畫執行失敗

//        設定EditText的提示文字
        bindingDialog.edtMeShoppingSearch.hint = setDialogSpannableString(context)

//        設定adapter
        val adapter = setDialogAdapter(context)
        bindingDialog.elvMeShopping.setAdapter(adapter)

        bindingDialog.btnMeShoppingConfirm.setOnClickListener {
//            取得篩選的運動類型、教練名稱、課程名稱
            val filterId = adapter.getAllKindId()
//            執行搜尋
            search(filterId, bindingDialog.edtMeShoppingSearch.text.toString())
//            將結果返回並更新MeShopping的RecycleView
            shopitems.value?.let { it1 -> meAdapter.update(it1) }
//            清除這次已點擊的類型
            adapter.clearSet()
            dialog.dismiss()
        }
//
        bindingDialog.btnMeShoppingCancel.setOnClickListener {
//            清除這次已點擊的類型
            adapter.clearSet()
            dialog.dismiss()
        }
        return dialog
    }

//
    private fun setDialogAdapter(context: Context) : MeShoppingSearchExpandableListViewAdapter{
        val adapter =
            MeShoppingSearchExpandableListViewAdapter(
                context,
                sportbigcats,
                sportcats
            )
        return adapter
    }
    private fun setDialogSpannableString(context : Context) : SpannableString{
        //        設定提示文字附加圖片
        val imageHint = ImageSpan(context, R.drawable.baseline_search_24)
        val spannableString = SpannableString(context.getString(R.string.meSearchViewLessonName))
        spannableString.setSpan(imageHint, 0, 1, Spanned.SPAN_INCLUSIVE_EXCLUSIVE)
        return spannableString
    }


    private fun update(){
        _shopitems.add(ClassInfo(1, "螺旋有氧", "14:00", "15:00", "A502",
                                    500, 8, "2023/04/15", "本課程希望大家能認真學習", 50,
                                    "桃園 hawk")
        )
        _shopitems.add(ClassInfo(2, "基礎肌力", "09:00", "12:00", "A503",
                                    500, 5, "2023/06/15", "本課程希望大家能認真學習", 50,
                                    "王曉明")
        )
        shopitems.value = _shopitems
    }



//    TEST DATA
    val sportbigcats : List<SportBigCat> = listOf(
        SportBigCat(1, "有氧", "有氧"),
        SportBigCat(2, "無氧", "肩"),
        SportBigCat(3, "無氧", "胸"),
        SportBigCat(4, "缺氧", "背"),
        SportBigCat(5, "沒氧", "腿")
    )

    val sportcats : List<List<SportCat>> = listOf(

        listOf(
            SportCat(1, 1, "飛輪"),
            SportCat(2, 1, "靜態"),
            SportCat(3, 1, "心肺訓練"),
            SportCat(4, 1, "跑步"),
        ),
        listOf(
            SportCat(5, 2, "槓鈴肩推"),
            SportCat(6, 2, "啞鈴肩推"),
            SportCat(7, 2, "啞鈴側平舉"),
            SportCat(8, 2, "啞鈴前平舉"),
            SportCat(9, 2, "站姿肩推")
        ),
        listOf(
            SportCat(10, 3, "啞鈴握推"),
            SportCat(11, 3, "槓鈴握推"),
            SportCat(12, 3, "蝴蝶機夾胸"),
            SportCat(13, 3, "繩索下斜夾胸"),
            SportCat(14,3, "槓鈴斜上推")
        )

    )
}