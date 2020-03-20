import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 * Created by Trey Jenkins on March 19, 2020 at 23:52
 */
public class Maze {

    BufferedImage image;
    int[][] maze;

    int entrypointX = -1;
    int entrypointY = -1;
    int exitpointX = -1;
    int exitpointY = -1;

    ArrayList<int[]> solvePath = new ArrayList<>();

    public Maze(BufferedImage image) {
        this.image = image;
        maze = new int[image.getWidth()][image.getHeight()];

        // Read image into maze
        for (int x = 0; x < image.getWidth(); x++) {
            for (int y = 0; y < image.getHeight(); y++) {
                if (Math.abs(image.getRGB(y, x)) < 100000) {
                    maze[x][y] = 0;
                } else {
                    maze[x][y] = 1;
                }
            }
        }

        // Find entry and exit points
        // Search first row
        for (int x = 0; x < maze[0].length; x++) {
            if (maze[0][x] == 0) {
                if (entrypointX == -1) {
                    entrypointX = 0;
                    entrypointY = x;
                } else if (exitpointX == -1) {
                    exitpointX = 0;
                    exitpointY = x;
                }
            }
        }
        // Search last row
        for (int x = 0; x < maze[0].length; x++) {
            if (maze[maze.length-1][x] == 0) {
                if (entrypointX == -1) {
                    entrypointX = maze.length-1;
                    entrypointY = x;
                } else if (exitpointX == -1) {
                    exitpointX = maze.length-1;
                    exitpointY = x;
                }
            }
        }
        // Search first column
        for (int x = 0; x < maze.length; x++) {
            if (maze[x][0] == 0) {
                if (entrypointX == -1) {
                    entrypointX = x;
                    entrypointY = 0;
                } else if (exitpointX == -1) {
                    exitpointX = x;
                    exitpointY = 0;
                }
            }
        }
        // Search last column
        for (int x = 0; x < maze.length; x++) {
            if (maze[x][maze[0].length-1] == 0) {
                if (entrypointX == -1) {
                    entrypointX = x;
                    entrypointY = maze[0].length-1;
                } else if (exitpointX == -1) {
                    exitpointX = x;
                    exitpointY = maze[0].length-1;
                }
            }
        }
        maze[entrypointX][entrypointY] = 2;
        maze[exitpointX][exitpointY] = 3;
    }


    public int[] getEntrypoint() {
        return new int[] {entrypointX, entrypointY};
    }

    public int[] getExitpoint() {
        return new int[] {exitpointX, exitpointX};
    }

    private boolean rsolve(int x, int y) {
        maze[x][y] = 4;
        image.setRGB(y, x, new Color(255, 255, 0).getRGB());


        try {
            if (maze[x + 1][y] == 3) {
                solvePath.add(new int[] {x,y});
                solvePath.add(new int[] {x+1,y});
                return true;
            }
            if (maze[x - 1][y] == 3) {
                solvePath.add(new int[] {x,y});
                solvePath.add(new int[] {x-1,y});
                return true;
            }
            if (maze[x][y + 1] == 3) {
                solvePath.add(new int[] {x,y});
                solvePath.add(new int[] {x,y+1});
                return true;
            }
            if (maze[x][y - 1] == 3) {
                solvePath.add(new int[] {x,y});
                solvePath.add(new int[] {x,y-1});
                return true;
            }
        } catch (Exception e) {}

        try {
            if (maze[x + 1][y] == 0)
                if (rsolve(x + 1, y)) {solvePath.add(new int[] {x,y}); return true;}
            if (maze[x - 1][y] == 0)
                if (rsolve(x - 1, y)) {solvePath.add(new int[] {x,y}); return true;}
            if (maze[x][y + 1] == 0)
                if (rsolve(x, y + 1)) {solvePath.add(new int[] {x,y}); return true;}
            if (maze[x][y - 1] == 0)
                if (rsolve(x, y - 1)) {solvePath.add(new int[] {x,y}); return true;}
        } catch (Exception e) {}

        return false;
    }

    public ArrayList<int[]> solve() {
        rsolve(entrypointX, entrypointY);
        return solvePath;
    }

    public BufferedImage getImage() {

        if (solvePath.size() > 0) {
            for (int[] xy:solvePath) {
                image.setRGB(xy[1], xy[0], new Color(0, 255, 0).getRGB());
            }
        }


        return image;
    }

    @Override
    public String toString() {
        String str = "";
        for (int x = 0; x < maze.length; x++) {
            for (int y = 0; y < maze[x].length; y++) {
                str += maze[x][y];
            }
            str += "\n";
        }
        return str;
    }

}
