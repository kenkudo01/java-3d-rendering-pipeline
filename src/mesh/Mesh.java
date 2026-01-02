package mesh;

import java.util.ArrayList;
import java.util.List;

public class Mesh {
    public List<Vertex> vertices = new ArrayList<>();
    public List<Edge> edges = new ArrayList<>();
    public List<Face> faces = new ArrayList<>();

    public static Mesh createCube() {
        Mesh m = new Mesh();

        // vertices
        Vertex v0 = new Vertex(-1, -1, -1);
        Vertex v1 = new Vertex( 1, -1, -1);
        Vertex v2 = new Vertex( 1,  1, -1);
        Vertex v3 = new Vertex(-1,  1, -1);
        Vertex v4 = new Vertex(-1, -1,  1);
        Vertex v5 = new Vertex( 1, -1,  1);
        Vertex v6 = new Vertex( 1,  1,  1);
        Vertex v7 = new Vertex(-1,  1,  1);

        m.vertices.addAll(List.of(v0,v1,v2,v3,v4,v5,v6,v7));

        // edges（ワイヤーフレーム用）
        m.edges.addAll(List.of(
                new Edge(v0,v1), new Edge(v1,v2), new Edge(v2,v3), new Edge(v3,v0),
                new Edge(v4,v5), new Edge(v5,v6), new Edge(v6,v7), new Edge(v7,v4),
                new Edge(v0,v4), new Edge(v1,v5), new Edge(v2,v6), new Edge(v3,v7)
        ));

        // faces（三角形）
        m.faces.addAll(List.of(
                new Face(v0,v1,v2), new Face(v0,v2,v3),
                new Face(v4,v5,v6), new Face(v4,v6,v7)
                // 他の面も同様
        ));

        return m;
    }
}
