package engine;

import java.util.*;

public class Scene {
    private final List<GameObject> objects = new ArrayList<>();

    public void add(GameObject o){ objects.add(o); }

    public void update(double dt){ objects.forEach(o->o.update(dt)); }
    public void render(){ objects.forEach(GameObject::render); }
}
