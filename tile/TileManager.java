package tile;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class TileManager {

    GamePanel gp;
    public Tiles[] tile;
    public int mapTileNumber[][];

    public TileManager(GamePanel gp) {
        this.gp = gp;
        tile = new Tiles[10];
        mapTileNumber = new int[gp.maxWorldCol][gp.maxWorldRow];

        getTileImage();
        loadMap();
    }

    public void getTileImage() {
        try {
            tile[0] = new Tiles();
            tile[0].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("tiles/roof1.png"));
            tile[0].collision = true;

            tile[1] = new Tiles();
            tile[1].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("tiles/floor2.png"));

            tile[2] = new Tiles();
            tile[2].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("tiles/verticalwall.png"));
            tile[2].collision = true;

            tile[3] = new Tiles();
            tile[3].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("tiles/horizontalwall.png"));
            tile[3].collision = true;

            tile[4] = new Tiles();
            tile[4].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("tiles/innercorner.png"));
            tile[4].collision = true;

            tile[5] = new Tiles();
            tile[5].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("tiles/outercorner.png"));
            tile[5].collision = true;

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadMap() {
        try {
            InputStream is = getClass().getClassLoader().getResourceAsStream("maps/map1.txt");
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            int col = 0;
            int row = 0;

            while (col < gp.maxWorldCol && row < gp.maxWorldRow) {
                String line = br.readLine();

                while (col < gp.maxWorldCol) {
                    String numbers[] = line.split(" ");
                    int num = Integer.parseInt(numbers[col]);

                    mapTileNumber[col][row] = num;
                    col++;
                }
                if (col == gp.maxWorldCol) {
                    col = 0;
                    row++;
                }
            }
            br.close();

        } catch (Exception e) {
        }
    }

    public void draw(Graphics2D g2) {

        int worldCol = 0;
        int worldRow = 0;

        while (worldCol < gp.maxWorldCol && worldRow < gp.maxWorldRow) {
            int tileNum = mapTileNumber[worldCol][worldRow];

            int worldX = worldCol * gp.tileSize;
            int worldY = worldRow * gp.tileSize;
            int screenX = worldX - gp.player.worldX + gp.player.screenX;
            int screenY = worldY - gp.player.worldY + gp.player.screenY;

            g2.drawImage(tile[tileNum].image, screenX, screenY, gp.tileSize, gp.tileSize, null);
            worldCol++;

            if (worldCol == gp.maxWorldCol) {
                worldCol = 0;
                worldRow++;
            }
        }
    }
}
