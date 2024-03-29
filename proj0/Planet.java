public class Planet {

    private double xxPos;
    private double yyPos;
    private double xxVel;
    private double yyVel;
    private double mass;
    private String imgFileName;

    static double G = 6.67e-11;

    public Planet(double xP, double yP, double xV, double yV, double m, String img){
        this.xxPos = xP;
        this.yyPos = yP;
        this.xxVel = xV;
        this.yyVel = yV;
        this.mass = m;
        this.imgFileName = img;
    }

    public Planet(Planet p){
        this.xxPos = p.xxPos;
        this.yyPos = p.yyPos;
        this.xxVel = p.xxVel;
        this.yyVel = p.yyVel;
        this.mass = p.mass;
        this.imgFileName = p.imgFileName;
    }

    public double calcDistance(Planet p){
        return Math.sqrt(Math.pow((this.xxPos - p.xxPos),2) + Math.pow((this.yyPos - p.yyPos),2));
    }

    public double calcForceExertedBy(Planet p){
        double distance = this.calcDistance(p);
        return G*((this.mass * p.mass)/Math.pow(distance,2));
    }

    public double calcForceExertedByX(Planet p){
        double distance = this.calcDistance(p);
        double force = this.calcForceExertedBy(p);
        return force * ((p.xxPos - this.xxPos)/distance);
    }

    public double calcForceExertedByY(Planet p){
        double distance = this.calcDistance(p);
        double force = this.calcForceExertedBy(p);
        return force * ((p.yyPos - this.yyPos)/distance);
    }

    public double calcNetForceExertedByX(Planet[] pList){
        double netForce = 0;
        for(Planet p : pList){
            if(!this.equals(p)){
                netForce += this.calcForceExertedByX(p);
            }
        }
        return netForce;
    }

    public double calcNetForceExertedByY(Planet[] pList){
        double netForce = 0;
        for(Planet p : pList){
            if(!this.equals(p)){
                netForce += this.calcForceExertedByY(p);
            }
        }
        return netForce;
    }

    public void update(double dt, double fX, double fY){
        double a_x = fX/this.mass;
        double a_y = fY/this.mass;
        this.xxVel += a_x * dt;
        this.yyVel += a_y * dt;
        this.xxPos += this.xxVel * dt;
        this.yyPos += this.yyVel * dt;
    }

    public void draw(){
        StdDraw.picture(xxPos,yyPos, "images/"+imgFileName);
    }
}

