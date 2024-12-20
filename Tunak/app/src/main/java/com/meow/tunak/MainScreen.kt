package com.meow.tunak


//noinspection UsingMaterialAndMaterial3Libraries
import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomAppBar
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetValue
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.Scaffold
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.ScaffoldState
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.Text
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.rememberModalBottomSheetState
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun MainScreen(){
    val scaffoldState:ScaffoldState= rememberScaffoldState()
    val scope= rememberCoroutineScope()
    val viewModel:MusicViewModel= viewModel()
    val currentScreen=viewModel.currentScreen.value
    val title= remember{ mutableStateOf(currentScreen.title)}
    val showBox= remember { mutableStateOf(false) }
    val isFullScreen by remember { mutableStateOf(false) }
    val modifier=if (isFullScreen) Modifier.fillMaxSize() else Modifier.fillMaxWidth()

    val modalState= rememberModalBottomSheetState(initialValue = ModalBottomSheetValue.Hidden
    , confirmValueChange = {it!=ModalBottomSheetValue.HalfExpanded})
    val roundedCorner=if(isFullScreen) 0.dp else 12.dp

    //to know in which screen you are
    val controller:NavController= rememberNavController()
    val navBackStackEntry by controller.currentBackStackEntryAsState()
    val currentRoute=navBackStackEntry?.destination?.route

    val bottomBar:@Composable ()->Unit={
        if (currentScreen is Screen.DrawerScreen || currentScreen == Screen.BottomScreen.Home){
            BottomAppBar (modifier = Modifier.wrapContentSize().navigationBarsPadding()){
                screensInBottom.forEach{item->
                    val isSelected=currentRoute==item.bRoute
                    val tint=if(isSelected) Color.White else Color.Gray
                    BottomNavigationItem(selected = isSelected,onClick = {
                        controller.navigate(item.bRoute);title.value=item.bTitle},
                        icon = { Icon(contentDescription = item.bTitle, painter = painterResource(item.icon), tint = tint) },
                        label = { Text(item.bTitle, color = tint) }, selectedContentColor = Color.White, unselectedContentColor = Color.Gray)
                }
            }
        }
    }

    ModalBottomSheetLayout(
        sheetContent = {MoreLowerContent(modifier=modifier)},
        sheetState = modalState,
        sheetShape = RoundedCornerShape(topStart = roundedCorner, topEnd = roundedCorner)
    )

    {
        Scaffold(modifier = Modifier.fillMaxWidth().padding(top=40.dp), backgroundColor = Color(137, 196, 244 ),
            bottomBar = bottomBar,
            topBar = {TopAppBar(actions = {
                IconButton(onClick = {
                scope.launch {
                    if (modalState.isVisible){
                        modalState.hide()
                    }else{
                        modalState.show()
                    }
                }}) {
                    Icon(imageVector = Icons.Default.MoreVert, contentDescription = null)
                }
            },
                title = {Text(text = title.value)}, backgroundColor = Color(99, 142, 215),
                navigationIcon = { IconButton(onClick = {
                    //open drawer
                    scope.launch {
                        scaffoldState.drawerState.open()
                    }
                }){
                    Icon(Icons.Default.AccountCircle, contentDescription = null, modifier = Modifier.size(35.dp))
                } }
            )},
            scaffoldState = scaffoldState, drawerContent = {
                Box (modifier = Modifier.fillMaxSize().background(colorResource(R.color.background))){
                    LazyColumn (Modifier.padding(16.dp)){
                        items(screensInDrawer){item ->
                            DrawerItem(selected = currentRoute==item.dRoute,item=item){
                                if (item.dTitle=="Add Account"){
                                    showBox.value=true
                                    scope.launch {
                                        scaffoldState.drawerState.close()
                                    }
                                }else{
                                    title.value=item.dTitle
                                    controller.navigate(item.dRoute)
                                }
                            }
                        }
                    }
                }
            })
        {
            Navigation(navController = controller, viewModel = viewModel,it)
            AlertBox(showBox)
        }

    }



}

@Composable
fun MoreLowerContent(modifier: Modifier){
    Box (Modifier.fillMaxWidth().height(300.dp).background(colorResource(R.color.background))){
        Column (modifier.padding(16.dp), verticalArrangement = Arrangement.SpaceBetween){
            Row(modifier.padding(16.dp)) {
                Icon(modifier = Modifier.padding(end=8.dp), imageVector = Icons.Default.Settings, contentDescription = null)
                Text("Setting", fontSize = 25.sp, color = Color.White)
            }
        }
    }
}

