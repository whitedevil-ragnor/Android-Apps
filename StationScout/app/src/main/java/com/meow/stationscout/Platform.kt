import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Share
import androidx.compose.ui.text.font.FontWeight
import androidx.navigation.NavController
import com.meow.stationscout.R

@Composable
fun PlatformScreen(navController: NavController, modifier: Modifier = Modifier) {
    // State for text fields
    val currentLocation = remember { mutableStateOf(TextFieldValue("")) }
    val platform = remember { mutableStateOf(TextFieldValue("")) }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(8.dp) // Padding to avoid overlap with other elements
    ) {
        // TextField for Current Location with Leading Icon
        TextField(
            value = currentLocation.value,
            onValueChange = { currentLocation.value = it },
            label = { Text("Current Location") },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.LocationOn,
                    contentDescription = "Location Icon",
                    tint = Color.Red
                )
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp) // Space between text fields
        )
        // TextField for Platform with Leading Icon
        TextField(
            value = platform.value,
            onValueChange = { platform.value = it },
            label = { Text("Platform") },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.LocationOn,
                    contentDescription = "Platform Icon",
                    tint = Color.Blue
                )
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp) // Space between text fields
        )

        // Spacer to add some space between the text fields and the image
        Spacer(modifier = Modifier.height(8.dp))

        // Image with specified height and maximum width
        Image(
            painter = painterResource(id = R.drawable.platform_map), // Replace with your image resource ID
            contentDescription = "Platform Image",
            contentScale = ContentScale.Crop, // Ensures the image fills the width while cropping as needed
            modifier = Modifier
                .fillMaxWidth() // Maximum width
                .height(280.dp) // Fixed height
        )
        Spacer(modifier = Modifier.height(16.dp))
        Divider(
            color = Color.Gray, // You can change the color of the line
            thickness = 2.dp,   // Thickness of the line
            modifier = Modifier.fillMaxWidth() // Ensures the line covers the complete width
        )
        Spacer(modifier = Modifier.height(10.dp))
        Row {
            Text(text = "9 Min", style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold), color = Color.Blue)
            Text(text = " (1.9 Km)",style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),color = Color.Black )// Change color if needed)
        }
        Text(text = "Fastest route",style = MaterialTheme.typography.bodySmall,color = Color.Black )
        Spacer(modifier = Modifier.height(8.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Button(
                onClick = { /* TODO */ },
                modifier = Modifier
                    .weight(1f) // Ensure all buttons are of equal width
                    .padding(4.dp) // External padding for spacing around the button
                    .background(
                        Color(51, 119, 255),
                        shape = RoundedCornerShape(8.dp)
                    ) // Light blue background
                    .padding(horizontal = 8.dp, vertical = 4.dp), // Inner padding for content
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(51, 119, 255) // Light blue color
                ),
                contentPadding = PaddingValues(4.dp) // Padding inside the button
            ) {
                Icon(
                    imageVector = Icons.Default.KeyboardArrowUp,
                    contentDescription = "Home",
                    tint = Color.White,
                    modifier = Modifier.size(20.dp) // Adjust icon size
                )
                Text(
                    text = "Start",
                    color = Color.White,
                    style = MaterialTheme.typography.bodyMedium, // Ensure consistent text size
                    modifier = Modifier.padding(start = 4.dp) // Spacing between the icon and text
                )
            }
            Button(
                onClick = { /* TODO */ },
                modifier = Modifier
                    .weight(1f) // Ensure all buttons are of equal width
                    .padding(4.dp) // External padding for spacing around the button
                    .background(
                        Color(51, 119, 255),
                        shape = RoundedCornerShape(8.dp)
                    ) // Light blue background
                    .padding(horizontal = 8.dp, vertical = 4.dp), // Inner padding for content
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(51, 119, 255) // Light blue color
                ),
                contentPadding = PaddingValues(4.dp) // Padding inside the button
            ) {
                Icon(
                    imageVector = Icons.Default.Share,
                    contentDescription = "Share",
                    tint = Color.White,
                    modifier = Modifier.size(20.dp) // Adjust icon size
                )
                Text(
                    text = "Share",
                    color = Color.White,
                    style = MaterialTheme.typography.bodyMedium, // Ensure consistent text size
                    modifier = Modifier.padding(start = 4.dp) // Spacing between the icon and text
                )
            }
            Button(
                onClick = { /* TODO */ },
                modifier = Modifier
                    .weight(1f) // Ensure all buttons are of equal width
                    .padding(4.dp) // External padding for spacing around the button
                    .background(
                        Color(51, 119, 255),
                        shape = RoundedCornerShape(8.dp)
                    ) // Light blue background
                    .padding(horizontal = 8.dp, vertical = 4.dp), // Inner padding for content
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(51, 119, 255) // Light blue color
                ),
                contentPadding = PaddingValues(4.dp) // Padding inside the button
            ) {
                Icon(
                    imageVector = Icons.Default.Done,
                    contentDescription = "Save",
                    tint = Color.White,
                    modifier = Modifier.size(20.dp) // Adjust icon size
                )
                Text(
                    text = "Save",
                    color = Color.White,
                    style = MaterialTheme.typography.bodyMedium, // Ensure consistent text size
                    modifier = Modifier.padding(start = 4.dp) // Spacing between the icon and text
                )
            }
        }
    }

}
