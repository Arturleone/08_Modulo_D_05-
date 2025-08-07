package com.example.a08_modulo_d_05

import android.widget.Toast
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.longClick
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextClearance
import androidx.compose.ui.test.performTextInput
import androidx.compose.ui.test.performTouchInput
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.example.a08_modulo_d_05.view.CadastroCurso
import com.example.a08_modulo_d_05.view.DashboardScreen
import com.example.a08_modulo_d_05.view.ProfessorScreen
import com.example.a08_modulo_d_05.view.RelatorioScreen
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ModuloDTestes {

    @get:Rule
    val rule = createComposeRule()

    val timer = 10000L

    @Test
    fun useAppContext() {
        runBlocking {
            rule.setContent {
                val nav = rememberNavController()
                val ctx = LocalContext.current
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

            delay(timer)
            rule.onNodeWithTag("OutSearch").performTextClearance()
            delay(timer)
            rule.onNodeWithTag("OutSearch").performTextInput("@#$")
            delay(timer)

            delay(timer)
            rule.onNodeWithTag("OutSearch").performTextClearance()
            delay(timer)
            rule.onNodeWithTag("OutSearch").performTextInput("Matemática-Avançada")
            delay(timer)

            delay(timer)
            rule.onNodeWithTag("OutSearch").performTextClearance()
            delay(timer)
            rule.onNodeWithTag("OutSearch").performTextInput("Mate")
            delay(timer)

            delay(timer)
            rule.onNodeWithTag("OutSearch").performTextClearance()
            delay(timer)
            rule.onNodeWithTag("OutSearch").performTextInput("Física Quântica")
            delay(timer)

            delay(timer)
            rule.onNodeWithTag("OutSearch").performTextClearance()
            delay(timer)

            rule.onNodeWithTag("ButtonGrid").performClick()
            delay(timer)

            delay(timer)
            rule.onNodeWithTag("curso_Lógica de Programação").performTouchInput { longClick() }
            delay(timer)

            delay(timer)
            rule.onNodeWithText("Fechar").performClick()
            delay(timer)

            delay(timer)
            rule.onNodeWithTag("ButtonList").performClick()
            delay(timer)

            delay(timer)
            rule.onNodeWithTag("curso_Lógica de Programação").performClick()
            delay(timer)

            delay(timer)
            rule.onNodeWithText("Salvar").performClick()
            delay(timer)

            rule.onNodeWithTag("btnAdicionar").performClick()
            delay(timer)

            delay(timer)
            rule.onNodeWithTag("BarSalvar").performClick()
            delay(timer)

            delay(timer)
            rule.onNodeWithTag("OutNome").performTextInput("Desenvolvimento de Aplicativos Móveis")
            delay(timer)
            rule.onNodeWithTag("BarSalvar").performClick()
            delay(timer)

            delay(timer)
            rule.onNodeWithTag("OutBreve").performTextInput("Mobile")
            delay(timer)

            delay(timer)
            rule.onNodeWithTag("BarSalvar").performClick()
            delay(timer)

            delay(timer)
            rule.onNodeWithTag("boxCategoria").performClick()
            delay(timer)

            delay(timer)
            rule.onNodeWithText("Qualidade de Software").performClick()
            delay(timer)

            delay(timer)
            rule.onNodeWithTag("OutInicio").performTextInput("15/02/2025")
            delay(timer)
            rule.onNodeWithTag("OutInicio").performTextClearance()
            delay(timer)
            rule.onNodeWithTag("OutInicio").performTextInput("12/11/2025")
            delay(timer)
            delay(timer)
            rule.onNodeWithTag("OutFim").performTextInput("12/10/2025")
            delay(timer)
            rule.onNodeWithTag("OutFim").performTextClearance()
            delay(timer)
            rule.onNodeWithTag("OutFim").performTextInput("12/12/2025")
            delay(timer)
            delay(timer)
            rule.onNodeWithTag("BarSalvar").performClick()
            delay(timer)
            delay(timer)
            rule.onNodeWithTag("ButAdd").performClick()
            delay(timer)
            listOf("Ana Silva", "Caio Martins", "Luciana Costa", "Felipe Nogueira", "Júlia Fernandes").forEach {
                rule.onNodeWithText(it).performClick()
            }
            delay(timer)
            listOf("Ana Silva", "Caio Martins").forEach {
                rule.onNodeWithText(it, ignoreCase = true).performClick()
            }
            delay(timer)
            rule.onNodeWithText("Close").performClick()
            delay(timer)
            rule.onNodeWithTag("BarSalvar").performClick()
            delay(timer)
            rule.onNodeWithTag("BtnProf").performClick()
            delay(timer)




        }
    }
}