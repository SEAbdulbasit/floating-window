import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.CanvasBasedWindow


@OptIn(ExperimentalComposeUiApi::class)
fun main() {
    CanvasBasedWindow("Floating Window") {

        MaterialTheme {
            val screenWidth = 640.dp
            val screenHeight = 480.dp

            val initialLeft = 0.dp
            val initialTop = 0.dp

            val leftUpdate = remember { mutableStateOf(initialLeft) }
            val topUpdate = remember { mutableStateOf(initialTop) }

            val density = LocalDensity.current.density

            DisposableEffect(Unit) {
                val animation = this. {
                    while (true) {
                        // Simulating screenLeft and screenTop changes
                        leftUpdate.value += 1.dp
                        topUpdate.value += 1.dp
                        delay(16) // ~60 FPS
                    }
                }
                onDispose {
                    animation.cancel()
                }
            }

            val observedLeft by leftUpdate
            val observedTop by topUpdate

            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Canvas(
                    modifier = Modifier.size(screenWidth, screenHeight)
                ) {
                    drawIntoCanvas { canvas ->
                        val width = size.width
                        val height = size.height

                        val leftUpdateValue = observedLeft.toPx()
                        val topUpdateValue = observedTop.toPx()

                        canvas.drawCircle(
                            center = Offset(
                                leftUpdateValue + (width / 2),
                                topUpdateValue + (height / 2) + 35 * density
                            ),
                            radius = 50 * density,
                            paint = Paint()
                        )
                    }
                }
                Spacer(modifier = Modifier.height(16.dp))
                Text("Window.screenLeft: $observedLeft, Window.screenTop: $observedTop")
            }
        }
    }
}

