#include veil:fog

uniform vec4 ColorModulator;
uniform float FogStart;
uniform float FogEnd;
uniform vec4 FogColor;
uniform vec2 ScreenSize;

in float vertexDistance;
in vec4 vertexColor;
in vec2 texCoord0;

out vec4 fragColor;

void main() {
    fragColor = linear_fog(vec4(1.0, 1.0, 1.0, 1.0), vertexDistance, FogStart, FogEnd, FogColor);
}