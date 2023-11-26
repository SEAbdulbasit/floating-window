import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.Text
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.clipRect
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.CanvasBasedWindow
import kotlinx.browser.window


@OptIn(ExperimentalComposeUiApi::class)
fun main() {
    CanvasBasedWindow("Floating Window") {
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
            modifier = Modifier.size(680.dp, 480.dp),
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
                clipRect(
                    left = 0f,
                    top = 0f,
                    right = width,
                    bottom = height
                ) {
                    drawCircle(
                        color = circleColor,
                        radius = circleRadius,
                        center = Offset(leftUpdate + (width / 2), topUpdate + (height / 2) + 35)
                    )
                }
            }
        )


        Text("Window.screenLeft: ${screenLeft.value.toInt()}, Window.screenTop: ${screenTop.value.toInt()}")
    }
}


