
public class NBody {
    public static double readRadius(String file){
        In in  = new In(file);
        int num = in.readInt();
        double radius = in.readDouble();
        return radius;
    }
    public static Planet[] readPlanets(String file){
        In in = new In(file);
        int num = in.readInt();
        Planet[] Planet_list = new Planet[num];
        double radius = in.readDouble();
        for(int i =0; i<num;i+=1){
            double x = in.readDouble();
            double y = in.readDouble();
            double x_vel = in.readDouble();
            double y_vel = in.readDouble();
            double mass = in.readDouble();
            String img = in.readString();
            Planet_list[i] = new Planet(x, y,x_vel,y_vel,mass,img);
        }
        return Planet_list;
    }
    public static void main(String[] args){
        double T = Double.parseDouble(args[0]);
        double dt = Double.parseDouble(args[1]);
        String filename = new String(args[2]);
        Planet[] planets = readPlanets(filename);
        double radius = readRadius(filename);
        StdDraw.setXscale(-radius,radius);
        StdDraw.setYscale(-radius,radius);
        StdDraw.enableDoubleBuffering();
        int time = 0;
        int num = planets.length;
        while(time <= T) {
            double[] xForces = new double[num];
            double[] yForces = new double[num];
            for (int i = 0; i < num; i += 1) {
                xForces[i] = planets[i].calcNetForceExertedByX(planets);
                yForces[i] = planets[i].calcNetForceExertedByY(planets);
            }
            for (int k = 0; k < num; k += 1) {
                planets[k].update(dt, xForces[k], yForces[k]);
            }
            StdDraw.picture(0, 0, "images/starfield.jpg");
            for (int k = 0; k < planets.length; k += 1) {
                planets[k].draw();
            }
            StdDraw.show();
            StdDraw.pause(10);
            time += dt;
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
