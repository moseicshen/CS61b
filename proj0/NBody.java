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

        StdDraw.enableDoubleBuffering();
        
        //initial view
        StdDraw.setScale(-radius, radius);
        StdDraw.clear();
        StdDraw.picture(0,0,"images/starfield.jpg");
        for(Planet planet : planets){
            planet.draw();
        }

        //animation
        double t = 0;
        while(t <= T){
            double[] xForces = new double[planets.length];
            double[] yForces = new double[planets.length];
            int i = 0;
            for(Planet planet : planets){
                xForces[i] = planet.calcNetForceExertedByX(planets);
                yForces[i] = planet.calcNetForceExertedByY(planets);
                i++;
            }

            i = 0;
            for(Planet planet : planets){
                planet.update(dt, xForces[i], yForces[i]);
                i++;
            }

            StdDraw.picture(0,0,"images/starfield.jpg");
            for(Planet planet : planets){
                planet.draw();
            }

            StdDraw.show();
            StdDraw.pause(10);
            t += dt;
        }

        StdOut.printf("%d\n", planets.length);
        StdOut.printf("%.2e\n", radius);
        for (int i = 0; i < planets.length; i++) {
            StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
            planets[i].xxPos, planets[i].yyPos, planets[i].xxVel,
            planets[i].yyVel, planets[i].mass, planets[i].imgFileName);   
        }
    }
}
