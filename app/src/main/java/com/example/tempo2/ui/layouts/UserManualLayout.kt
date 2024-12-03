package com.example.tempo2.ui.layouts

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.tempo2.R
import com.example.tempo2.ui.components.TopBar

@Composable
fun UserManualLayout(navigateApp:() -> Unit) {

    var currentPage by remember { mutableIntStateOf(1) }
    val totalPages = 6

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopBar(
                title = R.string.manual_title,
                icon = R.drawable.close,
                descr_icon = R.string.cont_descrp_close_icon,
                onIconClick = navigateApp
            )
        }
    ) { innerPadding ->
            Surface(
                modifier = Modifier
                    .padding(innerPadding)
                    .background(MaterialTheme.colorScheme.tertiaryContainer),
                color = MaterialTheme.colorScheme.background
            ) {
                ManualPage(
                    isBackEnabled = currentPage > 1,
                    isNextEnabled = currentPage < totalPages,
                    onNextClick = {
                        if (currentPage < totalPages) currentPage++
                    },
                    onBackClick = {
                        if (currentPage > 1) currentPage--
                    },
                    img = when (currentPage) {
                        1 -> R.drawable.manual_img_1
                        2 -> R.drawable.manual_img_2
                        3 -> R.drawable.manual_img_3
                        4 -> R.drawable.manual_img_4
                        5 -> R.drawable.manual_img_5
                        else -> R.drawable.manual_img_6
                    },
                    descr_img = when(currentPage) {
                        1 -> R.string.cont_descrp_img_manual_1
                        2 -> R.string.cont_descrp_img_manual_2
                        3 -> R.string.cont_descrp_img_manual_3
                        4 -> R.string.cont_descrp_img_manual_4
                        5 -> R.string.cont_descrp_img_manual_5
                        else -> R.string.cont_descrp_img_manual_6
                    },
                    title = when(currentPage) {
                        1 -> R.string.title_1
                        2 -> R.string.title_2
                        3 -> R.string.title_3
                        4 -> R.string.title_4
                        5 -> R.string.title_5
                        else -> R.string.title_6
                    },
                    text = when(currentPage) {
                        1 -> R.string.text_1
                        2 -> R.string.text_2
                        3 -> R.string.text_3
                        4 -> R.string.text_4
                        5 -> R.string.text_5
                        else -> R.string.text_6
                    }
                )
            }
        }
}

@Composable
fun ManualPage(
    @DrawableRes img: Int,
    @StringRes descr_img: Int,
    @StringRes title: Int,
    @StringRes text: Int,
    isBackEnabled: Boolean,
    isNextEnabled: Boolean,
    onNextClick: () -> Unit,
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier
){
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 25.dp)
    ) {
        Spacer(modifier = Modifier.height(8.dp))
        Image(
            painter = painterResource(img),
            contentDescription = stringResource(descr_img),
            modifier = Modifier
                .size(240.dp)
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = stringResource(title),
            style = MaterialTheme.typography.headlineLarge
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = stringResource(text),
            style = MaterialTheme.typography.bodyMedium,
            textAlign = TextAlign.Justify
        )
        Spacer(modifier = Modifier.weight(1f))
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(horizontal = 24.dp, vertical = 18.dp)
                .fillMaxWidth()
        ) {
            Button(
                onClick = onBackClick,
                enabled = isBackEnabled,
            ){
                Image(
                    painter = painterResource(R.drawable.arrow_left),
                    contentDescription = stringResource(R.string.cont_descrp_back_icon),
                    modifier = Modifier.size(28.dp)
                )
            }
            Button(
                onClick = onNextClick,
                enabled = isNextEnabled
            ){
                Image(
                    painter = painterResource(R.drawable.arrow_right),
                    contentDescription = stringResource(R.string.cont_descrp_next_icon),
                    modifier = Modifier.size(28.dp)
                )
            }
        }
    }
}