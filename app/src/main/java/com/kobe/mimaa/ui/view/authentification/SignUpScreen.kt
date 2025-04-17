package com.kobe.mimaa.ui.view.authentification

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.kobe.mimaa.R
import com.kobe.mimaa.presentation.navgraph.Routes
import okhttp3.internal.wait

@Composable
fun SignUpScreen(
    navController: NavController,
    //vm: FbViewModel,
) {
    val empty by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var c_password by remember { mutableStateOf("") }
    var passwordVisibility by remember { mutableStateOf(false) }
    var c_passwordVisibility by remember { mutableStateOf(false) }
    var errorE by remember { mutableStateOf(false) }
    var errorP by remember { mutableStateOf(false) }
    var errorCP by remember { mutableStateOf(false) }
    var errorC by remember { mutableStateOf(false) }
    var p_length by remember { mutableStateOf(false) }

    //for firebase indicator
//    Column(
//        modifier = Modifier
//            .fillMaxSize()
//            .padding(10.dp)
//        ,
//        verticalArrangement = Arrangement.Center,
//        horizontalAlignment = Alignment.CenterHorizontally
//    ){
//        if(vm.inProgress.value){
//            CircularProgressIndicator()
//        }
//    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 150.dp)
            .verticalScroll(
                rememberScrollState()
            )

        ,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        //logo app
        AsyncImage(
            model = null,
            placeholder = painterResource(id = R.drawable.person_filled_icn),
            error = painterResource(id = R.drawable.person_filled_icn),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(50.dp)
                .clip(RoundedCornerShape(30.dp))
            ,
        )
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            text = "Creez votre compte",
            style = androidx.compose.material3.MaterialTheme.typography.headlineSmall.copy(
                fontWeight = FontWeight.Bold,
            )
        )


        Spacer(modifier = Modifier.height(10.dp))
        //email error
        if(errorE){
            Text(
                text = "Entrez votre email",
                style = androidx.compose.material3.MaterialTheme.typography.labelLarge.copy(
                    fontWeight = FontWeight.Bold,
                ),
                modifier = Modifier.padding(end = 100.dp),
                color = androidx.compose.material3.MaterialTheme.colorScheme.onError
            )
        }
        TextField(
            value = email,
            onValueChange = {newValue->
                email = newValue
            },
            label = {
                Text(
                    text = stringResource(R.string.emai),
                )
            },
            leadingIcon = {
                Icon(
                    painter = painterResource(R.drawable.person_filled_icn),
                    contentDescription = "email"
                )
            },
            trailingIcon = {
                if(email.isNotEmpty()){
                    Icon(
                        painter = painterResource(R.drawable.close_icn),
                        contentDescription = null,
                        modifier = Modifier.clickable { email = empty }
                    )
                }
            },
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Next
            ),
            singleLine = true,
            textStyle = TextStyle(
                color = Color.White,
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp
            ),
            shape = RoundedCornerShape(8.dp),
            modifier = Modifier
                .width(300.dp)
                .height(60.dp)
            ,
            colors = TextFieldDefaults.textFieldColors(
                unfocusedIndicatorColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent,
                cursorColor = Color.Red,
                focusedLabelColor = Color.White,
                unfocusedLabelColor = Color.White,
                leadingIconColor = Color.Black,
                trailingIconColor = Color.White
            ),
        )


        Spacer(modifier = Modifier.height(10.dp))
        //password error
        if(errorP){
            Text(
                text = "Entrez votre mot de passe",
                style = androidx.compose.material3.MaterialTheme.typography.labelLarge.copy(
                    fontWeight = FontWeight.Bold,
                ),
                modifier = Modifier.padding(end = 100.dp),
                color = androidx.compose.material3.MaterialTheme.colorScheme.onError
            )
        }
        if(p_length){
            Text(
                text = "Le mot de passe doit avoir au moins 6 caracteres",
                style = androidx.compose.material3.MaterialTheme.typography.labelLarge.copy(
                    fontWeight = FontWeight.Bold,
                ),
                modifier = Modifier.padding(end = 100.dp),
                color = androidx.compose.material3.MaterialTheme.colorScheme.onError
            )
        }

        //password textfield
        TextField(
            value = password,
            onValueChange = {newValue->
                password = newValue
                p_length = (newValue.length < 6)
            },
            label = {
                Text(
                    text = stringResource(R.string.password),
                )
            },
            leadingIcon = {
                Icon(
                    painter = painterResource(R.drawable.lock_filled_icn),
                    contentDescription = "password"
                )
            },
            trailingIcon = {
                if(password.isNotEmpty()){
                    val visibilityIcon =
                        if(passwordVisibility){
                        painterResource(R.drawable.visibillity_on_filled_icn)
                        }
                        else{ painterResource(R.drawable.visibility_off_filled_icn)
                    }
                    Icon(
                        painter = visibilityIcon,
                        contentDescription = if(passwordVisibility) "hide Password" else "Show Password",
                        modifier = Modifier
                            .clickable {
                                passwordVisibility =! passwordVisibility
                            }
                    )
                }
            },
            visualTransformation = if(passwordVisibility){
                VisualTransformation.None
            }else{
                PasswordVisualTransformation()
            },
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Next,
                keyboardType = KeyboardType.Password
            ),
            singleLine = true,
            textStyle = TextStyle(
                color = Color.White,
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp
            ),
            shape = RoundedCornerShape(8.dp),
            modifier = Modifier
                .width(300.dp)
                .height(60.dp)
            ,
            colors = TextFieldDefaults.textFieldColors(
                unfocusedIndicatorColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent,
                cursorColor = Color.Red,
                focusedLabelColor = Color.White,
                unfocusedLabelColor = Color.White,
                leadingIconColor = Color.Black,
                trailingIconColor = Color.Black
            ),
        )

        Spacer(modifier = Modifier.height(10.dp))
        //password confirmation error
        if(errorCP){
            Text(
                text = "Le mot de passe ne correspond pas",
                style = androidx.compose.material3.MaterialTheme.typography.labelLarge.copy(
                    fontWeight = FontWeight.Bold,
                ),
                modifier = Modifier.padding(end = 100.dp),
                color = androidx.compose.material3.MaterialTheme.colorScheme.onError
            )
        }
        if(errorC){
            Text(
                text = "Entrez un mot de passe conforme",
                style = androidx.compose.material3.MaterialTheme.typography.labelLarge.copy(
                    fontWeight = FontWeight.Bold,
                ),
                modifier = Modifier.padding(end = 100.dp),
                color = androidx.compose.material3.MaterialTheme.colorScheme.onError
            )
        }
        TextField(
            value = c_password,
            onValueChange = { newValue ->
                c_password = newValue
            },
            label = {
                Text(
                    text = stringResource(R.string.confirm_password),
                )
            },
            leadingIcon = {
                Icon(
                    painter = painterResource(R.drawable.lock_filled_icn),
                    contentDescription = "c_password"
                )
            },
            trailingIcon = {
                if (c_password.isNotEmpty()) {
                    val visibilityIcon =
                        if (c_passwordVisibility) {
                            painterResource(R.drawable.visibillity_on_filled_icn)
                        } else {
                            painterResource(R.drawable.visibility_off_filled_icn)
                        }
                    Icon(
                        painter = visibilityIcon,
                        contentDescription = if (c_passwordVisibility) "hide C_Password" else "Show C_Password",
                        modifier = Modifier
                            .clickable {
                                c_passwordVisibility = !c_passwordVisibility
                            }
                    )
                }
            },
            visualTransformation = if (c_passwordVisibility) {
                VisualTransformation.None
            } else {
                PasswordVisualTransformation()
            },
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Done,
                keyboardType = KeyboardType.Password
            ),
            singleLine = true,
            textStyle = TextStyle(
                color = Color.White,
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp
            ),
            shape = RoundedCornerShape(8.dp),
            modifier = Modifier
                .width(300.dp)
                .height(60.dp),
            colors = TextFieldDefaults.textFieldColors(
                unfocusedIndicatorColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent,
                cursorColor = Color.Red,
                focusedLabelColor = Color.White,
                unfocusedLabelColor = Color.White,
                leadingIconColor = Color.Black,
                trailingIconColor = Color.Black
            ),
        )

        //signup button
        Spacer(modifier = Modifier.height(50.dp))
        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(8.dp))
                .background(Color.White)
        ){
            Button(
                onClick = {
                    //conditions
                    if(email.isNotEmpty()){
                        errorE = false
                        if(password.isNotEmpty()){
                            errorP = false
                            if(c_password.isNotEmpty()){
                                errorC = false
                                if (password == c_password){
                                    errorCP = false

                                    //signUp
                                    //vm.signUp(email, password)
                                }else{
                                    errorCP = true
                                }
                            }else{
                                errorC = true
                            }
                        }else{
                            errorP = true
                        }
                    }else{
                        errorE = true
                    }
                },
                colors = ButtonDefaults.buttonColors(
                    Color.Transparent
                ),
                modifier = Modifier.width(200.dp)
            ){
                Text(
                    text = "Creer un compte",
                    color = Color.Black,
                    style = androidx.compose.material3.MaterialTheme.typography.titleMedium.copy(
                        fontWeight = FontWeight.Bold
                    )
                )
            }
//            if (vm.signedIn.value){
//                navController.navigate(Routes.Screen.LoginScreen.route)
//            }
//            vm.signedIn.value = false
        }

    }
}



@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun SignUpPreview() {
    val navController = rememberNavController()
    SignUpScreen(navController)
}