package com.mine.cappella.screens

import android.content.ContentValues.TAG
import android.media.Image
import android.net.Uri
import android.os.Build
import android.util.Log
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.annotation.RequiresExtension
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.rememberAsyncImagePainter
import com.mine.cappella.R
import com.mine.cappella.ui.theme.CappellaTheme
import com.mine.cappella.ui.theme.colorPrimary
import com.mine.cappella.ui.theme.colorSecondary
import com.mine.cappella.ui.theme.onColorPrimary
import com.mine.cappella.ui.theme.poppinsFamily
import com.mine.cappella.viewModels.RegistrationViewModel
import org.koin.androidx.compose.koinViewModel

/**
 * Registration screen main composable
 */
@RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun RegistrationScreen(
    modifier: Modifier = Modifier, onContinueClick: () -> Unit = {},
    snackBarHostState: SnackbarHostState
) {
    // Retrieve the view model using Koin
    val viewModel: RegistrationViewModel = koinViewModel()

    // Collect UI state from the view model
    val baseResult by viewModel.uiStateFlow.collectAsStateWithLifecycle()

    // Retrieve the current context
    val context = LocalContext.current

    // Collect user data state from the view model
    val user by viewModel.uiState.collectAsStateWithLifecycle()

    // Launch effect to handle changes in base result
    LaunchedEffect(key1 = baseResult) {
        when (baseResult) {
            is UiState.Success -> {
                // Update view model with new name data if available
                (baseResult as UiState.Success<RegistrationUiState>).data?.name?.let {
                    viewModel.onNewName(it)
                }
                // Update view model with new date data if available
                (baseResult as UiState.Success<RegistrationUiState>).data?.date?.let {
                    viewModel.onNewDate(it)
                }
                // Update view model with new gender data if available
                (baseResult as UiState.Success<RegistrationUiState>).data?.gender?.let {
                    viewModel.onNewGenderSelected(it)
                }
                // Update view model with new image data if available
                (baseResult as UiState.Success<RegistrationUiState>).data?.imageProfile?.let {
                    viewModel.onNewImage(it)
                }
            }

            is UiState.Error -> {
                Toast.makeText(
                    context,
                    (baseResult as UiState.Error<RegistrationUiState>).error?.asString(context = context),
                    Toast.LENGTH_LONG
                ).show()
            }

            else -> {}
        }
    }

    // Initialize mutable state for image URI
    var imageUri by remember { mutableStateOf<Uri?>(null) }

    // Initialize launcher for gallery activity result
    val galleryLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent(),
        onResult = { uri ->
            uri?.let {
                imageUri = it
            }
        }
    )

    // Composable content for the registration screen
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(onColorPrimary)
    ) {
        // Title of the profile section
        IconWithText(title = stringResource(R.string.profile_title), fontWeight = FontWeight.Bold)

        // Surface to contain the profile information
        Surface(
            modifier = modifier
                .fillMaxSize()
                .padding(16.dp), shape = RoundedCornerShape(24.dp),
            color = Color.White
        ) {
            Column(
                modifier = modifier
                    .padding(16.dp)
                    .background(Color.White)
            ) {
                // Display profile image or placeholder
                if (imageUri == null) {
                    user.imageProfile?.let {
                        RoundedImage(
                            modifier = Modifier.align(Alignment.CenterHorizontally),
                            imageUrl = it
                        )
                    }
                } else {
                    Image(
                        painter = rememberAsyncImagePainter(model = imageUri),
                        contentDescription = null,
                        modifier = Modifier
                            .align(Alignment.CenterHorizontally)
                            .clip(CircleShape)
                            .size(80.dp),
                        contentScale = ContentScale.Crop
                    )
                }

                // Button to update profile image
                IconWithText(
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .clickable {
                            galleryLauncher.launch("image/*")
                        }
                        .padding(top = 16.dp, start = 4.dp, bottom = 16.dp),
                    icon = painterResource(id = R.drawable.vectoredit_pen),
                    title = stringResource(R.string.update_profile_text),
                    fontSize = 14.sp,
                    iconPadding = 2.dp
                )

                // Divider
                Spacer(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(colorSecondary)
                        .height(1.dp)
                )
                // Spacing
                Spacer(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(16.dp)
                )

                // Label for name
                Label(
                    modifier = modifier.padding(top = 16.dp),
                    text = stringResource(R.string.name_nickname),
                    isCompulsory = true
                )

                // Text field for name input
                user?.name?.let { s ->
                    ProfileTextField(
                        modifier = Modifier,
                        onValueChange = { viewModel.onNewName(it) },
                        placeholder = stringResource(R.string.name),
                        value = s
                    )
                }

                // Label for date of birth
                Label(text = stringResource(R.string.date_of_birth))

                // Date picker for selecting date of birth
                user?.date?.let {
                    DatePicker(
                        modifier = modifier,
                        initialDate = it,
                        onValueChange = { s -> viewModel.onNewDate(s) })
                }

                // Label for gender
                Label(text = stringResource(R.string.gender))

                // Gender selector dropdown
                user?.gender?.let {
                    GenderSelector(
                        modifier = modifier,
                        initialText = it,
                        onValueChange = { s -> viewModel.onNewGenderSelected(s) })
                }

                // Button to continue with registration
                Button(
                    onClick = {
                        viewModel.updateProfile()
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 8.dp),
                    shape = CircleShape,
                    colors = ButtonColors(
                        containerColor = colorPrimary,
                        contentColor = Color.White,
                        disabledContainerColor = Color.Gray,
                        disabledContentColor = Color.Black
                    )
                ) {
                    Text(
                        text = stringResource(R.string.continue_text),
                        color = Color.White,
                        fontSize = 18.sp,
                        fontFamily = poppinsFamily,
                        fontWeight = FontWeight.Bold
                    )
                }

                // Skip for now text
                Text(
                    text = stringResource(R.string.skip_for_now),
                    modifier = Modifier
                        .padding(vertical = 16.dp)
                        .align(Alignment.CenterHorizontally),
                    color = colorPrimary,
                    fontSize = 14.sp,
                    fontFamily = poppinsFamily,
                    fontWeight = FontWeight.Black
                )
            }
        }
    }
}


@RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
@RequiresApi(Build.VERSION_CODES.O)
@Preview(showBackground = true)
@Composable
fun RegistrationScreenPreview() {
    CappellaTheme {
        RegistrationScreen(snackBarHostState = SnackbarHostState())
    }
}