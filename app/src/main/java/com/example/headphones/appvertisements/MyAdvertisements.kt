package com.example.headphones.appvertisements

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
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
import com.example.headphones.R
import com.example.headphones.data.HeadphonesRepository
import com.example.headphones.data.dataClasses.Headphones
import com.example.headphones.homePage.HeadphonesListViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyAdvertisements(
    viewModel: HeadphonesListViewModel = HeadphonesListViewModel(HeadphonesRepository()),
    onBackArrowClick: () -> Unit,
    onAddClick: () -> Unit,
    onEditClick: (String) -> Unit = {},
    onDeleteClick: (String) -> Unit = {},
){
    val headphones by viewModel.headphonesLiveData.observeAsState()

    if (headphones != null){
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            containerColor = Color.White,
            topBar = {
                TopAppBar( title = {

                    Box(modifier = Modifier.fillMaxSize()) {
                        Row(
                            modifier = Modifier.align(Alignment.CenterStart),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            IconButton(
                                onClick = onBackArrowClick,
                                colors = IconButtonDefaults.iconButtonColors(
                                    contentColor = Color.Black
                                )
                            ) {
                                Icon(
                                    imageVector = Icons.Default.ArrowBack,
                                    contentDescription = "Back"
                                )
                            }
                        }
                        Row(
                            modifier = Modifier.align(Alignment.Center),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                color = Color.Black,
                                fontSize = 20.sp,
                                text = "My Advertisements",
                                fontWeight = FontWeight.SemiBold,
                                textAlign = TextAlign.Center,
                                modifier = Modifier.padding(horizontal = 20.dp)
                            )
                        }
                        Row(
                            modifier = Modifier.align(Alignment.CenterEnd),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            IconButton(
                                onClick = onAddClick,
                                colors = IconButtonDefaults.iconButtonColors(
                                    contentColor = Color.Black
                                )
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Add,
                                    contentDescription = "Add"
                                )
                            }
                        }
                    }
                },
                    colors = TopAppBarDefaults.smallTopAppBarColors(
                        containerColor = Color.White,
                        scrolledContainerColor = Color.White,
                        navigationIconContentColor = Color.Black,
                        titleContentColor = Color.Black
                    )
                )

            }
        ) {values ->
            if (!headphones.isNullOrEmpty()) {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(values)

                ) {
                    items(items = headphones!!) {item ->
                        CardItemList(headphones = item, onEditClick, onDeleteClick)

                    }
                }

            }

        }
    }
}

@Composable
fun CardItemList(
    headphones: Headphones,
    onEditClick: (String) -> Unit,
    onDeleteClick: (String) -> Unit
)
{

    val localcontext = LocalContext.current

    Card(
        shape = RoundedCornerShape(25.dp),
        elevation = CardDefaults.cardElevation(5.dp),
        colors = CardDefaults.cardColors(
            contentColor = Color.Black,
            containerColor = Color.White,
        ),
        modifier = Modifier
            .padding(10.dp)
            .border(1.dp, color = Color.LightGray, shape = RoundedCornerShape(25.dp)),

    ){
        Column{

            Row (
                modifier = Modifier
                    .background(Color.White)
                    .padding(top = 10.dp, bottom = 10.dp)
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ){
                var expanded by remember { mutableStateOf(false) }
                IconButton(
                    onClick = { expanded = true },
                    colors = IconButtonDefaults.iconButtonColors(
                        contentColor = Color.Black
                    )
                ) {
                    Icon(
                        imageVector = Icons.Default.MoreVert,
                        contentDescription = "More options"
                    )
                }

                Spacer(modifier = Modifier.weight(1f))

                Text(
                    color = Color.Black,
                    text = headphones.product,
                    fontSize = 20.sp,
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.weight(1f))

                // Empty IconButton to balance the Row
                IconButton(onClick = {}, enabled = false) {
                    Icon(
                        imageVector = Icons.Default.MoreVert,
                        contentDescription = null,
                        tint = Color.Transparent
                    )
                }

                DropdownMenu(
                    modifier = Modifier.background(Color.LightGray) ,
                    expanded = expanded,
                    onDismissRequest = { expanded = false }
                ) {
                    DropdownMenuItem(
                        text = { Text("Edit") },
                        trailingIcon = { Icon(Icons.Default.Create , contentDescription = "Localized description", tint = Color.White)},
                        onClick = {
                            onEditClick(headphones.id)
                        }
                    )
                    Divider()

                    DropdownMenuItem(
                        text = { Text("Delete") },
                        trailingIcon = { Icon(Icons.Default.Delete, contentDescription = "Localized description", tint = Color.White)},
                        onClick = {
                            onDeleteClick(headphones.id)
                            Toast.makeText(
                                localcontext,
                                localcontext.resources.getString(R.string.deleted_msg),
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    )


                }


            }


            ImageCard1(url = headphones.url)
            Spacer(modifier =Modifier.padding(top = 10.dp))

            ProductName(product = headphones.product)
            Spacer(modifier =Modifier.padding(top = 10.dp))

            BrandName(brand = headphones.brand)
            Spacer(modifier =Modifier.padding(top = 10.dp))

            Warranty(warranty = headphones.warranty)
            Spacer(modifier =Modifier.padding(top = 10.dp))

            ProductType(type = headphones.type)
            Spacer(modifier =Modifier.padding(top = 10.dp))

            ProductPrice(price = headphones.price)
            Spacer(modifier =Modifier.padding(top = 10.dp))

        }




    }
}


@Composable
fun ImageCard1(url: String? = null) {
    Card(
        shape = RoundedCornerShape(25.dp),
        modifier = Modifier
            .padding(top = 10.dp, bottom = 8.dp, start =  8.dp, end = 8.dp)
            .border(0.5.dp, color = Color.LightGray, shape = RoundedCornerShape(25.dp)),
    ) {
        AsyncImage(
            model = url ?: "",
            placeholder = painterResource(id = R.drawable.placeholder_img),
            error = painterResource(id = R.drawable.placeholder_img),
            contentDescription = "The test pic",
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )
    }
}

@Composable
fun ProductName(product: String) {
    Row (modifier = Modifier.padding(start = 25.dp, top = 20.dp),){
        Text(
            text = "Product: ",
            color = Color.Black,
            fontSize = 20.sp,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = product,
            color = Color.Black,
            fontSize = 20.sp,
            textAlign = TextAlign.Center

        )
    }
}


@Composable
fun BrandName(brand: String) {
    Row (modifier = Modifier.padding(start = 25.dp),){
        Text(
            text = "Brand: ",
            color = Color.Black,
            fontSize = 20.sp,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = brand,
            color = Color.Black,
            fontSize = 20.sp,
            textAlign = TextAlign.Center
        )
    }
}

@Composable
fun Warranty(warranty: String) {
    Row (modifier = Modifier.padding(start = 25.dp),){
        Text(
            text = "Warranty: ",
            color = Color.Black,
            fontSize = 20.sp,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = warranty,
            color = Color.Black,
            fontSize = 20.sp,
            textAlign = TextAlign.Center
        )
    }
}
@Composable
fun ProductType(type: String) {
    Row (modifier = Modifier.padding(start = 25.dp),){
        Text(
            text = "Type: ",
            color = Color.Black,
            fontSize = 20.sp,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = type,
            color = Color.Black,
            fontSize = 20.sp,
            textAlign = TextAlign.Center
        )
    }
}

@Composable
fun ProductPrice(price: Double) {
    Row (modifier = Modifier.padding(start = 25.dp, bottom = 25.dp)){
        Text(
            text = "Price:  ",
            color = Color.Black,
            fontSize = 20.sp,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = "$$price",
            color = Color.Black,
            fontSize = 20.sp,
            textAlign = TextAlign.Center
        )
    }

}
