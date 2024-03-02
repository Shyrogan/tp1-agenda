package fr.umontpellier.tp1_agenda

import AddEventButton
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import fr.umontpellier.tp1_agenda.ui.CalendarView
import fr.umontpellier.tp1_agenda.ui.ChangeMenuButton
import fr.umontpellier.tp1_agenda.ui.event.Event
import fr.umontpellier.tp1_agenda.ui.event.EventList
import fr.umontpellier.tp1_agenda.ui.theme.Theme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Theme {
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    AgendaScreen()
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AgendaScreen() {
    val activities = remember { mutableStateListOf<Event>() }
    var isCalendar by remember { mutableStateOf(false) }

    Column(modifier = Modifier.fillMaxSize()) {
        TopAppBar(title = { Text(text = "Agenda") })
        Box(modifier = Modifier.weight(1f)) {
            if (isCalendar) CalendarView(events = activities)
            else EventList(events = activities)
        }
        AddEventButton(onAdd = {
            activities.add(it)
        })
        ChangeMenuButton(isCalendar, toggle = {
            isCalendar = !isCalendar
        })
    }
}

@Preview(showBackground = true)
@Composable
fun AgendaScreenPreview() {
    Theme {
        AgendaScreen()
    }
}
