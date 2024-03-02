package fr.umontpellier.tp1_agenda.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.List
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ChangeMenuButton(isCalendar: Boolean, toggle: () -> Unit) {
    FloatingActionButton(
        onClick = toggle,
        modifier = Modifier.padding(16.dp),
    ) {
        Icon(if (isCalendar) Icons.Filled.List else Icons.Filled.DateRange, contentDescription = "Changer de vue")
    }
}