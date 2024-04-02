package com.gauntletrecolor;

import net.runelite.api.*;
import net.runelite.api.coords.LocalPoint;
import net.runelite.api.coords.WorldPoint;
import net.runelite.client.ui.overlay.*;

import javax.inject.Inject;
import java.awt.*;

public class RecolorGroundOverlay extends Overlay {

    private final Client client;
    private final GauntletRecolorConfig config;
    //private final GauntletRecolorPlugin plugin;

    @Inject
    public RecolorGroundOverlay(Client client, GauntletRecolorConfig config, GauntletRecolorPlugin plugin) {
        this.client = client;
        this.config = config;
        //this.plugin = plugin;
        setPosition(OverlayPosition.DYNAMIC);
        setPriority(PRIORITY_LOW);
        setLayer(OverlayLayer.ABOVE_SCENE);
    }

    @Override
    public Dimension render(Graphics2D graphics) {

        final WorldPoint playerPos = client.getLocalPlayer().getWorldLocation();
        //final LocalPoint playerPosLocal = LocalPoint.fromWorld(client, playerPos);

        Color tileColor = new Color(255, 234, 110, 65);

        for (int x = 3027; x <= 3038; x++) {
            for (int y = 6118; y <= 6129; y++) {
                final LocalPoint localPoint = LocalPoint.fromWorld(client, x, y);
                renderTile(graphics, localPoint, tileColor, 0);
            }
        }

        //removeActor(graphics, client.getLocalPlayer()); TODO
        return null;
    }

    private void renderTile(final Graphics2D graphics, final LocalPoint dest, final Color color, final double borderWidth)
    {
        if (dest == null)
        {
            return;
        }

        final Polygon poly = Perspective.getCanvasTilePoly(client, dest);

        if (poly != null)
        {
            OverlayUtil.renderPolygon(graphics, poly, color, color, new BasicStroke((float) borderWidth));
        }
    }

    private void removeActor(final Graphics2D graphics, final Actor actor) {
        final int clipX1 = client.getViewportXOffset();
        final int clipY1 = client.getViewportYOffset();
        final int clipX2 = client.getViewportWidth() + clipX1;
        final int clipY2 = client.getViewportHeight() + clipY1;
        Object origAA = graphics.getRenderingHint(RenderingHints.KEY_ANTIALIASING);
        graphics.setRenderingHint(
                RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_OFF);
        Model model = actor.getModel();
        int vCount = model.getVerticesCount();
        int[] x3d = model.getVerticesX();
        int[] y3d = model.getVerticesY();
        int[] z3d = model.getVerticesZ();

        int[] x2d = new int[vCount];
        int[] y2d = new int[vCount];

        int size = 1;
        if (actor instanceof NPC)
        {
            NPCComposition composition = ((NPC) actor).getTransformedComposition();
            if (composition != null)
            {
                size = composition.getSize();
            }
        }

        final LocalPoint lp = actor.getLocalLocation();

        final int localX = lp.getX();
        final int localY = lp.getY();
        final int northEastX = lp.getX() + Perspective.LOCAL_TILE_SIZE * (size - 1) / 2;
        final int northEastY = lp.getY() + Perspective.LOCAL_TILE_SIZE * (size - 1) / 2;
        final LocalPoint northEastLp = new LocalPoint(northEastX, northEastY);
        int localZ = Perspective.getTileHeight(client, northEastLp, client.getPlane());
        int rotation = actor.getCurrentOrientation();

        Perspective.modelToCanvas(client, vCount, localX, localY, localZ, rotation, x3d, z3d, y3d, x2d, y2d);

        boolean anyVisible = false;

        for (int i = 0; i < vCount; i++) {
            int x = x2d[i];
            int y = y2d[i];

            boolean visibleX = x >= clipX1 && x < clipX2;
            boolean visibleY = y >= clipY1 && y < clipY2;
            anyVisible |= visibleX && visibleY;
        }

        if (!anyVisible) return;

        int tCount = model.getFaceCount();
        int[] tx = model.getFaceIndices1();
        int[] ty = model.getFaceIndices2();
        int[] tz = model.getFaceIndices3();

        final byte[] triangleTransparencies = model.getFaceTransparencies();

        Composite orig = graphics.getComposite();
        graphics.setComposite(AlphaComposite.Clear);
        graphics.setColor(Color.WHITE);
        for (int i = 0; i < tCount; i++) {
            // Cull tris facing away from the camera
            if (getTriDirection(x2d[tx[i]], y2d[tx[i]], x2d[ty[i]], y2d[ty[i]], x2d[tz[i]], y2d[tz[i]]) >= 0)
            {
                continue;
            }
            if (triangleTransparencies == null || (triangleTransparencies[i] & 255) < 254) {
                Polygon p = new Polygon(
                        new int[]{x2d[tx[i]], x2d[ty[i]], x2d[tz[i]]},
                        new int[]{y2d[tx[i]], y2d[ty[i]], y2d[tz[i]]},
                        3);
                graphics.fill(p);
            }
        }
        graphics.setComposite(orig);
        graphics.setRenderingHint(
                RenderingHints.KEY_ANTIALIASING,
                origAA);
    }

    private int getTriDirection(int x1, int y1, int x2, int y2, int x3, int y3) {
        int x4 = x2 - x1;
        int y4 = y2 - y1;
        int x5 = x3 - x1;
        int y5 = y3 - y1;
        return x4 * y5 - y4 * x5;
    }
}
