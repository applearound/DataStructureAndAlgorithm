package me.zyz.dsal.collection.graph;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AdjMatrix {
    private static final Pattern EDGE_INFO_PATTERN = Pattern.compile("^(\\d+) (\\d+)$");

    private final int vertex;
    private final int edge;
    private final int[][] adj;

    public AdjMatrix(final String filePath) {
        try (final BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath))) {
            final String vertexLine = bufferedReader.readLine();

            this.vertex = Integer.parseInt(vertexLine);
            this.adj = new int[vertex][vertex];

            final String edgeLine = bufferedReader.readLine();

            this.edge = Integer.parseInt(edgeLine);

            for (int i = 0; i < edge; i++) {
                final String edgeInfoLine = bufferedReader.readLine();
                final Matcher matcher = EDGE_INFO_PATTERN.matcher(edgeInfoLine);

                if (matcher.matches()) {
                    final int vertexFirst = Integer.parseInt(matcher.group(1));
                    final int vertexSecond = Integer.parseInt(matcher.group(2));

                    adj[vertexFirst][vertexSecond] = 1;
                    adj[vertexSecond][vertexFirst] = 1;
                } else {
                    throw new RuntimeException("Format Error!");
                }
            }
        } catch (final IOException e) {
            throw new RuntimeException("Open File Failed", e);
        } catch (final Exception e) {
            throw new RuntimeException("Parse Failed", e);
        }
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();

        sb.append(String.format("V = %d, E = %d%n", vertex, edge));
        for (int i = 0; i < vertex; i++) {
            for (int j = 0; j < vertex; j++) {
                sb.append(adj[i][j]);
                if (j != vertex - 1) {
                    sb.append(' ');
                }
            }
            sb.append('\n');
        }

        return sb.toString();
    }
}
