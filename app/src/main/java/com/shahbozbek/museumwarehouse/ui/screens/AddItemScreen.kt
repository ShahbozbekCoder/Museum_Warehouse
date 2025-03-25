package com.shahbozbek.museumwarehouse.ui.screens

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.net.toUri
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.shahbozbek.museumwarehouse.R
import com.shahbozbek.museumwarehouse.data.local.Items
import java.io.File
import java.io.FileOutputStream

@Composable
fun AddItemScreen(
    navController: NavController,
    mainScreenViewModel: MainScreenViewModel,
    items: Items? = null
) {
    val scrollState = rememberScrollState()
    val onCheckedChange = remember {
        mutableStateOf(items?.status ?: true)
    }
    val itemName = remember {
        mutableStateOf(items?.name ?: "")
    }
    val itemDescription = remember {
        mutableStateOf(items?.description ?: "")
    }
    val itemPrice = remember {
        mutableStateOf(if (items?.price != null) items.price.toInt().toString() else "")
    }
    val imageUriState = remember { mutableStateOf(items?.image?.toUri() ?: Uri.EMPTY) }
    val imageUri by mainScreenViewModel.imageUri.collectAsState()

    LaunchedEffect(imageUri) {
        if (imageUri != null && imageUri.toString().isNotEmpty()) {
            imageUriState.value = imageUri
        }
    }

    val context = LocalContext.current

    val cameraLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.TakePicturePreview(),
    ) { bitmap ->
        bitmap?.let {
            val savedUri = mainScreenViewModel.saveImage(it, context)
            mainScreenViewModel.setImageUri(savedUri.toUri())
        }

    }

    val galleryLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri ->
        uri?.let {
            val bitmap = it.toBitmap(context)
            bitmap?.let { img ->
                val savedUri = mainScreenViewModel.saveImage(img, context)
                mainScreenViewModel.setImageUri(savedUri.toUri())
            }
        }
    }

    val requestPermissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        if (isGranted) {
            cameraLauncher.launch(null)
        } else {
            Toast.makeText(context, "Camera permission denied", Toast.LENGTH_SHORT).show()
        }
    }

    val showDialog = remember { mutableStateOf(false) }

    if (showDialog.value) {
        AlertDialog(
            onDismissRequest = { showDialog.value = false },

            title = { Text(text = "Select image source") },

            text = {
                Text(text = "Choose the source for the image")
            },
            confirmButton = {
                Button(onClick = {
                    showDialog.value = false
                    val cameraPermission = android.Manifest.permission.CAMERA
                    val permissionGranted = context.checkSelfPermission(cameraPermission) == android.content.pm.PackageManager.PERMISSION_GRANTED
                    if (permissionGranted) {
                        cameraLauncher.launch(null)
                    } else {
                        requestPermissionLauncher.launch(cameraPermission)
                    }
                }) {
                    Text(text = "Camera")
                }
            },
            dismissButton = {
                Button(onClick = {
                    showDialog.value = false
                    galleryLauncher.launch("image/*")
                }) {
                    Text(text = "Gallery")
                }
            },
        )
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(32.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(
                onClick = { navController.popBackStack() },
                modifier = Modifier.padding(8.dp)
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Back",
                    tint = Color.Black,
                    modifier = Modifier.size(36.dp)
                )
            }
            Text(
                text = if (items == null) "Add museum item" else "Edit museum item",
                modifier = Modifier.padding(16.dp),
                color = Color.Black,
                style = MaterialTheme.typography.titleLarge,
                fontSize = 32.sp,
                textAlign = TextAlign.Center
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "Item image",
            modifier = Modifier
                .align(Alignment.Start)
                .padding(start = 16.dp),
            fontSize = 18.sp
        )
        Image(
            painter = if (imageUriState.value.toString().isNotEmpty())
                rememberAsyncImagePainter(imageUriState.value)
            else painterResource(
                id = R.drawable.not_loaded_img
            ),
            contentDescription = "Item 1",
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp, start = 16.dp, end = 16.dp, top = 8.dp)
                .height(180.dp)
                .background(Color.LightGray)
                .clickable {
                    showDialog.value = true
                },
            alignment = Alignment.Center,
        )
        Text(
            text = "Item name",
            modifier = Modifier
                .align(Alignment.Start)
                .padding(start = 16.dp),
            fontSize = 18.sp
        )
        OutlinedTextField(
            value = itemName.value,
            onValueChange = { itemName.value = it },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            singleLine = true,
            maxLines = 1,
            placeholder = { Text(text = "Name") },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                capitalization = KeyboardCapitalization.Sentences
            )
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "Item description",
            modifier = Modifier
                .align(Alignment.Start)
                .padding(start = 16.dp),
            fontSize = 18.sp
        )
        OutlinedTextField(
            value = itemDescription.value,
            onValueChange = { itemDescription.value = it },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            placeholder = { Text(text = "Description") },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                capitalization = KeyboardCapitalization.Sentences
            )
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "Item price",
            modifier = Modifier
                .align(Alignment.Start)
                .padding(start = 16.dp),
            fontSize = 18.sp
        )
        OutlinedTextField(
            value = itemPrice.value,
            onValueChange = { itemPrice.value = it },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            singleLine = true,
            maxLines = 1,
            placeholder = { Text(text = "Price") },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number
            )
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "Item status",
            modifier = Modifier
                .align(Alignment.Start)
                .padding(start = 16.dp),
            fontSize = 18.sp
        )
        Switch(
            checked = onCheckedChange.value,
            onCheckedChange = {
                onCheckedChange.value = it
            },
            modifier = Modifier
                .align(Alignment.Start)
                .padding(start = 16.dp)
        )
        Spacer(modifier = Modifier.weight(1f))
        Button(
            onClick = {
                val newItems = Items(
                    id = items?.id ?: 0,
                    name = itemName.value,
                    description = itemDescription.value,
                    price = if (itemPrice.value.isEmpty()) 0.0 else itemPrice.value.toDouble(),
                    image = imageUri?.toString().takeIf { !it.isNullOrEmpty() } ?: items?.image ?: "",
                    status = onCheckedChange.value
                )
                if (newItems.name.isEmpty() || newItems.description.isEmpty() || newItems.price == null || newItems.image.isEmpty()) {
                    Toast.makeText(context, "Please fill all fields", Toast.LENGTH_SHORT).show()
                    return@Button
                }
                if (items == null) {
                    mainScreenViewModel.insert(newItems)
                } else {
                    mainScreenViewModel.update(newItems)
                }
                navController.popBackStack()
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(text = if (items == null) "Save" else "Update")
        }
        Spacer(modifier = Modifier.height(32.dp))
    }
}

fun saveImageToInternalStorage(bitmap: Bitmap, context: Context): String {
    val file = File(context.cacheDir, "${System.currentTimeMillis()}.jpg")
    FileOutputStream(file).use { outputStream ->
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream)
    }
    return file.absolutePath
}

fun Uri.toBitmap(context: Context): Bitmap? {
    return context.contentResolver.openInputStream(this)?.use { inputStream ->
        BitmapFactory.decodeStream(inputStream)
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun AddItemScreenPreview() {
    AddItemScreen(
        navController = NavController(LocalContext.current),
        mainScreenViewModel = hiltViewModel(),
        items = null
    )
}