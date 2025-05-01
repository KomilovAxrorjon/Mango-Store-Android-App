package com.example.headphones.detailedPage

import android.annotation.SuppressLint
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.headphones.R
import com.example.headphones.appvertisements.BrandName
import com.example.headphones.appvertisements.ProductPrice
import com.example.headphones.appvertisements.ProductType
import com.example.headphones.appvertisements.Warranty
import com.example.headphones.data.HeadphonesRepository

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailedItem(
    headphonesId: String,
    viewModel: DetailedViewModel = DetailedViewModel(headphonesId, HeadphonesRepository()),
    onBackArrowClick: () -> Unit,
) {
    val headphones by viewModel.headphonesLiveData.observeAsState()

    if (headphones != null) {
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            containerColor = Color(0xFFFFFFFF),
            topBar = {
                TopAppBar(
                    modifier = Modifier.padding(top = 8.dp),
                    title = {

                        HeadphonesTitle(title = headphones!!.product)
                    },
                    navigationIcon = {

                        IconButton(onClick = onBackArrowClick) {
                            Icon(
                                imageVector = Icons.Default.ArrowBack,
                                contentDescription = "Back"
                            )
                        }

                    }
                )
            }
        ) {values ->

            Column(
                modifier = Modifier
                    .padding(values)
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally

            ) {
                Spacer(modifier = Modifier.padding(top = 10.dp))
                ImageCard2(url = headphones!!.url)
                Spacer(modifier = Modifier.padding(top = 10.dp))

                BrandName(brand = headphones!!.brand)
                Spacer(modifier =Modifier.padding(top = 10.dp))

                Warranty(warranty = headphones!!.warranty)
                Spacer(modifier =Modifier.padding(top = 10.dp))

                ProductType(type = headphones!!.type)
                Spacer(modifier =Modifier.padding(top = 10.dp))

                ProductPrice(price = headphones!!.price)
                Spacer(modifier =Modifier.padding(top = 10.dp))

            }

        }
    }
}

@Composable
fun HeadphonesTitle(title: String) {
    Text(
        text = title,
        color = Color.Black,
        fontSize = 20.sp,
        modifier = Modifier.padding(start = 90.dp),
        maxLines = 1,
        fontWeight = FontWeight.Bold
    )
}


@Composable
fun ImageCard2(url: String? = null) {
    Card(
        shape = RoundedCornerShape(25.dp),
        modifier = Modifier
            .padding(top = 10.dp, bottom = 8.dp, start = 10.dp, end = 10.dp)
            .border(0.5.dp, color = Color.LightGray, shape = RoundedCornerShape(25.dp))
    ) {
        AsyncImage(
            model = url ?: "",
            placeholder = painterResource(id = R.drawable.placeholder_img),
            error = painterResource(id = R.drawable.placeholder_img),
            contentDescription = "The test pic",
            contentScale = ContentScale.Crop,
        )
    }
}
