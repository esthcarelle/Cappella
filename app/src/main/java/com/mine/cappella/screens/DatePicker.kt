package com.mine.cappella.screens

import android.app.DatePickerDialog
import android.content.ContentValues.TAG
import android.util.Log
import android.widget.DatePicker
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mine.cappella.R
import com.mine.cappella.ui.theme.colorSecondary
import com.mine.cappella.ui.theme.onTextColor
import com.mine.cappella.ui.theme.poppinsFamily
import java.util.Calendar
import java.util.Date

@Composable
fun DatePicker(modifier: Modifier,initialDate: String,onValueChange: (String) -> Unit = {}){
    // Fetching the Local Context
    val mContext = LocalContext.current

    // Declaring integer values
    // for year, month and day
    val mYear: Int
    val mMonth: Int
    val mDay: Int

    // Initializing a Calendar
    val mCalendar = Calendar.getInstance()

    // Fetching current year, month and day
    mYear = mCalendar.get(Calendar.YEAR)
    mMonth = mCalendar.get(Calendar.MONTH)
    mDay = mCalendar.get(Calendar.DAY_OF_MONTH)

    mCalendar.time = Date()

    // Declaring a string value to
    // store date in string format

    val mDate = remember { mutableStateOf("") }
    mDate.value = initialDate
    Log.e(TAG, "DatePicker: "+ mDate.value)

    // Declaring DatePickerDialog and setting
    // initial values as current values (present year, month and day)
    val mDatePickerDialog = DatePickerDialog(
        mContext,
        { _: DatePicker, mYear: Int, mMonth: Int, mDayOfMonth: Int ->
            mDate.value = "${mMonth + 1}/$mDayOfMonth/$mYear"
            onValueChange.invoke("${mMonth + 1}/$mDayOfMonth/$mYear")
        }, mYear, mMonth, mDay
    )

    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp)
            .background(Color.White)
            .border(
                width = 1.dp,
                color = colorSecondary,
                shape = RoundedCornerShape(10.dp)
            )
            .height(56.dp), horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = mDate.value,
            modifier = modifier
                .padding(16.dp)
                .align(Alignment.CenterVertically),
            color = onTextColor,
            fontSize = 16.sp,
            fontFamily = poppinsFamily,
            fontWeight = FontWeight.Normal,

            )
        Icon(
            modifier = Modifier
                .clickable {
                    mDatePickerDialog.show()
                }
                .align(Alignment.CenterVertically)
                .padding(16.dp),
            painter = painterResource(id = R.drawable.calendar),
            contentDescription = "Calendar Icon"
        )
    }

}