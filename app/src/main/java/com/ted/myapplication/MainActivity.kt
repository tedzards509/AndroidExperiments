package com.ted.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LargeFloatingActionButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import `mipmap-hdpi`.myapplication.ui.theme.AppTheme


class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val mode = remember { mutableStateOf(0) }
            AppTheme(useDarkTheme = true) {
                when (mode.value) {
                    0 -> {val clicks = remember { mutableStateOf(0) }
                        Column(
                            modifier = Modifier.fillMaxSize()
                        ) {
                            TopAppBar (
                                title = { Text(text = "Hello World") },
                                modifier = Modifier.background(MaterialTheme.colorScheme.primaryContainer),
                                navigationIcon = {
                                    IconButton(onClick = { mode.value = 1 }) {
                                        Icon(Icons.Filled.Menu, contentDescription = null)
                                    }
                                },
                            )
                            Surface {
                                Column(
                                    modifier = Modifier
                                        .fillMaxSize()
                                ) {
                                    Row(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(16.dp)
                                    ) {
                                        MyButton(text = "Button 1")
                                    }
                                    Row(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(16.dp)
                                    ) {
                                        MyButton(text = "Button 2")
                                    }
                                    Row(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(16.dp)
                                    ) {
                                        MyButton(text = "Button 3")
                                    }
                                    Row(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(16.dp)
                                    ) {
                                        ClickCounter(clicks, modifier = Modifier.fillMaxWidth())
                                    }
                                }
                                Box(modifier = Modifier
                                    .padding(16.dp)
                                    .fillMaxSize(),
                                    contentAlignment = Alignment.BottomEnd
                                ) {
                                    Floating_Add_10(clicks)
                                }
                            }
                        }
                    }
                    1 -> {
                        ScaffoldTest(colorScheme = MaterialTheme.colorScheme, mode)
                    }
                    else -> {
                        Text(text = "Hello World")
                    }
                }
            }
        }
    }
}

@Composable
fun MyButton(text: String) {
    val (isChecked, setIsChecked) = remember { mutableStateOf(false) }
    val curve = 32.dp
    Card(
        modifier = Modifier
            .height(100.dp)
            .fillMaxWidth(),
        shape = RoundedCornerShape(curve)
    ) {
        Row {
            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .fillMaxWidth(0.7f),
                verticalArrangement = Arrangement.Center
            )
            {
                Text(
                    text = text,
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxWidth(0.7f),
                    fontSize = 6.em
                )
            }
            Row(verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight())
            {
                Switch(
                    checked = isChecked,
                    onCheckedChange = {setIsChecked(it)}
                )
            }

        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScaffoldTest(colorScheme: ColorScheme, mode: MutableState<Int>) {
    val colors = listOf(
        colorScheme.primaryContainer,
        colorScheme.secondaryContainer,
        colorScheme.tertiaryContainer,
        colorScheme.primary,
        colorScheme.secondary,
        colorScheme.tertiary)
    val colorValue = remember { mutableStateListOf<Int>() }
    val itemCount = remember {mutableStateOf(0)}
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Simple Scaffold Screen") },
                navigationIcon = {
                    IconButton(
                        onClick = { mode.value = 0 }
                    ) {
                        Icon(Icons.Filled.Menu, contentDescription = "Localized description")
                    }
                }
            )
        },
        floatingActionButtonPosition = FabPosition.End,
        floatingActionButton = {
            ExtendedFloatingActionButton(
                onClick = {itemCount.value++; colorValue.add(0)}
            ) {
                Icon(Icons.Filled.Add, contentDescription = "Localized description")
                Text("Inc")
            }
        },
        content = { innerPadding ->
            LazyColumn(
                // consume insets as scaffold doesn't do it by default
                // modifier = Modifier.consumeWindowInsets(innerPadding),
                contentPadding = innerPadding
            ) {
                items(count = itemCount.value) {
                    Box(
                        Modifier
                            .fillMaxWidth()
                            .height(150.dp)
                    )
                    {
                        Card (modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
                            colors = CardDefaults.cardColors(
                                containerColor = colors[colorValue[it]%6],
                            ),
                            onClick = {colorValue[it]++; println("incremented colorValue[$it] to ${colorValue[it]}")}) {
                            Box(
                                modifier = Modifier
                                    .fillMaxSize(),
                                contentAlignment = Alignment.Center) {
                                Text("Item $it")
                            }
                        }
                    }
                }
            }
        }
    )
}


@Composable
fun ClickCounter(clicks: MutableState<Int>, modifier: Modifier = Modifier) {
    FilledTonalButton(onClick = {clicks.value++}, modifier = modifier) {
        Text("I've been clicked ${clicks.value} times", fontSize =16.sp)
    }
}

@Composable
fun Floating_Add_10(clicks: MutableState<Int>, modifier: Modifier = Modifier) {
    LargeFloatingActionButton(onClick = {clicks.value += 10}, modifier = modifier) {
        Text("+10", fontSize = 40.sp, fontWeight = FontWeight(900))
    }
}