package com.example.geniusgym.member.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.geniusgym.member.model.WorkoutSmallItem

class MeTrainingMuscleSmallViewModel : ViewModel() {
    val items: MutableLiveData<List<WorkoutSmallItem>> by lazy { MutableLiveData<List<WorkoutSmallItem>>() }
    val item: MutableLiveData<WorkoutSmallItem> by lazy { MutableLiveData<WorkoutSmallItem>() }
    private var itemList = listOf<WorkoutSmallItem>()

    fun load(workoutSmallItem: List<WorkoutSmallItem>?, id: String) {
        val strengthSmallList = mutableListOf<WorkoutSmallItem>()

        strengthSmallList.add(WorkoutSmallItem("1", "1", "槓鈴肩推"))
        strengthSmallList.add(WorkoutSmallItem("1", "2", "啞鈴肩推"))
        strengthSmallList.add(WorkoutSmallItem("1", "3", "啞鈴側平舉"))
        strengthSmallList.add(WorkoutSmallItem("1", "4", "啞鈴前平舉"))
        strengthSmallList.add(WorkoutSmallItem("1", "5", "站姿肩推"))
        strengthSmallList.add(WorkoutSmallItem("1", "6", "器械式側平舉"))
        strengthSmallList.add(WorkoutSmallItem("1", "7", "器械反向飛鳥"))
        strengthSmallList.add(WorkoutSmallItem("1", "8", "臉拉"))

        strengthSmallList.add(WorkoutSmallItem("2", "1", "槓鈴臥推"))
        strengthSmallList.add(WorkoutSmallItem("2", "2", "槓鈴斜上推"))
        strengthSmallList.add(WorkoutSmallItem("2", "3", "啞鈴臥推"))
        strengthSmallList.add(WorkoutSmallItem("2", "4", "啞鈴斜上推"))
        strengthSmallList.add(WorkoutSmallItem("2", "5", "上胸史密斯"))
        strengthSmallList.add(WorkoutSmallItem("2", "6", "蝴蝶機夾胸"))
        strengthSmallList.add(WorkoutSmallItem("2", "7", "繩索飛鳥夾胸"))
        strengthSmallList.add(WorkoutSmallItem("2", "8", "繩索下斜夾胸"))

        strengthSmallList.add(WorkoutSmallItem("3", "1", "引體向上"))
        strengthSmallList.add(WorkoutSmallItem("3", "2", "啞鈴單邊划船"))
        strengthSmallList.add(WorkoutSmallItem("3", "3", "坐姿划船機"))
        strengthSmallList.add(WorkoutSmallItem("3", "4", "滑輪背部下拉"))
        strengthSmallList.add(WorkoutSmallItem("3", "5", "滑輪反向背部下拉"))
        strengthSmallList.add(WorkoutSmallItem("3", "6", "單邊軀體划船"))
        strengthSmallList.add(WorkoutSmallItem("3", "7", "輔助引體向上"))
        strengthSmallList.add(WorkoutSmallItem("3", "8", "背部TYW"))

        strengthSmallList.add(WorkoutSmallItem("4", "1", "槓鈴硬舉"))
        strengthSmallList.add(WorkoutSmallItem("4", "2", "相撲硬舉"))
        strengthSmallList.add(WorkoutSmallItem("4", "3", "SSB桿深蹲"))
        strengthSmallList.add(WorkoutSmallItem("4", "4", "SSB桿保加蹲"))
        strengthSmallList.add(WorkoutSmallItem("4", "5", "啞鈴保加利亞蹲"))
        strengthSmallList.add(WorkoutSmallItem("4", "6", "槓鈴深蹲"))
        strengthSmallList.add(WorkoutSmallItem("4", "7", "腿推機"))
        strengthSmallList.add(WorkoutSmallItem("4", "8", "單腳腿推"))

        strengthSmallList.add(WorkoutSmallItem("5", "1", "壺鈴硬舉"))
        strengthSmallList.add(WorkoutSmallItem("5", "2", "單邊臀部伸展訓練機"))
        strengthSmallList.add(WorkoutSmallItem("5", "3", "單邊臀部訓練機"))
        strengthSmallList.add(WorkoutSmallItem("5", "4", "臀推機"))

        strengthSmallList.add(WorkoutSmallItem("6", "1", "將軍椅"))
        strengthSmallList.add(WorkoutSmallItem("6", "2", "側腹肌訓練架"))
        strengthSmallList.add(WorkoutSmallItem("6", "3", "腹部訓練機"))
        strengthSmallList.add(WorkoutSmallItem("6", "4", "可調式下斜腹部訓練椅"))
        strengthSmallList.add(WorkoutSmallItem("6", "5", "上腹訓練機"))

        strengthSmallList.add(WorkoutSmallItem("7", "1", "熊爬"))
        strengthSmallList.add(WorkoutSmallItem("7", "2", "死蟲"))
        strengthSmallList.add(WorkoutSmallItem("7", "3", "農夫走路"))
        strengthSmallList.add(WorkoutSmallItem("7", "4", "鳥狗式"))
        strengthSmallList.add(WorkoutSmallItem("7", "5", "毛毛蟲"))
        strengthSmallList.add(WorkoutSmallItem("7", "6", "棒式"))
        strengthSmallList.add(WorkoutSmallItem("7", "7", "側棒式"))

        strengthSmallList.add(WorkoutSmallItem("8", "1", "槓鈴站姿彎舉"))
        strengthSmallList.add(WorkoutSmallItem("8", "2", "啞鈴彎舉"))
        strengthSmallList.add(WorkoutSmallItem("8", "3", "斜板彎舉"))
        strengthSmallList.add(WorkoutSmallItem("8", "4", "槌式彎舉"))
        strengthSmallList.add(WorkoutSmallItem("8", "5", "窄握臥推"))
        strengthSmallList.add(WorkoutSmallItem("8", "6", "三頭滑輪下壓"))
        strengthSmallList.add(WorkoutSmallItem("8", "7", "過頭三頭屈伸"))
        strengthSmallList.add(WorkoutSmallItem("8", "8", "Bench Dip"))

        this.items.value = strengthSmallList
    }
}