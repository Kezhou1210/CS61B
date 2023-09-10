import java.util.spi.TimeZoneNameProvider;

public class Planet {
    public double xxPos;
    public double yyPos;
    public double xxVel;
    public double yyVel;
    public double mass;
    public String imgFileName;
    private double G = 6.67e-11;
    
    public Planet(double xP, double yP, double xV,
                  double yV, double m, String img){
        xxPos = xP;
        yyPos = yP;
        xxVel = xV;
        yyVel = yV;
        mass = m;
        imgFileName = img;
    }
    public Planet(Planet p){
        xxPos = p.xxPos;
        yyPos = p.yyPos;
        xxVel = p.xxVel;
        yyVel = p.yyVel;
        mass = p.mass;
        imgFileName = p.imgFileName;
    }
    public double calcDistance(Planet supplied_planet){
        double distance;
        double x;
        double y;
        x = this.xxPos - supplied_planet.xxPos;
        y = this.yyPos - supplied_planet.yyPos;
        distance = Math.sqrt(x*x +y*y);
        return distance;
    }
    public double calcForceExertedBy(Planet exert_planet){
        double force;
        force = G * this.mass * exert_planet.mass / (this.calcDistance(exert_planet) * this.calcDistance(exert_planet));
        return force;
    }
    public double calcForceExertedByX(Planet exert_planet){
        double x;
        x = exert_planet.xxPos - this.xxPos;
        double force_x;
        force_x = this.calcForceExertedBy(exert_planet) * x/this.calcDistance(exert_planet);
        return force_x;
    }
    public double calcForceExertedByY(Planet exert_planet){
        double y;
        y = exert_planet.yyPos - this.yyPos;
        double force_y;
        force_y = this.calcForceExertedBy(exert_planet) * y/this.calcDistance(exert_planet);
        return force_y;
    }
    public double calcNetForceExertedByX(Planet[] exert_planet){
        double netforce_x = 0;
        for(int i =0; i< exert_planet.length;i+=1){
            if(!this.equals(exert_planet[i])){
                netforce_x = netforce_x + this.calcForceExertedByX(exert_planet[i]);
            }
        }
        return netforce_x;
    }
    public double calcNetForceExertedByY(Planet[] exert_planet){
        double netforce_y = 0;
        for(int i =0; i< exert_planet.length;i+=1){
            if(!this.equals(exert_planet[i])){
                netforce_y = netforce_y + this.calcForceExertedByY(exert_planet[i]);
            }
        }
        return netforce_y;
    }
    public void update(double time, double netforce_x, double netforce_y){
        double a_x;
        double a_y;
        a_x = netforce_x/this.mass;
        a_y = netforce_y/this.mass;
        xxVel= this.xxVel + a_x * time;
        yyVel = this.yyVel + a_y * time;
        this.xxPos = this.xxPos + xxVel * time;
        this.yyPos = this.yyPos + yyVel * time;
    }
    public void draw(){
        StdDraw.picture(xxPos, yyPos, "images/" + imgFileName);
    }
}