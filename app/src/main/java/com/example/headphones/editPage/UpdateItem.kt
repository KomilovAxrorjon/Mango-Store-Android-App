package com.example.headphones.editPage

import android.content.Context
import android.content.Intent
import android.os.Handler
import android.os.Looper
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.headphones.MainActivity
import com.example.headphones.R
import com.example.headphones.data.HeadphonesRepository
import com.example.headphones.data.dataClasses.NewHeadphones
import com.example.headphones.detailedPage.DetailedViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UpdateItemCard(
    headphonesId:String,
    viewModel: UpdateViewModel = UpdateViewModel(HeadphonesRepository()),
    viewModel1: DetailedViewModel = DetailedViewModel(headphonesId, HeadphonesRepository())

){
    val headphones by viewModel1.headphonesLiveData.observeAsState()

    val localContext = LocalContext.current

    val product = remember { mutableStateOf("") }
    val brand = remember { mutableStateOf("") }
    val warranty = remember { mutableStateOf("") }
    val type = remember { mutableStateOf("") }
    val price = remember { mutableDoubleStateOf(0.0) }
    val url = remember { mutableStateOf("") }

    LaunchedEffect(headphones) {
        headphones?.let {
            product.value = it.product
            brand.value = it.brand
            warranty.value = it.warranty
            type.value = it.type
            price.value = it.price
            url.value = it.url
        }
    }
    val response by viewModel.updateResponseLiveData.observeAsState()

    if (headphones != null) {

        Scaffold(
            modifier = Modifier.fillMaxSize(),
            topBar = {
                TopAppBar(
                    title = {
                        Text(
                            text = "Edit Form",
                            fontSize = 20.sp,
                            textAlign = TextAlign.Center ,
                            fontWeight = FontWeight.SemiBold ,
                            modifier = Modifier.fillMaxWidth()
                        )
                    },
                    navigationIcon = {
                        IconButton(onClick = {
                            localContext.startActivity(Intent(localContext, MainActivity::class.java))
                        }) {
                            Icon(
                                imageVector = Icons.Default.ArrowBack,
                                contentDescription = "Back"
                            )
                        }
                    }
                )
            }
        ) {values->
            Column(
                modifier = Modifier
                    .padding(values)
                    .fillMaxWidth()
                    .background(color = Color.White)
                    .padding(16.dp)
                    .verticalScroll(rememberScrollState())
            ) {

                Card(
                    shape = RoundedCornerShape(25.dp),
                    modifier = Modifier
                        .padding(bottom = 10.dp)
                        .border(0.5.dp, color = Color.LightGray, shape = RoundedCornerShape(25.dp)),
                ) {
                    AsyncImage(
                        model = url!!.value,
                        placeholder = painterResource(id = R.drawable.placeholder_img),
                        error = painterResource(id = R.drawable.placeholder_img),
                        contentDescription = "The test pic",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(250.dp)
                    )
                }


                OutlinedTextField(
                    value = product.value,
                    onValueChange = { product.value = it },
                    label = { Text(
                        text = "Product",
                        color = Color.Gray
                    ) },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(8.dp))
                OutlinedTextField(
                    value = brand.value,
                    onValueChange = { brand.value = it },
                    label = { Text(
                        text = "Brand",
                        color = Color.Gray
                    ) },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(8.dp))
                OutlinedTextField(
                    value = warranty.value,
                    onValueChange = { warranty.value = it },
                    label = { Text(
                        text = "Warranty",
                        color = Color.Gray
                    ) },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(8.dp))
                OutlinedTextField(
                    value = type.value,
                    onValueChange = { type.value = it },
                    label = { Text(
                        text = "Type",
                        color = Color.Gray
                    ) },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(8.dp))
                OutlinedTextField(
                    value = price.value.toString(),
                    onValueChange = { price.value = it.toDouble() },
                    label = { Text(
                        text = "Price",
                        color = Color.Gray
                    ) },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(8.dp))
                OutlinedTextField(
                    value =url.value,
                    onValueChange = { url.value = it },
                    label = { Text(
                        text = "Image URL",
                        color = Color.Gray
                    ) },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(8.dp))

                Row {
                    CancelButton {
                        localContext.startActivity(Intent(localContext, MainActivity::class.java))
                    }
                    Spacer(modifier = Modifier.width(16.dp))
                    Spacer(modifier =Modifier.width(16.dp))
                    UpdateButton {
                        val readyItem: NewHeadphones? = readyItem(

                            productInput = product.value,
                            brandInput = brand.value,
                            warrantyInput = warranty.value,
                            typeInput = type.value,
                            priceInput = price.value,
                            urlInput = url.value,
                            context = localContext
                        )

                        if (readyItem != null
                        ) {
                            viewModel.updateItem(
                                headphones!!.id,
                                readyItem
                            )


                        }
                    }
                }
                if (response != null) {

                    if (response!!.status == "OK") {
                        Toast.makeText(
                            localContext,
                            localContext.resources.getString(R.string.updated),
                            Toast.LENGTH_SHORT
                        ).show()
                        // Delay for 1 second before navigating
                        Handler(Looper.getMainLooper()).postDelayed({
                            localContext.startActivity(Intent(localContext, MainActivity::class.java))
                        }, 1000)
                    }

                }

            }

        }

    }
}


private fun readyItem(
    productInput: String?,
    brandInput: String?,
    warrantyInput: String?,
    typeInput: String?,
    priceInput: Double,
    urlInput: String?,
    context: Context

): NewHeadphones? {


    if (
        productInput.isNullOrEmpty() ||
        brandInput.isNullOrEmpty() ||
        warrantyInput.isNullOrEmpty()||
        typeInput.isNullOrEmpty() ||
        priceInput ==0.0||
        urlInput.isNullOrEmpty()
    ) {
        Toast.makeText(
            context,
            context.resources.getString(R.string.fields_compulsory_warning),
            Toast.LENGTH_SHORT
        ).show()
        return null
    }

    return NewHeadphones(
        product = productInput,
        brand = brandInput,
        warranty = warrantyInput,
        type = typeInput,
        price = priceInput,
        url = urlInput,
    )


}

@Composable
fun UpdateButton(onClick: () -> Unit) {

    Button(
        onClick = {
            onClick()
        },
        modifier = Modifier
            .width(195.dp)
            .height(85.dp)
            .padding(vertical = 16.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color(0xFF000000),
            contentColor = Color.White
        )

    ) {
        Text(
            fontSize = 16.sp,
            text = "Update"
        )
    }

}

@Composable
private fun CancelButton(onClick: () -> Unit) {
    Button(
        onClick = {
            onClick()
        },
        modifier = Modifier
            .width(175.dp)
            .height(85.dp)
            .padding(vertical = 16.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.LightGray,
            contentColor = Color.White

        )

    ) {
        Text(
            fontSize = 16.sp,
            text = "Cancel"
        )
    }
}