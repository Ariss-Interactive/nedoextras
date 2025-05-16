package ariss.nedoextras.rendering.block;

import org.joml.Matrix4f;
import ariss.nedoextras.block.entity.PortalBlockEntity;
import net.minecraft.client.render.*;
import net.minecraft.client.render.block.entity.BlockEntityRenderer;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactory;
import net.minecraft.client.util.math.MatrixStack;

public class PortalBlockEntityRenderer implements BlockEntityRenderer<PortalBlockEntity> {

    public PortalBlockEntityRenderer(BlockEntityRendererFactory.Context ignoredCtx) {}

    @Override
    public void render(PortalBlockEntity entity, float tickDelta, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int overlay) {
        Matrix4f matrix4f = matrices.peek().getPositionMatrix();
        this.renderSides(matrix4f, vertexConsumers.getBuffer(RenderLayer.getEndPortal()));
    }

    private void renderSides(Matrix4f matrix, VertexConsumer vertexConsumer) {
        float f = 0.375f; // bottom offset
        float g = 0.75f; // top offset
        this.renderSide(matrix, vertexConsumer, 0.0F, 1.0F, f, f, 0.0F, 0.0F, 1.0F, 1.0F);
        this.renderSide(matrix, vertexConsumer, 0.0F, 1.0F, g, g, 1.0F, 1.0F, 0.0F, 0.0F);
    }

    private void renderSide(Matrix4f model, VertexConsumer vertices, float x1, float x2, float y1, float y2, float z1, float z2, float z3, float z4) {
        vertices.vertex(model, x1, y1, z1).next();
        vertices.vertex(model, x2, y1, z2).next();
        vertices.vertex(model, x2, y2, z3).next();
        vertices.vertex(model, x1, y2, z4).next();
    }
}
