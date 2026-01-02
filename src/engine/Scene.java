package engine;

import java.util.ArrayList;
import java.util.List;

public class Scene {

    private List<GameObject> objects = new ArrayList<>();

    public void add(GameObject obj) {
        objects.add(obj);
    }

    public void update(double delta) {
        for (GameObject obj : objects) {
            obj.update(delta);
        }
    }

    public void render() {
        for (GameObject obj : objects) {
            obj.render();
        }
    }
}
