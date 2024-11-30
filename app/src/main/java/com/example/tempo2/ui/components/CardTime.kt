package com.example.tempo2.ui.components

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.tempo2.R

@Composable
fun CardTime(
    @StringRes label: Int,
    @StringRes timeResult: Int,
    @StringRes leadingIconDescription: Int,
    @DrawableRes leadingIcon: Int,
    isOutlined: Boolean,
    remainingTimeLayout: String
) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = if(isOutlined) MaterialTheme.colorScheme.outline else MaterialTheme.colorScheme.surfaceVariant
        ),
        modifier = Modifier
            .fillMaxSize()
            .background(
                color = if(isOutlined) MaterialTheme.colorScheme.outline else MaterialTheme.colorScheme.surfaceVariant,
                shape = MaterialTheme.shapes.extraSmall
            )
    ) {
        Row(modifier = Modifier.padding(top = 15.dp, start = 12.dp)) {
            Icon(
                painter = painterResource(id = leadingIcon),
                contentDescription = stringResource(id = leadingIconDescription),
                modifier = Modifier
                    .size(32.dp)
                    .padding(end = 8.dp)
            )
            Text(
                text = stringResource(label),
                modifier = Modifier.padding(top=4.dp)
            )
        }
        Spacer(modifier = Modifier.height(5.dp))
        Text(
            modifier = Modifier.align(Alignment.CenterHorizontally),
            text = if(!isOutlined) stringResource(timeResult, remainingTimeLayout) else "-- : --",
            style = MaterialTheme.typography.displayLarge,
            color = Color(android.graphics.Color.DKGRAY)
        )
        Text(
            modifier = Modifier.align(Alignment.CenterHorizontally),
            text = stringResource(R.string.hours_minutes),
            style = MaterialTheme.typography.bodySmall,
            color = Color(android.graphics.Color.DKGRAY)
        )
        Spacer(modifier = Modifier.height(30.dp))
    }
}