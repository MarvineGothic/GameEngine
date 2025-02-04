package textures;

public class ModelTexture {
    private int textureID;

    private float shineDamper = 1;
    private float reflectivity = 0;

    private boolean hasTransparency = false;
    private boolean useFakeLighting = false;

    private int numberOfRows = 1;

    public ModelTexture(int textureID) {
        this.textureID = textureID;
    }

    public int getID() {
        return textureID;
    }

    public boolean isHasTransparency() {
        return hasTransparency;
    }

    public ModelTexture setHasTransparency(boolean hasTransparency) {
        this.hasTransparency = hasTransparency;
        return this;
    }

    public boolean isUseFakeLighting() {
        return useFakeLighting;
    }

    public ModelTexture setUseFakeLighting(boolean useFakeLighting) {
        this.useFakeLighting = useFakeLighting;
        return this;
    }

    public void setTextureID(int textureID) {
        this.textureID = textureID;
    }

    public float getShineDamper() {
        return shineDamper;
    }

    public void setShineDamper(float shineDamper) {
        this.shineDamper = shineDamper;
    }

    public float getReflectivity() {
        return reflectivity;
    }

    public void setReflectivity(float reflectivity) {
        this.reflectivity = reflectivity;
    }

    public int getNumberOfRows() {
        return numberOfRows;
    }

    public void setNumberOfRows(int numberOfRows) {
        this.numberOfRows = numberOfRows;
    }
}
