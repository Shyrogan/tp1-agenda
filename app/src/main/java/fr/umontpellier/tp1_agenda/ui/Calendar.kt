package fr.umontpellier.tp1_agenda.ui

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import fr.umontpellier.tp1_agenda.ui.event.Event
import java.time.LocalDate
import java.time.format.TextStyle
import java.util.*

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CalendarView(events: List<Event>) {
    val currentMonth = LocalDate.now()
    val daysOfWeek =
        (1..7).map { LocalDate.now().withDayOfMonth(it).dayOfWeek.getDisplayName(TextStyle.SHORT, Locale.getDefault()) }
    val daysInMonth = (1..currentMonth.lengthOfMonth()).map { LocalDate.of(currentMonth.year, currentMonth.month, it) }

    val eventsByDay = events.groupBy { LocalDate.ofEpochDay(it.instant.epochSecond / (24 * 60 * 60)) }

    LazyVerticalGrid(
        columns = GridCells.Fixed(7),
        contentPadding = PaddingValues(8.dp),
        modifier = Modifier.fillMaxSize()
    ) {
        // Header row with days of the week
        items(7) { index ->
            Text(
                text = daysOfWeek[index],
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier
                    .padding(4.dp)
                    .fillMaxWidth(),
                textAlign = TextAlign.Center
            )
        }

        // Calendar cells
        items(daysInMonth.size) { index ->
            val day = daysInMonth[index]
            val dayEvents = eventsByDay[day] ?: emptyList()

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(4.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = day.dayOfMonth.toString(),
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.padding(bottom = 4.dp)
                )
                dayEvents.forEach { event ->
                    Text(
                        text = event.name,
                        style = MaterialTheme.typography.bodySmall,
                        modifier = Modifier.padding(bottom = 2.dp)
                    )
                }
            }
        }
    }
}
