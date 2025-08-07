package com.example.a08_modulo_d_05

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.a08_modulo_d_05.ui.theme._08_Modulo_D_05Theme
import com.example.a08_modulo_d_05.view.CadastroCurso
import com.example.a08_modulo_d_05.view.DashboardScreen
import com.example.a08_modulo_d_05.view.ProfessorScreen
import com.example.a08_modulo_d_05.view.RelatorioScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            _08_Modulo_D_05Theme (darkTheme = false) {
                MyApp()
            }
        }
    }
}

@Composable
fun MyApp() {
    val nav = rememberNavController()
    NavApp(nav)
}

@Composable
fun NavApp(nav: NavHostController) {
    NavHost(nav, "dashboard") {
        composable("dashboard") {
            DashboardScreen(nav)
        }
        composable("professor") {
            ProfessorScreen(nav)
        }
        composable("dashboardCadastro") {
            CadastroCurso(nav)
        }
        composable("relatorio") {
            RelatorioScreen(nav)
        }
    }
}
