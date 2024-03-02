import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import fr.umontpellier.tp1_agenda.ui.event.Event
import java.util.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddEventButton(onAdd: (Event) -> Unit) {
    var isDialogOpen by remember { mutableStateOf(false) }
    var nameField by remember { mutableStateOf(TextFieldValue()) }
    var selectedDate = rememberDatePickerState()

    FloatingActionButton(
        onClick = { isDialogOpen = true },
        modifier = Modifier.padding(16.dp)
    ) {
        Icon(Icons.Filled.Add, contentDescription = "Ajouter un event")
    }

    if (isDialogOpen) {
        AlertDialog(
            onDismissRequest = {
                isDialogOpen = false
                nameField = TextFieldValue()
                selectedDate.setSelection(null)
            },
            title = { Text(text = "Ajoutez un évènement") },
            text = {
                Column {
                    TextField(
                        value = nameField,
                        onValueChange = { nameField = it },
                        label = { Text("Nom de l'évènement") }
                    )
                    DatePicker(
                        state = selectedDate,
                        title = {}, headline = {},
                        showModeToggle = false,
                    )
                }
            },
            confirmButton = {
                Button(
                    onClick = {
                        isDialogOpen = false
                        onAdd(Event(nameField.text, Calendar.getInstance().apply {
                            timeInMillis = selectedDate.selectedDateMillis!!
                        }.toInstant()))
                        nameField = TextFieldValue()
                        selectedDate.setSelection(null)
                    }
                ) {
                    Icon(Icons.Default.Add, "Add")
                }
            },
            dismissButton = {
                Button(
                    onClick = {
                        isDialogOpen = false
                        nameField = TextFieldValue()
                        selectedDate.setSelection(null)
                    }
                ) {
                    Icon(Icons.Default.ArrowBack, "Go back")
                }
            }
        )
    }
}