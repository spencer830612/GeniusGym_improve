package com.example.geniusgym.member.model

import java.time.LocalDateTime

data class StoreBean(
    var bh_id : Int = -1,
    var bh_name: String = "緯育分店",
    var bh_address: String = "104506台北市中山區南京東路三段219號5樓",
    var bh_cell: String = "02-27120589",
    var bh_start_time: String = "09:00",
    var bh_ed_time: String = "18:00"
)