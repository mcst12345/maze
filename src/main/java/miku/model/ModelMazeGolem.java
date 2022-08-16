package miku.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

import javax.annotation.Nullable;

public class ModelMazeGolem extends ModelBase {
	private final ModelRenderer MazeGolem;
	private final ModelRenderer bb_main;

	public ModelMazeGolem() {
		textureWidth = 32;
		textureHeight = 32;

		MazeGolem = new ModelRenderer(this);
		MazeGolem.setRotationPoint(0.0F, 24.0F, 0.0F);


		ModelRenderer body = new ModelRenderer(this);
		body.setRotationPoint(0.0F, 0.0F, 0.0F);
		MazeGolem.addChild(body);
		body.cubeList.add(new ModelBox(body, 0, 0, -2.0F, -5.0F, -1.0F, 3, 3, 1, 0.0F, false));

		ModelRenderer leg = new ModelRenderer(this);
		leg.setRotationPoint(0.0F, 0.0F, 0.0F);
		MazeGolem.addChild(leg);
		leg.cubeList.add(new ModelBox(leg, 0, 0, 0.0F, -2.0F, -1.0F, 1, 2, 1, 0.0F, false));
		leg.cubeList.add(new ModelBox(leg, 0, 0, -2.0F, -2.0F, -1.0F, 1, 2, 1, 0.0F, false));

		ModelRenderer arm = new ModelRenderer(this);
		arm.setRotationPoint(0.0F, 0.0F, 0.0F);
		MazeGolem.addChild(arm);
		arm.cubeList.add(new ModelBox(arm, 0, 0, -3.0F, -5.0F, -1.0F, 1, 3, 1, 0.0F, false));
		arm.cubeList.add(new ModelBox(arm, 0, 0, 1.0F, -5.0F, -1.0F, 1, 3, 1, 0.0F, false));

		bb_main = new ModelRenderer(this);
		bb_main.setRotationPoint(0.0F, 24.0F, 0.0F);
		bb_main.cubeList.add(new ModelBox(bb_main, 0, 0, -1.0F, -6.0F, -1.0F, 1, 1, 1, 0.0F, false));
	}

	@Override
	public void render(@Nullable Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		MazeGolem.render(f5);
		bb_main.render(f5);
	}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
}