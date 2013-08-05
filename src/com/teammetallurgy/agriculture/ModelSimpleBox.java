package com.teammetallurgy.agriculture;

import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.model.PositionTextureVertex;
import net.minecraft.client.model.TexturedQuad;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.Icon;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ModelSimpleBox
{
    /**
     * The (x,y,z) vertex positions and (u,v) texture coordinates for each of the 8 points on a cube
     */
    private PositionTextureVertex[] vertexPositions;

    /** An array of 6 TexturedQuads, one for each face of a cube */
    private TexturedQuadIcon[] quadList;

    /** X vertex coordinate of lower box corner */
    public final float posX1;

    /** Y vertex coordinate of lower box corner */
    public final float posY1;

    /** Z vertex coordinate of lower box corner */
    public final float posZ1;

    /** X vertex coordinate of upper box corner */
    public final float posX2;

    /** Y vertex coordinate of upper box corner */
    public final float posY2;

    /** Z vertex coordinate of upper box corner */
    public final float posZ2;
    public String field_78247_g;

	private Icon icon;

    public ModelSimpleBox(int textureWidth, int textureHeight, int textureX, int textureZ, float posX, float posY, float posZ, int width, int height, int width2, int par10, Icon liquidIcon)
    {
    	this.icon = liquidIcon;
        this.posX1 = posX;
        this.posY1 = posY;
        this.posZ1 = posZ;
        this.posX2 = posX + (float)width;
        this.posY2 = posY + (float)height;
        this.posZ2 = posZ + (float)width2;
        this.vertexPositions = new PositionTextureVertex[8];
        this.quadList = new TexturedQuadIcon[6];
        float f4 = posX + (float)width;
        float f5 = posY + (float)height;
        float f6 = posZ + (float)width2;
        posX -= par10;
        posY -= par10;
        posZ -= par10;
        f4 += par10;
        f5 += par10;
        f6 += par10;

        PositionTextureVertex positiontexturevertex = new PositionTextureVertex(posX, posY, posZ, 0.0F, 0.0F);
        PositionTextureVertex positiontexturevertex1 = new PositionTextureVertex(f4, posY, posZ, 0.0F, 8.0F);
        PositionTextureVertex positiontexturevertex2 = new PositionTextureVertex(f4, f5, posZ, 8.0F, 8.0F);
        PositionTextureVertex positiontexturevertex3 = new PositionTextureVertex(posX, f5, posZ, 8.0F, 0.0F);
        PositionTextureVertex positiontexturevertex4 = new PositionTextureVertex(posX, posY, f6, 0.0F, 0.0F);
        PositionTextureVertex positiontexturevertex5 = new PositionTextureVertex(f4, posY, f6, 0.0F, 8.0F);
        PositionTextureVertex positiontexturevertex6 = new PositionTextureVertex(f4, f5, f6, 8.0F, 8.0F);
        PositionTextureVertex positiontexturevertex7 = new PositionTextureVertex(posX, f5, f6, 8.0F, 0.0F);
        this.vertexPositions[0] = positiontexturevertex;
        this.vertexPositions[1] = positiontexturevertex1;
        this.vertexPositions[2] = positiontexturevertex2;
        this.vertexPositions[3] = positiontexturevertex3;
        this.vertexPositions[4] = positiontexturevertex4;
        this.vertexPositions[5] = positiontexturevertex5;
        this.vertexPositions[6] = positiontexturevertex6;
        this.vertexPositions[7] = positiontexturevertex7;
        this.quadList[0] = new TexturedQuadIcon(new PositionTextureVertex[] {positiontexturevertex5, positiontexturevertex1, positiontexturevertex2, positiontexturevertex6}, textureX + width2 + width, textureZ + width2, textureX + width2 + width + width2, textureZ + width2 + height, textureWidth, textureHeight);
        this.quadList[1] = new TexturedQuadIcon(new PositionTextureVertex[] {positiontexturevertex, positiontexturevertex4, positiontexturevertex7, positiontexturevertex3}, textureX, textureZ + width2, textureX + width2, textureZ + width2 + height, textureWidth, textureHeight);
        this.quadList[2] = new TexturedQuadIcon(new PositionTextureVertex[] {positiontexturevertex5, positiontexturevertex4, positiontexturevertex, positiontexturevertex1}, textureX + width2, textureZ, textureX + width2 + width, textureZ + width2, textureWidth, textureHeight);
        this.quadList[3] = new TexturedQuadIcon(new PositionTextureVertex[] {positiontexturevertex2, positiontexturevertex3, positiontexturevertex7, positiontexturevertex6}, textureX + width2 + width, textureZ + width2, textureX + width2 + width + width, textureZ, textureWidth, textureHeight);
        this.quadList[4] = new TexturedQuadIcon(new PositionTextureVertex[] {positiontexturevertex1, positiontexturevertex, positiontexturevertex3, positiontexturevertex2}, textureX + width2, textureZ + width2, textureX + width2 + width, textureZ + width2 + height, textureWidth, textureHeight);
        this.quadList[5] = new TexturedQuadIcon(new PositionTextureVertex[] {positiontexturevertex4, positiontexturevertex5, positiontexturevertex6, positiontexturevertex7}, textureX + width2 + width + width2, textureZ + width2, textureX + width2 + width + width2 + width, textureZ + width2 + height, textureWidth, textureHeight);
    }

    /**
     * Draw the six sided box defined by this ModelBox
     */
    @SideOnly(Side.CLIENT)
    public void render(Tessellator par1Tessellator, float par2)
    {
        for (int i = 0; i < this.quadList.length; ++i)
        {
            this.quadList[i].draw(par1Tessellator, par2, icon);
        }
    }

    public ModelSimpleBox func_78244_a(String par1Str)
    {
        this.field_78247_g = par1Str;
        return this;
    }
}