package fr.umontpellier.tp1_agenda.ui.event

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import java.time.Instant
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter

private val formatter = DateTimeFormatter
    .ofPattern("dd/MM/uuuu")
    .withZone(ZoneOffset.UTC)

data class Event(val name: String, val instant: Instant)

@Composable
fun EventItem(event: Event, delete: () -> Unit) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .shadow(4.dp), // Ajoute une ombre à la surface
        color = MaterialTheme.colorScheme.surface
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(text = event.name)
                Text(
                    text = formatter.format(event.instant),
                    color = Color.LightGray,
                    modifier = Modifier.padding(top = 4.dp)
                )
            }
            Button(onClick = delete, content = {
                Icon(Icons.Filled.Delete, "Delete")
            })
        }
    }
}
