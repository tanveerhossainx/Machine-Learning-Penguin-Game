import processing.core.*; 
import processing.data.*; 
import processing.event.*; 
import processing.opengl.*; 

import oscP5.*; 
import netP5.*; 

import java.util.HashMap; 
import java.util.ArrayList; 
import java.io.File; 
import java.io.BufferedReader; 
import java.io.PrintWriter; 
import java.io.InputStream; 
import java.io.OutputStream; 
import java.io.IOException; 

public class Pinguin_Game_thoss003 extends PApplet {

/* OpenProcessing Tweak of *@*http://www.openprocessing.org/sketch/17115*@* */
/* !do not delete the line above, required for linking your tweak if you upload again */
/* Modified by Rebecca Fiebrink to work with Wekinator */
/* Receives DTW commands /output_1, /output_2, /output_3 (the default messages for 1st 3 gestures) on port 12000 */




OscP5 oscP5;
NetAddress dest;

final int WIDTH = 30;
final int HEIGHT = 23;
int[][] level = new int[HEIGHT][WIDTH];

int rightCount = 0;
int leftCount = 0;
int scrollPos =0;
//////
//game
int score = 0;
//int lives = 3;
boolean isLost = false;
boolean isWon = false;
int realPos;

//////


Player p1;
Clouds cl;
Mountains m1;
Enamy e1;
Fish f1;
//booleans for key presses to get a simple yes or no press and 
//to not have to worry about the thing
boolean right = false, left = false, up = false;

public void setup() {
  oscP5 = new OscP5(this,12000); //listen for OSC messages on port 12000 (Wekinator default)
  dest = new NetAddress("127.0.0.1",6448); //send messages back to Wekinator on port 6448, localhost (this machine) (default)
  
  p1 = new Player(WIDTH*8,HEIGHT*8); //put the player in the middle of the window
  cl = new Clouds();
  m1 = new Mountains();
  e1 = new Enamy();
  f1 = new Fish();
}
public void draw() {
  background(135,206,235);
 
    
  cl.drawCloud();
  text("lives="+p1.lives/10,(width/2)/10,(height/2)/2);
  m1.drawMountain();
  drawLevel();
  p1.update();
  p1.show();
  f1.drawFish(); 
  // Enemy
    push();
    //translate(30,100);
    e1.drawEnamy();
    e1.move();
    pop();
  //Fish
    push();
    f1.drawFish();
    f1.move();
    pop();
   //game
   e1.checkCharCollision();
   f1.checkCharCollision();
   
    if (p1.lives/10==0){
    background(255);
    fill(255,0,0);
    rect(width/3,height/2,200,10);
    text( "GAME OVER",width/3,height/2);
    redraw();
  }
  else if(p1.lives/10==5){
    background(255);
    fill(0,155,155);
    rect(width/3,height/2,200,10);
    text( "YOU WIN",width/3,height/2);
    redraw();
  }
 //  f1.checkFish();
   realPos = p1.pinguin_x - scrollPos;
   
  // m1.liveBar();
  if (rightCount > 0) {
     rightCount--;
    if (rightCount == 0)  {
       right = false;
    }
  }
  
  if (leftCount > 0) {
     leftCount--;
    if (leftCount == 0)  {
       left = false;
    }
  }
  up = false;
  
  drawText();
  
  
}

public void drawLevel() {
  fill(0);
  noStroke();
  for ( int ix = 0; ix < WIDTH; ix++ ) {
    for ( int iy = 0; iy < HEIGHT; iy++ ) {
      switch(level[iy][ix]) {
        case 1: rect(ix*16,iy*16,16,16);
      }
    }
  }
}

public boolean place_free(int xx,int yy) {
//checks if a given point (xx,yy) is free (no block at that point) or not
  yy = PApplet.parseInt(floor(yy/16.0f));
  xx = PApplet.parseInt(floor(xx/16.0f));
  if ( xx > -1 && xx < level[0].length && yy > -1 && yy < level.length ) {
    if ( level[yy][xx] == 0 ) {
      return true;
    }
  }
  return false;
}

public void keyPressed() {
  switch(keyCode) {
    case RIGHT: right = true; break;
    case LEFT: left = true; break;
    case UP: up = true; break;
  }
}
public void keyReleased() {
  switch(keyCode) {
    case RIGHT: right = false; break;
    case LEFT: left = false; break;
    case UP: up = false; break;
  }
}
//void mousePressed() {
////Left click creates/destroys a block
//  if ( mouseButton == LEFT ) {
//    level[int(floor(mouseY/16.0))][int(floor(mouseX/16.0))] ^= 1;
//  }
//}

//This is called automatically when OSC message is received
public void oscEvent(OscMessage theOscMessage) {
 if (theOscMessage.checkAddrPattern("/output_1")==true) {
        goLeft();
        println("left");
 } else if (theOscMessage.checkAddrPattern("/output_2")==true) {
     goRight();
     println("right");
 } else if (theOscMessage.checkAddrPattern("/output_3") == true) {
     jump();
     println("jump");
 } else {
    println("Unknown OSC message received");
 }
}

public void drawText() {
  text( "Receives /output_1 /output_2 and /output_3 (default messages) from Wekinator", 5, 15 );
  text( "Receives on port 12000", 5, 30 ); 
}

private void goLeft() {
   left = true;
   leftCount = 10;
}

private void goRight() {
   right = true;
   rightCount = 10;
}

private void jump() {
  up = true;
}
class Clouds{
  float c1 = random(0,100);
  float c2 = random(50,150);
  float s1 = random(0,480);
  float s2 = random(50,100);
  int speed = 2;
  int x1 = width-width;
  int x2=  width;

  public void drawCloud(){
    

        push();{
    
    //for(float c1 = 0; c1 < 99; c1++){
    if (speed > 0){
    
    //cloud1
    noStroke();
    fill(random(190,211));
    ellipse(c1, c2, 30, 15);
    ellipse(c1+10, c2-10, 30, 15);
    ellipse(c1+20, c2-5, 30, 15);
    
    //cloud2
    noStroke();
    fill(255);
    ellipse(c1+30, c2-50, 30, 15);
    ellipse(c1+40, c2-60, 30, 15);
    ellipse(c1+50, c2-55, 30, 15);
    
    //cloud3
    noStroke();
    fill(255);
    ellipse(c1+100, c2, 30, 15);
    ellipse(c1+110, c2-10, 30, 15);
    ellipse(c1+120, c2-5, 30, 15);
    
    //cloud4
    noStroke();
    fill(255);
    ellipse(c1+200, c2-30, 30, 15);
    ellipse(c1+210, c2-40, 30, 15);
    ellipse(c1+220, c2-35, 30, 15);
    
    //cloud5 
    noStroke();
    fill(random(190,211));
    ellipse(c1+350, c2, 40, 25);
    ellipse(c1+360, c2-10, 40, 25);
    ellipse(c1+370, c2-5, 40, 25);
    

            
          }
          
            c1 += this.speed;
            if(c1 < this.x1 || c1 > this.x2)
                {
                    //reverse direction
                    this.speed *= -1;
                }
         //sun
    fill(255,215,0);
    ellipse(s1,s2, 40, 40);
                
    }
    pop();
  }
}
class Enamy {
       //killer whale (Enamy)
       int x_pos = 75;
       int y_pos = 350;
       int x1 = width-width;
       int x2=  width;
       int speed = 2;
       int size =  30;
       int enemies = 30;
       int lives = 3;
       
        public void drawEnamy(){    
    //enemy 1
    push();
        {
            // Draw enemy.
            // killer whale
            if (speed > 0)
                {
                    stroke(0);
                    fill(255,0,0);
                    ellipse (x_pos, y_pos,40,40);
                    triangle (x_pos-10, y_pos,x_pos, y_pos-30,x_pos+10, y_pos);
                    triangle (x_pos-30, y_pos+15,x_pos, y_pos,x_pos+30, y_pos+15);
                    //belly 
                    noStroke();
                    fill(255,255,255);
                    ellipse (x_pos, y_pos+8,35,20);
                    //eyes
                    ellipse (x_pos-10, y_pos-3,10,15);
                    ellipse (x_pos+10, y_pos-3,10,15);
                    stroke(0);
                    fill(0);
                    ellipse (x_pos-10, y_pos-3,5,10);
                    ellipse (x_pos+10, y_pos-3,5,10);
                    stroke(0);
                    line(x_pos-4, y_pos+6,x_pos+4, y_pos+9);
                }
            else
                {
                      stroke(0);
                        fill(0);
                        ellipse (x_pos, y_pos,40,40);
                        triangle (x_pos-10, y_pos,x_pos, y_pos-30,x_pos+10, y_pos);
                        triangle (x_pos-30, y_pos+15,x_pos, y_pos,x_pos+30, y_pos+15);
                        //belly 
                        noStroke();
                        fill(255,255,255);
                        ellipse (x_pos, y_pos+8,35,20);
                        //eyes
                        fill(255,0,0);
                        ellipse (x_pos-10, y_pos-3,10,15);
                        ellipse (x_pos+10, y_pos-3,10,15);
                        stroke(0);
                        fill(0);
                        ellipse (x_pos-10, y_pos-3,5,10);
                        ellipse (x_pos+10, y_pos-3,5,10);
                        stroke(0);
                        line(x_pos-4, y_pos+6,x_pos+4, y_pos+9);
                }

    }
      
       pop();
        }
        
                
        public void move(){
            x_pos += this.speed;
            if(x_pos < this.x1 || x_pos > this.x2)
                {
                    //reverse direction
                    this.speed *= -1;
                }
          
        }

       public void checkCharCollision()
        {
          
            if( abs(realPos - this.x_pos) < 10 && abs(p1.pinguin_y - this.y_pos) < 20 )
              { 
               p1.lives--;
                //playerEated();
    
              }
      }
      
  }
class Fish {
       //killer whale (Enamy)
       int x_pos = 65;
       int y_pos = 350;
       int x1 = width-width;
       int x2=  width;
       int speed = 1;
       int size =  30;
       int enemies = 30;
        
        public void drawFish(){    
    //enemy 1
    push();
        {
            // Draw Food
            // killer whale
            if (speed > 0)
                {
                     //x_pos y_pos
                    noStroke();
                    fill(19, 56, 85);
                    
                   // ellipse(x_pos+25,y_pos,40,25);
                    
                    triangle (x_pos,y_pos,x_pos+15,y_pos-15,x_pos+15, y_pos+15 );
                    triangle (x_pos+15,y_pos-15,x_pos+45,y_pos,x_pos+15, y_pos+15);
                    fill(random(255), random(255), random(255));
                    triangle (x_pos+45,y_pos,x_pos+55,y_pos-10,x_pos+55, y_pos+10);
                    triangle (x_pos+15,y_pos-15,x_pos+45,y_pos,x_pos+15, y_pos-19);
                            //eyes
                    fill(255);
                    ellipse(x_pos+11,y_pos-5,4,6);
                    fill(0);
                    ellipse(x_pos+11,y_pos-5,2,4);
                   
                }
            else
                {
                    noStroke();
                    fill(19, 56, 85);
                    triangle (x_pos,y_pos,x_pos-15,y_pos-15,x_pos-15, y_pos+15);
                    triangle (x_pos-15,y_pos-15,x_pos-45,y_pos,x_pos-15, y_pos+15);
                    fill(random(255), random(255), random(255));
                    triangle (x_pos-45,y_pos,x_pos-55,y_pos-10,x_pos-55, y_pos+10);
                    triangle (x_pos-15,y_pos-15,x_pos-45,y_pos,x_pos-15, y_pos-19);
                            //eyes
                    fill(255);
                    ellipse(x_pos-11,y_pos-5,4,6);
                    fill(0);
                    ellipse(x_pos-11,y_pos-5,2,4);
                }

    }
      
       pop();
        }
        
                
        public void move(){
            x_pos += -this.speed;
            if(x_pos < this.x1 || x_pos > this.x2)
                {
                    //reverse direction
                    this.speed *= -1;
                }
          
        }
        
       public void checkCharCollision()
        {
          
            if( abs(realPos - this.x_pos) < 10 && abs(p1.pinguin_y - this.y_pos) < 20 )
              { 
               p1.lives++;
                //playerEated();
    
              }
      }
       //void checkFish()
       // {
       //   //realPos = p1.pinguin_x - scrollPos;
       //     if( abs(realPos - this.x_pos) < 10 && abs(p1.pinguin_y - this.y_pos) < 20 )
       //         {
       //             checkPlayerWon();
       //         }                                  
       //           if(isLost == false)
       //             {
       //                 score++;
       //                 fill(0);
       //                // background(255);
       //                 text("You WIN", 170, 174);
       //             }              
       // }
        
        
        
        
      }
class Mountains {
float m1 = 100;
float m2 = 100;
  public void drawMountain(){
      fill(186,143,48); 
      triangle(m1,m2,m1+180,m2+246,m1-80,m2+246);
      triangle(m1+25,m2+30,m1+200,m2+246,m1-60,m2+246);
      triangle(m1-40,m2+100,m1+180,m2+246,m1-100,m2+246);
      triangle(m1+76,m2+70,m1+300,m2+246,m1-80,m2+246);
      fill(255,255,255);
      triangle(m1,m2,m1+40,m2+70,m1-18,m2+60);
      triangle(m1+25,m2+30,m1+50,m2+60,m1+10,m2+60);
      
      //ocean
      fill(0,119,190);
      rect(0,height-22, width, 22);
      
  }
  
  
//    // Draw game life.
//        void liveBar(){
        
//        fill(0);
//        noStroke();
//        text("score: " + score, 20,50);
//        text("lives: ", 20,70);
//        for(int i = 0; i < lives; i++)
//                {
//                    stroke(0);
//                    fill(255,0,0);
//                    ellipse(60+i*30,65, 20,20);
//                }
            
//        if(isLost == true)
//        {
//            text("Game over - you lost. Press space to continue...", 50, height/2);
//        }
        
//        if(isWon == true)
//        {
//          text("Game over - you win. Press space to continue...", 50, height/2);
//        }

  
  
//}
  
  
  
  
  
  
  
  
  
  
  
}
class Player {
  int pinguin_x,pinguin_y;
  float xSpeed,ySpeed;
  float accel,deccel;
  float maxXspd,maxYspd;
  float xSave,ySave;
  int xRep,yRep;
  float gravity;
  int lives;
  Player(int _x, int _y ) {
    pinguin_x = _x;
    pinguin_y = _y;
    xSpeed = 0;
    ySpeed = 0;
    accel = 0.5f;
    deccel = 0.5f;
    maxXspd = 2;
    maxYspd = 12;
    xSave = 0;
    ySave = 0;
    xRep = 0;
    yRep = 0;
    gravity = 0.25f;
    lives = 30;
  }
  public void update() {
    if ( right ) {
      xSpeed += accel;
      if ( xSpeed > maxXspd ) {
        xSpeed = maxXspd;
      }
    }
    else if ( left ) {
      xSpeed -= accel;
      if ( xSpeed < -maxXspd ) {
        xSpeed = -maxXspd;
      }
    }
    else { //neither right or left pressed, decelerate
      if ( xSpeed > 0 ) {
        xSpeed -= deccel;
        if ( xSpeed < 0 ) {
          xSpeed = 0;
        }
      }
      else if ( xSpeed < 0 ) {
        xSpeed += deccel;
        if ( xSpeed > 0 ) {
          xSpeed = 0;
        }
      }
    }
    
    if ( up ) {
      if ( !place_free(pinguin_x,pinguin_y+16) || !place_free(pinguin_x+15,pinguin_y+16) ) {
        ySpeed = -6.3f;
      }
    }
    
    ySpeed += gravity;
    
    /*
    // The technique used for movement involves taking the integer (without the decimal)
    // part of the player's xSpeed and ySpeed for the number of pixels to try to move,
    // respectively.  The decimal part is accumulated in xSave and ySave so that once
    // they reach a value of 1, the player should try to move 1 more pixel.  This jump
    // is not normally visible if it is moving fast enough.  This method is used because
    // is guarantees that movement is pixel perfect because the player's position will
    // always be at a whole number.  Whole number positions prevents problems when adding
    // new elements like jump through blocks or slopes.
    */
    xRep = 0; //should be zero because the for loops count it down but just as a safety
    yRep = 0;
    xRep += floor(abs(xSpeed));
    yRep += floor(abs(ySpeed));
    xSave += abs(xSpeed)-floor(abs(xSpeed));
    ySave += abs(ySpeed)-floor(abs(ySpeed));
    int signX = (xSpeed<0) ? -1 : 1;
    int signY = (ySpeed<0) ? -1 : 1;
    //when the player is moving a direction collision is tested for only in that direction
    //the offset variables are used for this in the for loops below
    int offsetX = (xSpeed<0) ? 0 : 15;
    int offsetY = (ySpeed<0) ? 0 : 15;
    
    if ( xSave >= 1 ) {
      xSave -= 1;
      xRep++;
    }
    if ( ySave >= 1 ) {
      ySave -= 1;
      yRep++;
    }
    
    for ( ; yRep > 0; yRep-- ) {
      if ( place_free(pinguin_x,pinguin_y+offsetY+signY) && place_free(pinguin_x+15,pinguin_y+offsetY+signY) ) {
        pinguin_y += signY;
      }
      else {
        ySpeed = 0;
      }
    }
    for ( ; xRep > 0; xRep-- ) {
      if ( place_free(pinguin_x+offsetX+signX,pinguin_y) && place_free(pinguin_x+offsetX+signX,pinguin_y+15) ) {
        pinguin_x += signX;
      }
      else {
        xSpeed = 0;
      }
    }
      
  }
  public void show() {
    pushMatrix();
    
    
    fill(0,0,0);
        ellipse(pinguin_x,pinguin_y,25,35);
        //feet
        stroke(0,0,0);
        fill(255,165,0);
        ellipse(pinguin_x-5,pinguin_y+17,10,5);
        ellipse(pinguin_x+5,pinguin_y+17,10,5);
        //belly
        noStroke();
        fill(255,255,255);
        ellipse(pinguin_x,pinguin_y+5,20,20);
        //eyes
        ellipse(pinguin_x-5,pinguin_y-8,4,6);
        ellipse(pinguin_x+5,pinguin_y-8,4,6);
        //pupils
        fill(0,0,0);
        ellipse(pinguin_x-5,pinguin_y-8,3,3);
        ellipse(pinguin_x+5,pinguin_y-8,3,3);
        //nose
        fill(255,165,0);
        triangle(pinguin_x-4,pinguin_y-5,pinguin_x+4,pinguin_y-5,pinguin_x,pinguin_y-1);
        //arms
        fill(0,0,0);
        triangle(pinguin_x-13,pinguin_y-2,pinguin_x-13,pinguin_y+4,pinguin_x-17,pinguin_y+5);
        triangle(pinguin_x+13,pinguin_y-2,pinguin_x+13,pinguin_y+4,pinguin_x+17,pinguin_y+5);
    
    popMatrix();
  }
}
  public void settings() {  size(480,368); }
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "Pinguin_Game_thoss003" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
