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
    accel = 0.5;
    deccel = 0.5;
    maxXspd = 2;
    maxYspd = 12;
    xSave = 0;
    ySave = 0;
    xRep = 0;
    yRep = 0;
    gravity = 0.25;
    lives = 30;
  }
  void update() {
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
        ySpeed = -6.3;
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
  void show() {
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
