package com.example.headphones.homePage

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.headphones.R
import com.example.headphones.data.HeadphonesRepository
import com.example.headphones.data.dataClasses.Headphones
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomePage(
    viewModel: HeadphonesListViewModel = HeadphonesListViewModel(HeadphonesRepository()),
    onCardClick: (String) -> Unit = {},
    advertisementsDrawerClick: () -> Unit = {},
) {
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    val headphones by viewModel.headphonesLiveData.observeAsState()
    val onCloseDrawer: () -> Unit = {
        scope.launch {
            drawerState.close()
        }
    }

    val localContext = LocalContext.current

    ModalNavigationDrawer(
        drawerContent = {
            ModalDrawerSheet(
                drawerContainerColor = Color(0xFFF3F3F3)
            ) {
                Spacer(modifier = Modifier.padding(top = 10.dp))
                IconButton(
                    onClick = onCloseDrawer
                ) {
                    Icon(
                        imageVector = Icons.Filled.Close,
                        contentDescription = "Close Drawer",
                        tint = Color.Black
                    )
                }
                Box(modifier = Modifier.fillMaxWidth()) {
                    Image(
                        modifier = Modifier
                            .align(Alignment.Center)
                            .size(width = 180.dp, height = 70.dp)
                            .padding(10.dp),
                        painter = painterResource(id = R.drawable.img_3),
                        contentDescription = "Logo"
                    )
                }
                NavigationDrawerItem(
                    modifier = Modifier.padding(start = 10.dp, end = 10.dp),
                    icon = {
                        Image(
                            modifier = Modifier.size(width = 30.dp, height = 30.dp),
                            painter = painterResource(id = R.drawable.img_2),
                            contentDescription = "My profile"
                        )
                    },
                    label = {
                        Text(
                            fontWeight = FontWeight.Medium,
                            text = "My profile",
                            color = Color.Black,
                        )
                    },
                    colors = NavigationDrawerItemDefaults.colors(
                        unselectedIconColor = Color.White,
                        selectedIconColor = Color.Gray,
                        unselectedTextColor = Color.Black,
                        selectedTextColor = Color.Black
                    ),
                    selected = false,
                    onClick = {
                        Toast.makeText(
                            localContext,
                            localContext.resources.getString(R.string.future_feature),
                            Toast.LENGTH_SHORT
                        ).show()
                    })

                Spacer(modifier = Modifier.padding(3.dp))

                NavigationDrawerItem(
                    modifier = Modifier.padding(start = 10.dp, end = 10.dp),
                    icon = {
                        Image(
                            modifier = Modifier.size(width = 30.dp, height = 30.dp),
                            painter = painterResource(id = R.drawable.img_1),
                            contentDescription = "My Advertisements"
                        )
                    },
                    label = {
                        Text(
                            fontWeight = FontWeight.Medium,
                            text = "My Advertisements",
                            color = Color.Gray,
                        )
                    },
                    colors = NavigationDrawerItemDefaults.colors(
                        unselectedIconColor = Color.White,
                        selectedIconColor = Color.Gray,
                        unselectedTextColor = Color.Gray,
                        selectedTextColor = Color.White
                    ),
                    selected = false,
                    onClick = advertisementsDrawerClick
                )
                Spacer(modifier = Modifier.padding(3.dp))
                NavigationDrawerItem(
                    modifier = Modifier.padding(start = 10.dp, end = 10.dp),
                    icon = {
                        Image(
                            modifier = Modifier.size(width = 30.dp, height = 30.dp),
                            painter = painterResource(id = R.drawable.img_4),
                            contentDescription = "My drafts"
                        )
                    },
                    label = {
                        Text(
                            fontWeight = FontWeight.Medium,
                            text = "My Drafts",
                            color = Color.Gray,
                        )
                    },
                    selected = false,
                    onClick = {
                        Toast.makeText(
                            localContext,
                            localContext.resources.getString(R.string.future_feature),
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                )
                Spacer(modifier = Modifier.padding(3.dp))
                NavigationDrawerItem(
                    modifier = Modifier.padding(start = 10.dp, end = 10.dp),
                    icon = {
                        Image(
                            modifier = Modifier.size(width = 30.dp, height = 30.dp),
                            painter = painterResource(id = R.drawable.img_5),
                            contentDescription = "Basket"
                        )
                    },
                    label = {
                        Text(
                            fontWeight = FontWeight.Medium,
                            text = "Basket",
                            color = Color.Gray,
                        )
                    },
                    selected = false,
                    onClick = {
                        Toast.makeText(
                            localContext,
                            localContext.resources.getString(R.string.future_feature),
                            Toast.LENGTH_SHORT
                        ).show()
                    })
                Spacer(modifier = Modifier.padding(3.dp))
                NavigationDrawerItem(
                    modifier = Modifier.padding(start = 10.dp, end = 10.dp),
                    icon = {
                        Image(
                            modifier = Modifier.size(width = 30.dp, height = 30.dp),
                            painter = painterResource(id = R.drawable.img_6),
                            contentDescription = "Settings"
                        )
                    },
                    label = {
                        Text(
                            fontWeight = FontWeight.Medium,
                            text = "Settings",
                            color = Color.Gray,
                        )
                    },
                    selected = false,
                    onClick = {
                        Toast.makeText(
                            localContext,
                            localContext.resources.getString(R.string.future_feature),
                            Toast.LENGTH_SHORT
                        ).show()
                    })
            }
        },
        drawerState = drawerState
    ) {
        Scaffold(modifier = Modifier.fillMaxSize(),
            topBar = {
                TopAppBar(
                    modifier = Modifier.padding(top = 8.dp),
                    title = {
                        Text(
                            modifier = Modifier
                                .padding(70.dp, 0.dp, 0.dp, 5.dp),
                            text = "mango store",
                            fontFamily = FontFamily.Cursive,
                            fontWeight = FontWeight.Bold,
                            fontSize = 28.sp,
                            color = Color(0xFFD89431),
                        )
                    },
                    navigationIcon = {
                        IconButton(onClick = {
                            scope.launch {
                                drawerState.open()
                            }
                        }) {
                            Icon(
                                painter = painterResource(id = R.drawable.img_7),
                                contentDescription = "Menu",
                                modifier = Modifier.size(30.dp)
                            )
                        }
                    },
                    colors = TopAppBarDefaults.smallTopAppBarColors(
                        containerColor = Color.White, // Background color
                        navigationIconContentColor = Color(0xFFD89431),
                        titleContentColor = Color.White
                    )
                )
            }) { values ->
            if (!headphones.isNullOrEmpty()) {
                LazyVerticalStaggeredGrid(
                    columns = StaggeredGridCells.Fixed(2),
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(values)
                        .border(0.5.dp, color = Color.LightGray, shape = RoundedCornerShape(25.dp))

                ) {
                    items(items = headphones!!) { item ->
                        CardItem(headphones = item, onCardClick)
                    }
                }
            }
        }
    }
}

@Composable
fun CardItem(headphones: Headphones, onCardClick: (String) -> Unit) {
    Card(
        modifier = Modifier
            .width(180.dp)
            .padding(8.dp),
        elevation = CardDefaults.cardElevation(4.dp),
        colors = CardDefaults.cardColors(
            contentColor = Color.Black,
            containerColor = Color.White,
        )
    ) {
        Column(
            modifier = Modifier
                .clickable {
                    onCardClick(headphones.id)
                }
                .padding(8.dp),

            ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(120.dp)
                    .background(Color.LightGray, shape = RoundedCornerShape(8.dp))
            ) {
                ImageCard(url = headphones.url)
                IconButton(
                    onClick = { /* ToDo */ },
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .padding(4.dp)
                        .size(24.dp)
                ) {
                    Icon(
                        Icons.Rounded.Favorite,
                        contentDescription = "Favorite",
                        tint = Color(0xFFF7F1F1),
                        modifier = Modifier.fillMaxSize()
                    )
                }
            }
            Spacer(modifier = Modifier.padding(top = 8.dp))
            productName(product = headphones.product)
            Spacer(modifier = Modifier.padding(top = 8.dp))
            productPrice(price = headphones.price)
        }
    }
}

@Composable
fun ImageCard(url: String? = null) {
    Card(
        shape = RoundedCornerShape(8.dp),
        modifier = Modifier
            .fillMaxSize()
            .height(120.dp)
            .border(1.dp, color = Color.White, shape = RoundedCornerShape(3.dp)),
    ) {
        AsyncImage(
            model = url ?: "",
            placeholder = painterResource(id = R.drawable.placeholder_img),
            error = painterResource(id = R.drawable.placeholder_img),
            contentDescription = "CardImage",
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )
    }
}

@Composable
fun productName(product: String) {
    Text(
        text = product,
        color = Color.Black,
        fontSize = 16.sp,
        textAlign = TextAlign.Center,
        modifier = Modifier.padding(horizontal = 8.dp),
        fontWeight = FontWeight.Bold
    )
}

@Composable
fun productPrice(price: Double) {
    Text(
        text = "$" + price.toString(),
        color = Color.Black,
        fontSize = 16.sp,
        fontFamily = FontFamily.SansSerif,
        modifier = Modifier.padding(start = 5.dp),
        fontWeight = FontWeight.Bold
    )
}

@Preview
@Composable
fun SimpleComposablePreview() {
    HomePage()
}