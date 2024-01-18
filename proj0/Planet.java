
public class Planet{
    public double xxPos;
    public double yyPos;
    public double xxVel;
    public double yyVel;
    public double mass;
    public String imgFileName;
    public static final double g = 6.67e-11;

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

    public double calcDistance(Planet other){
        double distSqrt = (other.xxPos - this.xxPos)*(other.xxPos - this.xxPos) +
                        (other.yyPos - this.yyPos)*(other.yyPos - this.yyPos);
        return Math.sqrt(distSqrt);
    }

    public double calcForceExertedBy(Planet other){
        double dist = calcDistance(other);
        return (g * this.mass * other.mass)/(dist*dist);
    }

    public double calcForceExertedByX(Planet other){
        return (calcForceExertedBy(other) * (other.xxPos - this.xxPos))/calcDistance(other);
    }

    public double calcForceExertedByY(Planet other){
        return (calcForceExertedBy(other) * (other.yyPos - this.yyPos))/calcDistance(other);
    }

    public double calcNetForceExertedByX(Planet[] others){
        double sum = 0;
        for(Planet other: others){
            if(!this.equals(other)){
                sum += calcForceExertedByX(other);
            }
        }
        return sum;
    }

    public double calcNetForceExertedByY(Planet[] others){
        double sum = 0;
        for(Planet other: others){
            if(!this.equals(other)){
                sum += calcForceExertedByY(other);
            }
        }
        return sum;
    }

    public void update(double dt, double fX, double fY){
        double ax = fX / this.mass;
        double ay = fY / this.mass;

        this.xxVel += dt * ax;
        this.yyVel += dt * ay;

        this.xxPos += dt * this.xxVel;
        this.yyPos += dt * this.yyVel;
    }

    public void draw(){
        StdDraw.picture(xxPos, yyPos, "images/" + imgFileName);
    }
}