package fr.umontpellier.tp1_agenda

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import fr.umontpellier.tp1_agenda.ui.event.EventList
import fr.umontpellier.tp1_agenda.ui.theme.Theme
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter
import java.util.*


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
    val activities = remember { mutableStateListOf<String>() }

    Column(modifier = Modifier.fillMaxSize()) {
        TopAppBar(title = { Text(text = "Agenda") })
        Box(modifier = Modifier.weight(1f)) {
            EventList(events = activities)
        }
        AddEventButton(onAddEventClick = { title, date ->
            activities.add("$title le $date")
        })
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddEventButton(onAddEventClick: (String, String) -> Unit) {
    val formatter = DateTimeFormatter
        .ofPattern("dd-MM-uuuu")
        .withZone(ZoneOffset.UTC)
    var showDialog by remember { mutableStateOf(false) }
    var textFieldValue by remember { mutableStateOf(TextFieldValue()) }
    var selectedDate = rememberDatePickerState()

    FloatingActionButton(
        onClick = { showDialog = true },
        modifier = Modifier.padding(16.dp)
    ) {
        Icon(Icons.Filled.Add, contentDescription = "Add Event")
    }

    if (showDialog) {
        AlertDialog(
            onDismissRequest = {
                showDialog = false
                textFieldValue = TextFieldValue()
                selectedDate.setSelection(null)
            },
            title = { Text(text = "Add Event") },
            text = {
                Column {
                    TextField(
                        value = textFieldValue,
                        onValueChange = { textFieldValue = it },
                        label = { Text("Event Name") }
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    DatePicker(state = selectedDate, title = {}, headline = {})
                }
            },
            confirmButton = {
                Button(
                    onClick = {
                        showDialog = false
                        onAddEventClick(
                            textFieldValue.text,
                            formatter.format(Calendar.getInstance().apply {
                                timeInMillis = selectedDate.selectedDateMillis!!
                            }.toInstant())
                        )
                        textFieldValue = TextFieldValue()
                        selectedDate.setSelection(null)
                    }
                ) {
                    Text("Add")
                }
            },
            dismissButton = {
                Button(
                    onClick = {
                        showDialog = false
                        textFieldValue = TextFieldValue()
                        selectedDate.setSelection(null)
                    }
                ) {
                    Text("Cancel")
                }
            }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun AgendaScreenPreview() {
    Theme {
        AgendaScreen()
    }
}
