package com.gauntletrecolor;

import net.runelite.api.*;
import net.runelite.api.coords.LocalPoint;
import net.runelite.api.coords.WorldPoint;
import net.runelite.client.ui.overlay.*;

import javax.annotation.Nullable;
import javax.inject.Inject;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.util.Collection;

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

//        Scene scene = client.getScene();
        final WorldPoint playerPos = client.getLocalPlayer().getWorldLocation();
        //final LocalPoint playerPosLocal = LocalPoint.fromWorld(client, playerPos);

        //Tile[][] tiles = scene.getTiles()[1]; //1 is z level of gauntlet lobby
        Color tileColor = new Color(255, 234, 84, 65);

        for (int x = 3027; x <= 3038; x++) {
            for (int y = 6118; y <= 6129; y++) {
                final LocalPoint localPoint = LocalPoint.fromWorld(client, x, y);
                //LocalPoint lp = new LocalPoint(x,y); //TODO: why not work?
                renderTile(graphics, localPoint, tileColor, 0, tileColor);
            }
        }
        //renderTile(graphics, playerPosLocal, tileColor, 2, tileColor);
        return null;
    }

    private void drawTile(Graphics2D graphics, WorldPoint point, Color color) {
        LocalPoint lp = LocalPoint.fromWorld(client, point);
        if (lp == null) {
            return;
        }

        Polygon poly = Perspective.getCanvasTilePoly(client, lp);
        if (poly != null) {
            OverlayUtil.renderPolygon(graphics, poly, color);
        }
    }

    private void renderTile(final Graphics2D graphics, final LocalPoint dest, final Color color, final double borderWidth, final Color fillColor)
    {
        if (dest == null)
        {
            return;
        }

        final Polygon poly = Perspective.getCanvasTilePoly(client, dest);

        if (poly == null)
        {
            return;
        }

        OverlayUtil.renderPolygon(graphics, poly, color, fillColor, new BasicStroke((float) borderWidth));
    }
}
