public class NBody {
    public static double readRadius(String path){
        In in = new In(path);
        in.readInt();
        return in.readDouble();
    }

    public static Planet[] readPlanets(String path){
        In in = new In(path);
        int planetsCount = in.readInt();
        in.readDouble();
        Planet[] planets = new Planet[planetsCount];
        for(int i = 0; i < planetsCount; i++){
            Planet p = new Planet(in.readDouble(), in.readDouble(), in.readDouble(), in.readDouble(),
                            in.readDouble(), in.readString());
            planets[i] = p;
        }
        return planets;
    }

    public static void main(String args[]){
        double T = Double.parseDouble(args[0]);
        double dt = Double.parseDouble(args[1]);
        String filename = args[2];
        
        double radius = readRadius(filename);
        Planet[] planets = readPlanets(filename);

        StdDraw.setScale(-radius, radius);
        StdDraw.clear();
        StdDraw.picture(0,0,"images/starfield.jpg");
        for(Planet planet : planets){
            planet.draw();
        }
    }
}
