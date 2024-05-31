package physics.geometry;

import physics.entity.Brick;
import utils.GameConstants;
import physics.entity.Ball;

/**
 * Classe utilitaire pour les calculs de physique
 */

public class PhysicTools {

    public static boolean checkCollision(Coordinates cNew,Coordinates c, Vector d, double radius, Segment segment, Rotation rotation) {
        double x1 = segment.getStart().getX();
        double y1 = segment.getStart().getY();
        double x2 = segment.getEnd().getX();
        double y2 = segment.getEnd().getY();
        double dx = x2 - x1;
        double dy = y2 - y1;
        double len = Math.sqrt(dx * dx + dy * dy);
        dx /= len;
        dy /= len;
    
        double t = dx * (cNew.getX() - x1) + dy * (cNew.getY() - y1);
    
        // Si le point est proche des rebords du segment
        double closestX, closestY;
        if (t < 0) {
            closestX = x1;
            closestY = y1;
        } else if (t > len) {
            closestX = x2;
            closestY = y2;
        } else {
            closestX = x1 + t * dx;
            closestY = y1 + t * dy;
        }
    
        double distance = Math.sqrt((cNew.getX() - closestX) * (cNew.getX() - closestX) +
                                    (cNew.getY() - closestY) * (cNew.getY() - closestY));
    
        if (distance <= radius) {
            // Calculer le vecteur normal
            double normalX = cNew.getX() - closestX;
            double normalY = cNew.getY() - closestY;
            double lenNormal = Math.sqrt(normalX * normalX + normalY * normalY);
            normalX /= lenNormal;
            normalY /= lenNormal;
    
            // Repositionner la balle juste en dehors du segment
            cNew.setX(c.getX()); 
            cNew.setY(c.getY());
            // Corriger le vecteur de direction après la collision
            double d1 = d.getX() * normalX + d.getY() * normalY;
            d.setX(d.getX() - 2 * d1 * normalX);
            d.setY(d.getY() - 2 * d1 * normalY + (rotation.getEffect() / 90.0) * normalY);
            
            return true;
        }
        return false;
    }
    
    

    public static boolean checkCollision(Ball b , Segment s){
        return checkCollision(b.getNewCoordinates(),b.getC(),b.getDirection(),b.getRadius(),s,b.getRotation());
    }

    public static boolean checkCollision(Coordinates cNew,Coordinates c ,Vector d, double radius ,Brick b,Rotation rotation){
        for (Segment s : b.getSegments()) {
            if(checkCollision(cNew,c,d,radius,s,rotation)){
                return true;
            }
        }
        return false;
    }

    public static boolean intersectBrick(Coordinates c, int radius, Brick b) {

        double circleDistance_x = Math.abs(c.getX() - b.getC().getX() - GameConstants.BRICK_WIDTH / 2);
        double circleDistance_y = Math.abs(c.getY() - b.getC().getY() - GameConstants.BRICK_HEIGHT / 2);

        if (circleDistance_x > (GameConstants.BRICK_WIDTH / 2 + radius)) {
            return false;
        }
        if (circleDistance_y > (GameConstants.BRICK_HEIGHT / 2 + radius)) {
            return false;
        }

        if (circleDistance_x <= (GameConstants.BRICK_WIDTH / 2)) {
            return true;
        }
        if (circleDistance_y <= (GameConstants.BRICK_HEIGHT / 2)) {
            return true;
        }

        double cornerDistance_sq = (circleDistance_x - GameConstants.BRICK_WIDTH / 2)
                * (circleDistance_x - GameConstants.BRICK_WIDTH / 2)
                + (circleDistance_y - GameConstants.BRICK_HEIGHT / 2)
                        * (circleDistance_y - GameConstants.BRICK_HEIGHT / 2);

        return (cornerDistance_sq <= (radius * radius));
    }

    
    
}
