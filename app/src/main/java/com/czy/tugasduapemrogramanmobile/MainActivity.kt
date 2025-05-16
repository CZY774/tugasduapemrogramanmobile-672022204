package com.czy.tugasduapemrogramanmobile

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.czy.tugasduapemrogramanmobile.ui.theme.TugasDuaPemrogramanMobileTheme

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TugasDuaPemrogramanMobileTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    RegisterScreen()
                }
            }
        }
//        setContentView(R.layout.activity_main)
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
//            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
//            insets
//        }
    }
}

@Composable
fun RegisterScreen() {
    var fullName by remember { mutableStateOf("") }
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }
    var confirmPasswordVisible by remember { mutableStateOf(false) }

    var fullNameError by remember { mutableStateOf("") }
    var usernameError by remember { mutableStateOf("") }
    var passwordError by remember { mutableStateOf("") }
    var confirmPasswordError by remember { mutableStateOf("") }

    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Register",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 4.dp)
        )

        Spacer(modifier = Modifier.height(4.dp))

        Text(
            text = "Create an account to get started",
            fontSize = 16.sp,
            modifier = Modifier.padding(bottom = 32.dp)
        )

        // Full Name Field
        OutlinedTextField(
            value = fullName,
            onValueChange = {
                fullName = it
                fullNameError = ""
            },
            label = { Text("Full Name *") },
            leadingIcon = { Icon(Icons.Filled.Person, contentDescription = "Full Name") },
            isError = fullNameError.isNotEmpty(),
            supportingText = {
                if (fullNameError.isNotEmpty()) {
                    Text(text = fullNameError, color = MaterialTheme.colorScheme.error)
                }
            },
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next)
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Username Field
        OutlinedTextField(
            value = username,
            onValueChange = {
                username = it
                usernameError = ""
            },
            label = { Text("Username *") },
            leadingIcon = { Icon(Icons.Filled.Email, contentDescription = "Username") },
            isError = usernameError.isNotEmpty(),
            supportingText = {
                if (usernameError.isNotEmpty()) {
                    Text(text = usernameError, color = MaterialTheme.colorScheme.error)
                }
            },
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next)
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Password Field
        OutlinedTextField(
            value = password,
            onValueChange = {
                password = it
                passwordError = ""
            },
            label = { Text("Password *") },
            leadingIcon = { Icon(Icons.Filled.Lock, contentDescription = "Password") },
            trailingIcon = {
                IconButton(onClick = { passwordVisible = !passwordVisible }) {
                    Icon(
                        imageVector = if (passwordVisible) Icons.Filled.VisibilityOff else Icons.Filled.Visibility,
                        contentDescription = if (passwordVisible) "Hide password" else "Show password"
                    )
                }
            },
            visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
            isError = passwordError.isNotEmpty(),
            supportingText = {
                if (passwordError.isNotEmpty()) {
                    Text(text = passwordError, color = MaterialTheme.colorScheme.error)
                }
            },
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password,
                imeAction = ImeAction.Next
            )
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Confirm Password Field
        OutlinedTextField(
            value = confirmPassword,
            onValueChange = {
                confirmPassword = it
                confirmPasswordError = ""
            },
            label = { Text("Confirm Password *") },
            leadingIcon = { Icon(Icons.Filled.Lock, contentDescription = "Confirm Password") },
            trailingIcon = {
                IconButton(onClick = { confirmPasswordVisible = !confirmPasswordVisible }) {
                    Icon(
                        imageVector = if (confirmPasswordVisible) Icons.Filled.VisibilityOff else Icons.Filled.Visibility,
                        contentDescription = if (confirmPasswordVisible) "Hide password" else "Show password"
                    )
                }
            },
            visualTransformation = if (confirmPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
            isError = confirmPasswordError.isNotEmpty(),
            supportingText = {
                if (confirmPasswordError.isNotEmpty()) {
                    Text(text = confirmPasswordError, color = MaterialTheme.colorScheme.error)
                }
            },
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password,
                imeAction = ImeAction.Done
            )
        )

        Spacer(modifier = Modifier.height(32.dp))

        // Confirm Button
        Button(
            onClick = {
                // Validate fields
                var isValid = true

                if (fullName.isEmpty()) {
                    fullNameError = "Full name is required"
                    isValid = false
                }

                if (username.isEmpty()) {
                    usernameError = "Username is required"
                    isValid = false
                }

                if (password.isEmpty()) {
                    passwordError = "Password is required"
                    isValid = false
                } else if (password.length < 6) {
                    passwordError = "Password must be at least 6 characters"
                    isValid = false
                }

                if (confirmPassword.isEmpty()) {
                    confirmPasswordError = "Please confirm your password"
                    isValid = false
                } else if (confirmPassword != password) {
                    confirmPasswordError = "Passwords do not match"
                    isValid = false
                }

                if (isValid) {
                    // Success
                    val intent = Intent(context, LoginActivity::class.java)
                    intent.putExtra("FULLNAME", fullName)
                    intent.putExtra("USERNAME", username)
                    intent.putExtra("PASSWORD", password)
                    context.startActivity(intent)
                    Toast.makeText(context, "Registration successful!", Toast.LENGTH_SHORT).show()

                    // Clear all fields
                    fullName = ""
                    username = ""
                    password = ""
                    confirmPassword = ""

                    // Finish the activity
                    (context as MainActivity).finish()
                } else {
                    // Error
                    Toast.makeText(context, "Please fix the errors in the form", Toast.LENGTH_SHORT).show()
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
        ) {
            Text("REGISTER", fontSize = 16.sp)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun RegisterScreenPreview() {
    TugasDuaPemrogramanMobileTheme {
        RegisterScreen()
    }
}