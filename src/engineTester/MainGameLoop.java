package engineTester;

import entities.Camera;
import entities.Entity;
import entities.Light;
import entities.Player;
import guis.GuiRenderer;
import guis.GuiTexture;
import models.Cube;
import models.RawModel;
import models.TexturedModel;
import objConverter.ModelData;
import objConverter.OBJFileLoader;
import objConverter.OBJLoader;
import org.lwjgl.opengl.Display;
import org.lwjgl.util.vector.Vector2f;
import org.lwjgl.util.vector.Vector3f;
import renderEngine.DisplayManager;
import renderEngine.Loader;
import renderEngine.MasterRenderer;
import terrains.Terrain;
import textures.ModelTexture;
import textures.TerrainTexture;
import textures.TerrainTexturePack;
import toolbox.MousePicker;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class MainGameLoop {
    public static void main(String[] args) {
        List<Entity> entities = new ArrayList<>();

        DisplayManager.createDisplay();
        Loader loader = new Loader();

        RawModel playerModel = OBJLoader.loadObjModel("person", loader);
        TexturedModel playerTex = new TexturedModel(playerModel, new ModelTexture(loader.loadTexture("playerTexture")));
        Player player = new Player(playerTex, new Vector3f(300, 0, -200), 0, 0, 0, 1);
        entities.add(player);
        Camera camera = new Camera(player);

        MasterRenderer renderer = new MasterRenderer(loader, camera);


        //************************** TERRAIN ****************************************
        TerrainTexture backgroundTexture = new TerrainTexture(loader.loadTexture("grassy"));
        TerrainTexture rTexture = new TerrainTexture(loader.loadTexture("dirt"));
        TerrainTexture gTexture = new TerrainTexture(loader.loadTexture("pinkFlowers"));
        TerrainTexture bTexture = new TerrainTexture(loader.loadTexture("path"));

        TerrainTexturePack texturePack = new TerrainTexturePack(backgroundTexture, rTexture, gTexture, bTexture);
        TerrainTexture blendMap = new TerrainTexture(loader.loadTexture("blendMap"));

        Terrain terrain = new Terrain(0, -1, loader, texturePack, blendMap, "heightmap");


        //********************************** TEXTURES  ******************************************
        ModelData data = OBJFileLoader.loadOBJ("tree");
        RawModel model = loader.loadToVAO(data.getVertices(), data.getTextureCoords(), data.getNormals(), data.getIndices());

        TexturedModel treeModel = new TexturedModel(model, new ModelTexture(loader.loadTexture("tree")));
        TexturedModel grassModel = new TexturedModel(OBJLoader.loadObjModel("grassModel", loader),
                new ModelTexture(loader.loadTexture("grassTexture"))
                        .setHasTransparency(true)
                        .setUseFakeLighting(true));
        TexturedModel flowerModel = new TexturedModel(OBJLoader.loadObjModel("grassModel", loader),
                new ModelTexture(loader.loadTexture("flower"))
                        .setHasTransparency(true)
                        .setUseFakeLighting(true));
        ModelTexture fernTextureAtlas = new ModelTexture(loader.loadTexture("fern"));
        fernTextureAtlas.setNumberOfRows(2);
        TexturedModel fernModel = new TexturedModel(OBJLoader.loadObjModel("fern", loader),
                fernTextureAtlas
                        .setHasTransparency(true));
        TexturedModel polyTreeModel = new TexturedModel(OBJLoader.loadObjModel("lowPolyTree", loader),
                new ModelTexture(loader.loadTexture("lowPolyTree")));

        TexturedModel boxModel = new TexturedModel(OBJLoader.loadObjModel("box", loader),
                new ModelTexture(loader.loadTexture("box")));

        TexturedModel cubeModel = new TexturedModel(loader.loadToVAO(Cube.vertices, Cube.textureCoords, Cube.normals, Cube.indices),
                new ModelTexture(loader.loadTexture("mud")));

        TexturedModel sunModel = new TexturedModel(loader.loadToVAO(Cube.vertices, Cube.textureCoords, Cube.normals, Cube.indices),
                new ModelTexture(loader.loadTexture("white"))
                        .setUseFakeLighting(true));

        TexturedModel lamp = new TexturedModel(OBJLoader.loadObjModel("lamp", loader),
                new ModelTexture(loader.loadTexture("lamp"))
                        .setUseFakeLighting(true));

        // #################################################### Entities ##################################################################
        // Entity box = new Entity(boxModel, new Vector3f(400, terrain.getHeightOfTerrain(400, -20) + 10, -20), 0, 45, 0, 10);
        Entity box1 = new Entity(boxModel, new Vector3f(100, terrain.getHeightOfTerrain(100, -50) + 10, -50), 0, 45, 0, 10);
        Entity cube = new Entity(cubeModel, new Vector3f(400, terrain.getHeightOfTerrain(400, -20) + 5, -20), 0, 45, 0, 10);
        entities.add(box1);
        entities.add(cube);

        Random random = new Random(676452);
        float x = random.nextFloat() * 800 - 400;
        float z = random.nextFloat() * -600;
        float y = terrain.getHeightOfTerrain(x, z);
        entities.add(new Entity(grassModel, new Vector3f(x, y, z), 0, 0, 0, 1.8f));
        for (int i = 0; i < 400; i++) {
            if (i % 7 == 0) {
                x = random.nextFloat() * 800 - 400;
                z = random.nextFloat() * -600;
                y = terrain.getHeightOfTerrain(x, z);
                entities.add(new Entity(grassModel, new Vector3f(x, y, z), 0, 0, 0, 1.8f));
                x = random.nextFloat() * 800 - 400;
                z = random.nextFloat() * -600;
                y = terrain.getHeightOfTerrain(x, z);
                entities.add(new Entity(flowerModel, new Vector3f(x, y, z), 0, 0, 0, 2.3f));
            }
            if (i % 2 == 0) {
                x = random.nextFloat() * 800 - 400;
                z = random.nextFloat() * -600;
                y = terrain.getHeightOfTerrain(x, z);
                entities.add(new Entity(fernModel, random.nextInt(4), new Vector3f(x, y, z),
                        0, random.nextFloat() * 360, 0, random.nextFloat() * 1.5f + 0.5f));
            }
            if (i % 10 == 0) {
                x = random.nextFloat() * 800 - 400;
                z = random.nextFloat() * -600;
                y = terrain.getHeightOfTerrain(x, z);
                entities.add(new Entity(polyTreeModel, new Vector3f(x, y, z),
                        0, random.nextFloat() * 360, 0, random.nextFloat() * 0.1f + 0.6f));
                x = random.nextFloat() * 800 - 400;
                z = random.nextFloat() * -600;
                y = terrain.getHeightOfTerrain(x, z);
                entities.add(new Entity(treeModel, new Vector3f(x, y, z),
                        0, 0, 0, random.nextFloat() * 1 + 4));
            }
        }

        // ##################################################  Lights ##################################################################

        List<Light> lights = new ArrayList<>();
        x = 1_000_000;
        z = -1_500_000;
        y = 1_000_000;
        int lightOffsetDY = 15;
        Light sun = (Light) new Light(new Vector3f(x, y, z), new Vector3f(1.3f, 1.3f, 1.3f))
                .setTexturedModel(sunModel)
                .setLightOffsetDY(lightOffsetDY)
                .setEntityScale(10);

        lights.add(sun);

        x = 185;
        z = -293;
        y = terrain.getHeightOfTerrain(x, z);
        lights.add(new Light(new Vector3f(x, y, z), new Vector3f(2, 0, 0), new Vector3f(1, 0.01f, 0.002f))
                .setTexturedModel(lamp)
                .setLightOffsetDY(lightOffsetDY));
        x = 370;
        z = -300;
        y = terrain.getHeightOfTerrain(x, z);
        lights.add(new Light(new Vector3f(x, y, z), new Vector3f(0, 2, 2), new Vector3f(1, 0.01f, 0.002f))
                .setTexturedModel(lamp)
                .setLightOffsetDY(lightOffsetDY));
        x = 293;
        z = -305;
        y = terrain.getHeightOfTerrain(x, z);

        lights.add(new Light(new Vector3f(x, y, z), new Vector3f(2, 2, 0), new Vector3f(1, 0.01f, 0.002f))
                .setTexturedModel(lamp)
                .setLightOffsetDY(lightOffsetDY));

        entities.addAll(lights);

        MousePicker picker = new MousePicker(renderer.getProjectionMatrix(), camera, terrain);

        List<GuiTexture> guiTextures = new ArrayList<>();
        GuiTexture shadowMap = new GuiTexture(renderer.getShadowMapTexture(), new Vector2f(0.5f, 0.5f), new Vector2f(0.5f, 0.5f));
        //guiTextures.add(shadowMap);


        List<GuiTexture> guis = new ArrayList<>();
        GuiTexture gui = new GuiTexture(loader.loadTexture("health"), new Vector2f(-0.6f, -0.9f),
                new Vector2f(0.25f, 0.25f));
        // guis.add(gui);

        GuiRenderer guiRenderer = new GuiRenderer(loader);


        // ########################################  GAME LOOP ###################################################

        while (!Display.isCloseRequested()) {
            player.move(terrain);
            camera.move();
            picker.update();


            renderer.renderShadowMap(entities, sun);
            // System.out.println(picker.getCurrentRay());
            Vector3f terrainPoint = picker.getCurrentTerrainPoint();
            if (terrainPoint != null) {
                lights.get(1).setLightPosition(terrainPoint);
                //System.out.printf("X %s, Y %s, Z %s\n", terrainPoint.x, terrainPoint.y, terrainPoint.z);
            }


            //renderer.processEntity(player);
            renderer.processTerrain(terrain);

            //renderer.processEntity(cube);
            //renderer.processEntity(box1);
            for (Entity entity : entities) {
                renderer.processEntity(entity);
            }
            renderer.render(lights, camera);
            guiRenderer.render(guiTextures);
            DisplayManager.updateDisplay();
        }
        guiRenderer.cleanUp();
        renderer.cleanUp();
        loader.cleanUp();
        DisplayManager.closeDisplay();
    }
}
