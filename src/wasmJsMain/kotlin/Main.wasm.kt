import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.size
import androidx.compose.material.Text
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.CanvasBasedWindow
import kotlinx.browser.window


@OptIn(ExperimentalComposeUiApi::class)
fun main() {
    CanvasBasedWindow("Floating Window") {
//        val canvasRef = remember { mutableStateOf(document.createElement("canvas") as HTMLCanvasElement?) }
//        val coroutineScope = rememberCoroutineScope()
//        val pElemText = remember { mutableStateOf("Window.screenLeft: 0, Window.screenTop: 0") }
//        val position = remember { mutableStateOf(Offset.Zero) }
//
//        fun positionElem(timestamp: Double) {
//            pElemText.value = "Window.screenLeft: ${window.screenX}, Window.screenTop: ${window.screenY}"
//            position.value = Offset(window.screenX.toFloat(), window.screenY.toFloat())
//
//            window.requestAnimationFrame(::positionElem)
//        }
//        window.requestAnimationFrame(::positionElem)
//
//
//        Text(pElemText.value)
//
//        androidx.compose.foundation.Canvas(modifier = Modifier) {
//            drawCircle(
//                color = Color.Red,
//                center = Offset(window.screen.width / 2f, window.screen.height / 2f),
//                radius = 50f
//            )
//        }
//        val windowSize = IntSize(640, 480) // Set window size
//
//        val canvasSize = 400f // Canvas size
//
//        val circleRadius = 50f // Circle radius
//        val circleColor = Color.Blue // Circle color
//
//        val initialLeft = remember { mutableStateOf(0f) }
//        val initialTop = remember { mutableStateOf(0f) }
//
//        val screenLeft = remember { mutableStateOf(0f) }
//        val screenTop = remember { mutableStateOf(0f) }
//
//        val position = remember { mutableStateOf(Offset.Zero) }
//
//        fun positionElem(timestamp: Double) {
//            //   pElemText.value = "Window.screenLeft: ${window.screenX}, Window.screenTop: ${window.screenY}"
//            //position.value = Offset(window.screenX.toFloat(), window.screenY.toFloat())
//            initialLeft.value = window.screenX.toFloat()
//            initialTop.value = window.screenY.toFloat()
//
//            window.requestAnimationFrame(::positionElem)
//        }
//        window.requestAnimationFrame(::positionElem)
//
//        Box(modifier = Modifier.fillMaxSize()) {
//            Canvas(
//                modifier = Modifier.align(Alignment.Center).fill.size(640.dp, 480.dp),
//                onDraw = {
//                    val width = size.width
//                    val height = size.height
//
//                    if (initialLeft.value == 0f) {
//                        initialLeft.value = window.screenX.toFloat()
//                        initialTop.value = window.screenY.toFloat()
//                    }
//
//                    screenLeft.value = window.screenX.toFloat()
//                    screenTop.value = window.screenY.toFloat()
//
//                    val leftUpdate = initialLeft.value - screenLeft.value
//                    val topUpdate = initialTop.value - screenTop.value
//
//                    drawRect(color = Color.Black, size = Size(640f, 480f))
//                    drawCircle(
//                        color = circleColor,
//                        radius = circleRadius,
//                        center = Offset(
//                            (window.screen.width / 2).toLong().toFloat(),
//                            (window.screen.height / 2).toLong().toFloat()
//                        )
//                    )
//                }
//            )
//        }
//
//
//        Text("Window.screenLeft: ${screenLeft.value.toInt()}, Window.screenTop: ${screenTop.value.toInt()}")


        val windowSize = IntSize(640, 480) // Set window size


        val canvasSize = 400f // Canvas size
        val circleRadius = 50f // Circle radius
        val circleColor = Color.Blue // Circle color

        val initialLeft = remember { mutableStateOf(0f) }
        val initialTop = remember { mutableStateOf(0f) }

        val screenLeft = remember { mutableStateOf(0f) }
        val screenTop = remember { mutableStateOf(0f) }

        fun positionElem(timestamp: Double) {
            screenLeft.value = window.screenX.toFloat()
            screenTop.value = window.screenY.toFloat()
            window.requestAnimationFrame(::positionElem)
        }
        window.requestAnimationFrame(::positionElem)

        Canvas(
            modifier = Modifier.size(480.dp, 680.dp),
            onDraw = {
                val width = size.width.toFloat()
                val height = size.height.toFloat()

                if (initialLeft.value == 0f) {
                    initialLeft.value = window.screenX.toFloat()
                    initialTop.value = window.screenY.toFloat()
                }

                val leftUpdate = initialLeft.value - screenLeft.value
                val topUpdate = initialTop.value - screenTop.value

                drawRect(color = Color.Black, size = Size(width, height))
                drawCircle(
                    color = circleColor,
                    radius = circleRadius,
                    center = Offset(leftUpdate + (width / 2), topUpdate + (height / 2) + 35)
                )
            }
        )

        Text("Window.screenLeft: ${screenLeft.value.toInt()}, Window.screenTop: ${screenTop.value.toInt()}")
    }
}


