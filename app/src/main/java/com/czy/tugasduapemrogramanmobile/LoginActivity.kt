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
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.czy.tugasduapemrogramanmobile.ui.theme.TugasDuaPemrogramanMobileTheme

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        // Receive fields from intent
        val receivedFullName = intent.getStringExtra("FULLNAME") ?: ""
        val receivedUsername = intent.getStringExtra("USERNAME") ?: ""
        val receivedPassword = intent.getStringExtra("PASSWORD") ?: ""

        setContent {
            TugasDuaPemrogramanMobileTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    LoginScreen(receivedFullName, receivedUsername, receivedPassword)
                }
            }
        }

//        setContentView(R.layout.activity_login)
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
//            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
//            insets
//        }
    }
}

@Composable
fun LoginScreen(receivedFullName: String, receivedUsername: String, receivedPassword: String) {
    val fullName by remember { mutableStateOf(receivedFullName) }
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }

    var usernameError by remember { mutableStateOf("") }
    var passwordError by remember { mutableStateOf("") }

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
            text = "Login",
            style = MaterialTheme.typography.headlineLarge,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        Spacer(modifier = Modifier.height(4.dp))

        Text(
            text = "Login to your account",
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.padding(bottom = 32.dp)
        )

        OutlinedTextField(
            value = username,
            onValueChange = {
                username = it
                usernameError = ""
            },
            label = { Text("Username") },
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
            label = { Text("Password") },
            leadingIcon = { Icon(Icons.Filled.Lock, contentDescription = "Password") },
            trailingIcon = {
                IconButton(onClick = { passwordVisible = !passwordVisible }) {
                    Icon(
                        imageVector = if (passwordVisible) Icons.Filled.Visibility else Icons.Filled.VisibilityOff,
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
                imeAction = ImeAction.Done
            )
        )

        Spacer(modifier = Modifier.height(32.dp))

        // Login Button
        Button(
            onClick = {
                // Validate fields
                var isValid = true

                if (username.isEmpty()) {
                    usernameError = "Please enter your username"
                    isValid = false
                }

                if (password.isEmpty()) {
                    passwordError = "Please enter your password"
                    isValid = false
                } else if (password.length < 6) {
                    passwordError = "Password must be at least 6 characters"
                    isValid = false
                }

                if (isValid) {
                    // Success
                    if (username == receivedUsername && password == receivedPassword) {
                        val intent = Intent(context, HomeActivity::class.java)
                        intent.putExtra("FULLNAME", fullName)
                        context.startActivity(intent)
                        Toast.makeText(context, "Login successful! Welcome, $fullName", Toast.LENGTH_SHORT).show()

                        // Clear all fields
                        username = ""
                        password = ""

                        // Finish the activity
                        (context as LoginActivity).finish()
                    } else {
                        // Error
                        Toast.makeText(context, "Invalid username or password", Toast.LENGTH_SHORT).show()
                    }
                }


            },
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
        ) {
            Text(text = "LOGIN", fontSize = 16.sp)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LoginScreenPreview() {
    TugasDuaPemrogramanMobileTheme {
        LoginScreen("", "", "")
    }
}