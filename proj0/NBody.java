public class NBody {
    public static double readRadius(String fileName){
        In in = new In(fileName);
        int planet_num = in.readInt();
        double radius = in.readDouble();
        return radius;
    }

    public static Planet[] readPlanets(String fileName){
        In in = new In(fileName);
        int num = in.readInt();
        Planet[] planetList = new Planet[num];
        double radius = in.readDouble();
        int i = 0;
        while(i<num){
            double xxPos = in.readDouble();
            double yyPos = in.readDouble();
            double xxVel = in.readDouble();
            double yyVel = in.readDouble();
            double mass = in.readDouble();
            String imgFileName = in.readString();
            planetList[i] = new Planet(xxPos,yyPos,xxVel,yyVel,mass,imgFileName);
            i++;
        }
        return planetList;
    }

    public static void main(String[] args) {
        double T = Double.parseDouble(args[0]);
        double dt = Double.parseDouble(args[1]);
        String filename = args[2];
        double radius = NBody.readRadius(filename);
        Planet[] planetList = NBody.readPlanets(filename);

        //draw the image
        StdDraw.setXscale(-radius, radius);
        StdDraw.setYscale(-radius,radius);
        StdDraw.enableDoubleBuffering();


        double time =0;
        double[] xForces = new double[planetList.length];
        double[] yForces = new double[planetList.length];
        while(time <= T){
            for(int i =0; i< planetList.length; i++){
                xForces[i] = planetList[i].calcNetForceExertedByX(planetList);
                yForces[i] = planetList[i].calcNetForceExertedByY(planetList);
            }
            for(int i = 0; i<planetList.length; i++){
                planetList[i].update(dt, xForces[i], yForces[i]);
            }

            StdDraw.picture(0,0,"images/starfield.jpg");
            for(Planet p:planetList){
                p.draw();
            }
            StdDraw.show();
            StdDraw.pause(10);
            time += dt;
        }

        StdOut.printf("%d\n", planetList.length);
        StdOut.printf("%.2e\n", radius);
        for (int i = 0; i < planetList.length; i++) {
            StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                    planetList[i].xxPos, planetList[i].yyPos, planetList[i].xxVel,
                    planetList[i].yyVel, planetList[i].mass, planetList[i].imgFileName);
        }

    }


}
