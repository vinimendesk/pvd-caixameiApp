package com.example.pvd_caixamei.nav

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.pvd_caixamei.ui.theme.PinkBottomNavigationBar
import com.example.pvd_caixamei.ui.theme.PinkBottomNavigationBarSelected
import com.example.pvd_caixamei.ui.theme.PinkGray

@Composable
fun BottomNavigationBar(
    onTabPressed: (String) -> Unit,
    currentScreen: ScreenType,
    navigationItemContentList: List<NavigationItemContent>,
    modifier: Modifier
) {

    NavigationBar(
        containerColor = Color.White,
        modifier = modifier
    ) {

        navigationItemContentList.forEach { navItem ->
            NavigationBarItem(
                selected = currentScreen == navItem.screenType,
                onClick = { onTabPressed(navItem.screenType.name) },
                icon = {
                        Column(
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally,
                            modifier = Modifier
                                .clip(RoundedCornerShape(12.dp))
                                .background(
                                    color = if (currentScreen == navItem.screenType) PinkBottomNavigationBar
                                    else Color.White
                                )
                                .height(60.dp)
                                .width(80.dp)
                        ) {
                            Icon(
                                imageVector = navItem.icon,
                                tint = if (currentScreen == navItem.screenType) PinkBottomNavigationBarSelected
                                else Color.Gray,
                                contentDescription = navItem.screenType.toString(),
                                modifier = Modifier
                                    .size(25.dp)
                                    .padding(top = 4.dp)
                            )
                            Text(
                                text = navItem.text,
                                color = Color.Gray,
                                fontSize = 9.sp,
                            )
                        }
                },
                colors = NavigationBarItemDefaults.colors(
                    indicatorColor = Color.Transparent,
                    selectedIconColor = PinkBottomNavigationBarSelected,
                    unselectedIconColor = PinkBottomNavigationBar
                )
            )


        }

    }

}