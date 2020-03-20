import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Trey Jenkins on March 19, 2020 at 23:46
 */
public class MazeRunner {
    // Mazes can be generated here:
    // https://keesiemeijer.github.io/maze-generator/
    // Set thickness to one

    static final String mazeLocation = "Maze4.png";

    public static void main(String[] args) throws IOException {

        File file = new File(mazeLocation);
        BufferedImage image = ImageIO.read(file);

        // Load in the maze
        System.out.println("Loading the maze");
        Maze maze = new Maze(image);
        // Solve the maze
        System.out.println("Solving the maze");
        ArrayList<int[]> solvePath = maze.solve();
        //System.out.println(maze);

        System.out.println("Solved in " + solvePath.size() + " steps");




        File outputfile = new File("output-" + mazeLocation);
        ImageIO.write(maze.getImage(), "png", outputfile);



        //System.out.println(maze.getEntrypoint()[0] + " " + maze.getEntrypoint()[1]);
        //System.out.println(maze.getExitpoint()[0] + " " + maze.getExitpoint()[1]);
    }
}
