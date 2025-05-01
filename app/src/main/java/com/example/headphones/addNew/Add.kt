package com.example.headphones.addNew

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Handler
import android.os.Looper
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.headphones.MainActivity
import com.example.headphones.R
import com.example.headphones.data.HeadphonesRepository
import com.example.headphones.data.dataClasses.NewHeadphones

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Add(
    viewModel: AddNewViewModel = AddNewViewModel(HeadphonesRepository()),
            onBackArrowClick: () -> Unit,
) {
    val localcontext = LocalContext.current

    val product = remember { mutableStateOf("") }
    val brand = remember { mutableStateOf("") }
    val warranty = remember { mutableStateOf("") }
    val type = remember { mutableStateOf("") }
    val price = remember { mutableDoubleStateOf(0.0) }
    val url = remember { mutableStateOf("") }

    val response by viewModel.insertRessponseLiveData.observeAsState()


    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        modifier = Modifier
                            .padding(8.dp)
                            .fillMaxWidth(),
                        textAlign = TextAlign.Center,
                        text = "Add Form",
                        fontSize = 24.sp,
                    )
                },
                navigationIcon = {
                    IconButton(onClick = onBackArrowClick) {
                        Icon(Icons.Filled.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { values ->

        Column(
            modifier = Modifier
                .padding(values)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(color = Color.White)
                    .padding(16.dp)
                    .verticalScroll(rememberScrollState())
            ) {

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
                    value = url.value,
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
                        localcontext.startActivity(Intent(localcontext, MainActivity::class.java))
                    }
                    Spacer(modifier = Modifier.width(16.dp))
                    AddNewButton {
                        val readyHeadphones: NewHeadphones? = validatedHeadphones(

                            productInput = product.value,
                            brandInput = brand.value,
                            warrantyInput = warranty.value,
                            typeInput = type.value,
                            priceInput = price.value,
                            urlInput = url.value,
                            context = localcontext
                        )
                        if (readyHeadphones != null
                        ) {
                            viewModel.SaveNewItem(
                                readyHeadphones
                            )
                        }
                    }
                }
                Spacer(Modifier.height(16.dp))

            }

            if (response != null) {
                Toast.makeText(
                    localcontext,
                    localcontext.resources.getString(R.string.saved),
                    Toast.LENGTH_SHORT
                ).show()

                if (response!!.status == "OK") {
                    Handler(Looper.getMainLooper()).postDelayed({
                        localcontext.startActivity(
                            Intent(
                                localcontext,
                                MainActivity::class.java
                            )
                        )
                    }, 1000)
                }

            }


        }


    }
}

private fun validatedHeadphones(
    productInput: String?,
    brandInput: String?,
    warrantyInput: String?,
    typeInput: String?,
    priceInput: Double?,
    urlInput: String?,
    context: Context

): NewHeadphones? {

    if (
        productInput.isNullOrEmpty() ||
        brandInput.isNullOrEmpty() ||
        warrantyInput.isNullOrEmpty() ||
        typeInput.isNullOrEmpty() ||
        priceInput == null ||
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
        type = typeInput,
        warranty = warrantyInput,
        price = priceInput,
        url = urlInput,
        )

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


@Composable
fun AddNewButton(onClick: () -> Unit) {

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
            text = "Save"
        )
    }

}

@Preview
@Composable
fun SimpleComposablePreview() {
    //Add()
}