package com.example.todoapp.features.feature_settings.ui.screen

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.automirrored.filled.Send
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.todoapp.R
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.outlined.DateRange
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.Menu
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.PlayArrow
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.material.icons.outlined.Star

@Composable
fun CategoryItem(title: String, icon: ImageVector, trailing: @Composable (() -> Unit)? = null, onClick: () -> Unit) {
    Surface(
        onClick = onClick,
        shape = MaterialTheme.shapes.medium,
        color = MaterialTheme.colorScheme.surface,
        tonalElevation = 2.dp,
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(icon, contentDescription = null, modifier = Modifier.size(28.dp), tint = MaterialTheme.colorScheme.onSurface)
                Spacer(modifier = Modifier.width(16.dp))
                Text(title, style = MaterialTheme.typography.bodyLarge, color = MaterialTheme.colorScheme.onSurface)
            }
            if (trailing != null) trailing()
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(
    navController: NavController? = null,
    isDarkTheme: Boolean,
    onThemeChange: (Boolean) -> Unit,
    currentLanguage: String,
    onLanguageChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    val isFa = currentLanguage == "fa"
    var showInfoDialog by remember { mutableStateOf(false) }
    CompositionLocalProvider(LocalLayoutDirection provides if (isFa) LayoutDirection.Rtl else LayoutDirection.Ltr) {
        val listState = rememberLazyListState()
        val hasScrolled by remember {
            derivedStateOf { listState.firstVisibleItemScrollOffset > 0 }
        }
        val appBarElevation by animateDpAsState(targetValue = if (hasScrolled) 4.dp else 0.dp)
        Scaffold(
            containerColor = MaterialTheme.colorScheme.surface,
            contentColor = MaterialTheme.colorScheme.onSurface,
            topBar = {
                SmallTopAppBar(
                    title = {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = if (isFa) Arrangement.Start else Arrangement.End,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            if (isFa) {
                                Text(stringResource(R.string.settings), style = MaterialTheme.typography.titleLarge, modifier = Modifier.weight(1f))
                                IconButton(onClick = { showInfoDialog = true }) {
                                    Icon(Icons.Default.Info, contentDescription = "راهنما")
                                }
                            } else {
                                IconButton(onClick = { showInfoDialog = true }) {
                                    Icon(Icons.Default.Info, contentDescription = "Help")
                                }
                                Text(stringResource(R.string.settings), style = MaterialTheme.typography.titleLarge, modifier = Modifier.weight(1f))
                            }
                        }
                    },
                    colors = TopAppBarDefaults.smallTopAppBarColors(
                        containerColor = if (isDarkTheme) {
                            MaterialTheme.colorScheme.surfaceVariant.copy(alpha = if (hasScrolled) 1f else 0f)
                        } else {
                            MaterialTheme.colorScheme.surface
                        },
                    ),
                    modifier = Modifier.shadow(appBarElevation),
                )
            },
        ) { padding ->
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.TopCenter) {
                LazyColumn(
                    contentPadding = padding,
                    modifier = Modifier.widthIn(max = 600.dp)
                        .padding(0.dp,0.dp,0.dp,90.dp),
                    state = listState
                ) {
                    // --- آیتم‌های تنظیمات سفارشی ---
                    item {
                        CategoryItem(
                            title = stringResource(R.string.select_theme),
                            icon = Icons.Outlined.FavoriteBorder,
                            trailing = {
                                Switch(
                                    checked = isDarkTheme,
                                    onCheckedChange = { onThemeChange(it) },
                                    colors = SwitchDefaults.colors(
                                        checkedThumbColor = MaterialTheme.colorScheme.primary,
                                        uncheckedThumbColor = MaterialTheme.colorScheme.surfaceVariant
                                    )
                                )
                            },
                            onClick = { onThemeChange(!isDarkTheme) }
                        )
                    }
                    item {
                        CategoryItem(
                            title = stringResource(R.string.select_language),
                            icon = Icons.Outlined.Person,
                            trailing = {
                                Row {
                                    Text(
                                        text = stringResource(if (isFa) R.string.language_fa else R.string.language_en),
                                        style = MaterialTheme.typography.bodyMedium,
                                        color = MaterialTheme.colorScheme.primary
                                    )
                                }
                            },
                            onClick = {
                                onLanguageChange(if (isFa) "en" else "fa")
                            }
                        )
                    }
                    item {
                        CategoryItem(
                            title = stringResource(R.string.calendar),
                            icon = Icons.Outlined.DateRange,
                            onClick = { /* TODO: نمایش تقویم */ }
                        )
                    }
                    // --- Divider ---
                    item { HorizontalDivider(modifier = Modifier.padding(vertical = 12.dp)) }
                    // --- سایر آیتم‌ها ---
                    item { CategoryItem(title = stringResource(R.string.account), icon = Icons.Outlined.AccountCircle, onClick = { /*TODO*/ }) }
                    item { CategoryItem(title = stringResource(R.string.payment_methods), icon = Icons.Outlined.ShoppingCart, onClick = { /*TODO*/ }) }
                    item { CategoryItem(title = stringResource(R.string.privacy), icon = Icons.Outlined.Lock, onClick = { /*TODO*/ }) }
                    item { CategoryItem(title = stringResource(R.string.notifications), icon = Icons.Outlined.Notifications, onClick = { /*TODO*/ }) }
                    item { CategoryItem(title = stringResource(R.string.look_and_feel), icon = Icons.Outlined.AccountCircle, onClick = { /*TODO*/ }) }
                    item { HorizontalDivider(modifier = Modifier.padding(vertical = 12.dp)) }
                    item { CategoryItem(title = stringResource(R.string.faq), icon = Icons.Outlined.Info, onClick = { /*TODO*/ }) }
                    item { CategoryItem(title = stringResource(R.string.send_feedback), icon = Icons.Outlined.Email, onClick = { /*TODO*/ }) }
                    item { CategoryItem(title = stringResource(R.string.see_whats_new), icon = Icons.Outlined.Star, onClick = { /*TODO*/ }) }
                    item { HorizontalDivider(modifier = Modifier.padding(vertical = 12.dp)) }
                    item { CategoryItem(title = stringResource(R.string.legal), icon = Icons.Outlined.PlayArrow, onClick = { /*TODO*/ }) }
                    item { CategoryItem(title = stringResource(R.string.licenses), icon = Icons.Outlined.Menu, onClick = { /*TODO*/ }) }
                    item { HorizontalDivider(modifier = Modifier.padding(vertical = 12.dp)) }
                    item { AppVersion(versionText = if (isFa) "نسخه ۱.۰.۰" else "Version 1.0.0", copyrights = if (isFa) "© ۲۰۲۴ شرکت شما" else "© 2024 Your Company", onClick = { /* TODO Add easter egg after 8 times is clicked */ }) }
                }
            }
        }
    }
    if (showInfoDialog) {
        AlertDialog(
            onDismissRequest = { showInfoDialog = false },
            title = { Text(if (isFa) "راهنمای این بخش" else "Section Guide") },
            text = { Text(if (isFa) "این بخش برای مدیریت و مشاهده اطلاعات مربوط به این قسمت است." else "This section is for managing and viewing information related to this part.") },
            confirmButton = {
                TextButton(onClick = { showInfoDialog = false }) {
                    Text(if (isFa) "باشه" else "OK")
                }
            }
        )
    }
}

@Composable
fun AppVersion(versionText: String, copyrights: String, onClick: () -> Unit) {

}
