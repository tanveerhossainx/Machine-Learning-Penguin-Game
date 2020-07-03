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
       
        void drawEnamy(){    
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
        
                
        void move(){
            x_pos += this.speed;
            if(x_pos < this.x1 || x_pos > this.x2)
                {
                    //reverse direction
                    this.speed *= -1;
                }
          
        }

       void checkCharCollision()
        {
          
            if( abs(realPos - this.x_pos) < 10 && abs(p1.pinguin_y - this.y_pos) < 20 )
              { 
               p1.lives--;
                //playerEated();
    
              }
      }
      
  }
