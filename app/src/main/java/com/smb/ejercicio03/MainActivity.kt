package com.smb.ejercicio03

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester.Companion.createRefs
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ChainStyle
import androidx.constraintlayout.compose.ConstraintLayout
import com.smb.ejercicio03.ui.theme.Ejercicio03Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Ejercicio03Theme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ConstraintBarrier()
                }
            }
        }
    }
}


@Composable
fun GreetingPreview() {
    Ejercicio03Theme {
        ConstraintBarrier()
    }
}

// BARRERAS
@Composable
fun ConstraintBarrier() {
    ConstraintLayout {
        val (box1, box2, box3) = createRefs()
        val barrier = createEndBarrier(box1, box2)

        Box(modifier = Modifier
            .size(125.dp)
            .background(randomColor())
            .constrainAs(box1) {
                start.linkTo(parent.start, margin = 16.dp)
            })

        Box(modifier = Modifier
            .size(225.dp)
            .background(randomColor())
            .constrainAs(box2) {
                top.linkTo(box1.bottom, margin = 32.dp)
            })
        Box(modifier = Modifier
            .size(50.dp)
            .background(randomColor())
            .constrainAs(box3) {
                start.linkTo(barrier)
            })
    }
}

// Establecer guias
@Composable
fun ConstraintLayoutGuide() {
    ConstraintLayout(modifier = Modifier.fillMaxSize()) {
        val boxRed = createRef()
        val topGuide = createGuidelineFromTop(0.5f)
        val startGuide = createGuidelineFromStart(0.25f)
        Box(modifier = Modifier
            .size(125.dp)
            .background(randomColor())
            .constrainAs(boxRed) {
                top.linkTo(topGuide)
                start.linkTo(startGuide)
            })
    }
}

@Composable
fun ConstraintExample() {
    ConstraintLayout(modifier = Modifier.fillMaxSize()) {

        val (boxRed, boxYellow) = createRefs()

        Box(modifier = Modifier
            .size(125.dp)
            .background(randomColor())
            .constrainAs(boxRed) {
                top.linkTo(parent.top)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
                bottom.linkTo(parent.bottom)
            })

        Box(modifier = Modifier
            .size(125.dp)
            .background(randomColor())
            .constrainAs(boxYellow) {
                end.linkTo(boxRed.start)
                bottom.linkTo(boxRed.top)
            })
    }
}

@Preview(
    showBackground = true,
    showSystemUi = true,
    apiLevel = 29,
    device = Devices.NEXUS_6
)
@Composable
fun ConstraintChainExampler() {
    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)
            .border(1.dp, Color.Green, RectangleShape)
    )
    {
        val (boxRed, boxBlue, boxYellow) = createRefs()

        Box(modifier = Modifier
            .size(75.dp)
            .background(Color.Red)
            .constrainAs(boxRed) {
                start.linkTo(parent.start)
                end.linkTo(boxYellow.start)

            })

        Box(modifier = Modifier
            .size(75.dp)
            .background(Color.Yellow)
            .constrainAs(boxYellow) {
                start.linkTo(boxRed.end)
                end.linkTo(boxBlue.start)

            })

        Box(modifier = Modifier
            .size(75.dp)
            .background(Color.Blue)
            .constrainAs(boxBlue) {
                start.linkTo(boxYellow.start)
                end.linkTo(parent.end)

            })
        //variar el ChainStyle. a los 3 estilos
        createHorizontalChain(boxRed, boxBlue, boxYellow, chainStyle = ChainStyle.SpreadInside)
    }
}

fun randomColor() = Color(kotlin.random.Random.nextLong(0xFFFFFFFF))


