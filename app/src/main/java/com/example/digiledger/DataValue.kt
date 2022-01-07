package com.example.digiledger

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.math.BigDecimal

@Parcelize
data class DataValue(val amount: BigDecimal): Parcelable{
}