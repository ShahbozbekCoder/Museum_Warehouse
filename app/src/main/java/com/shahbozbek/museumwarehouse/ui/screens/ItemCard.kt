package com.shahbozbek.museumwarehouse.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.shahbozbek.museumwarehouse.R
import com.shahbozbek.museumwarehouse.data.local.Items

@Composable
fun ItemCard(
    onClick: () -> Unit,
    myItems: Items
) {
    val imagePainter = if (myItems.image.startsWith("/")) {
        rememberAsyncImagePainter(model = myItems.image)
    } else {
        painterResource(id = myItems.image.toIntOrNull() ?: R.drawable.not_loaded_img)
    }
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Card(
            modifier = Modifier
                .size(width = 240.dp, height = 320.dp)
                .padding(8.dp),
            onClick = {
                onClick()
            },
            shape = CardDefaults.elevatedShape,
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.surfaceVariant
            ),
            border = BorderStroke(1.dp, Color.LightGray),
            elevation = CardDefaults.elevatedCardElevation()
        ) {
            Image(
                painter = imagePainter,
                contentDescription = "Item Image",
                modifier = Modifier.size(width = 240.dp, height = 180.dp)
            )
            Text(
                text = myItems.name,
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                modifier = Modifier.padding(top = 4.dp, start = 8.dp, end = 8.dp),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )
            Text(
                text = if (myItems.status) "Available" else "Not available",
                fontSize = 16.sp,
                color = if (myItems.status) Color.Green else Color.Red,
                modifier = Modifier.padding(top = 4.dp, start = 8.dp, end = 8.dp)
            )
            Text(
                text = myItems.description,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                modifier = Modifier.padding(top = 4.dp, start = 8.dp, end = 8.dp),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )
            Text(
                text = "Price: ${myItems.price}",
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                modifier = Modifier.padding(top = 4.dp, start = 8.dp, end = 8.dp),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ItemScreenPreview() {
    ItemCard(
        onClick = {},
        myItems = Items(
            1,
            "Item 1",
            "Description 1",
            10.0,
            (R.drawable.item1).toString(),
            true
        )
    )
}