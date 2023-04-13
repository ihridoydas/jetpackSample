package com.ihridoydas.simpleapp.util.responsiveUI.component.AGSL_customLayoutAndGraphics

import android.graphics.Color
import android.graphics.RuntimeShader
import android.os.Build
import androidx.compose.animation.core.withInfiniteAnimationFrameMillis
import androidx.compose.runtime.getValue
import androidx.compose.runtime.produceState
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.graphics.ShaderBrush
import com.ihridoydas.simpleapp.ui.theme.GreenColor
import com.ihridoydas.simpleapp.ui.theme.GreenVarient
import org.intellij.lang.annotations.Language

/**
//Colors
val Green = Color(0xFF55A456)
val GreenVarient = Color(0xFFB3D5B3)
 */

fun Modifier.BackgroundByAGSL(): Modifier = this.composed {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        // produce updating time in seconds variable to pass into shader
        val time by produceState(0f) {
            while (true) {
                withInfiniteAnimationFrameMillis {
                    value = it / 1000f
                }
            }
        }
        Modifier.drawWithCache {
            val shader = RuntimeShader(SHADER)
            val shaderBrush = ShaderBrush(shader)
            shader.setFloatUniform("iResolution", size.width, size.height)
            shader.setFloatUniform("iTime", time)
            // Pass the color to support color space automatically
            shader.setColorUniform(
                "iColor",
                Color.valueOf(GreenColor.red, GreenColor.green, GreenColor.blue, GreenColor.alpha)
            )
            onDrawBehind {
                drawRect(shaderBrush)
            }
        }
    } else {
        Modifier.drawWithCache {
            val gradientBrush = Brush.verticalGradient(listOf(GreenColor, GreenVarient, White))
            onDrawBehind {
                drawRect(gradientBrush)
            }
        }
    }
}

@Language("AGSL")
val SHADER = """
    uniform float2 iResolution;
    uniform float iTime;
    layout(color) uniform half4 iColor;
    
    float calculateColorMultiplier(float yCoord, float factor) {
        return step(yCoord, 1.0 + factor * 2.0) - step(yCoord, factor - 0.1);
    }

    float4 main(in float2 fragCoord) {
        // Config values
        const float speedMultiplier = 1.5;
        const float waveDensity = 1.0;
        const float loops = 8.0;
        const float energy = 0.6;
        
        // Calculated values
        float2 uv = fragCoord / iResolution.xy;
        float3 color = iColor.rgb;
        float timeOffset = iTime * speedMultiplier;
        float hAdjustment = uv.x * 4.3;
        float3 loopColor = vec3(1.0 - color.r, 1.0 - color.g, 1.0 - color.b) / loops;
        
        for (float i = 1.0; i <= loops; i += 1.0) {
            float loopFactor = i * 0.1;
            float sinInput = (timeOffset + hAdjustment) * energy;
            float curve = sin(sinInput) * (1.0 - loopFactor) * 0.05;
            float colorMultiplier = calculateColorMultiplier(uv.y, loopFactor);
            color += loopColor * colorMultiplier;
            
            // Offset for next loop
            uv.y += curve;
        }
        
        return float4(color, 1.0);
    }
""".trimIndent()
