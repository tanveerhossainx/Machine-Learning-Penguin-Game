class Fish {
       //killer whale (Enamy)
       int x_pos = 65;
       int y_pos = 350;
       int x1 = width-width;
       int x2=  width;
       int speed = 1;
       int size =  30;
       int enemies = 30;
        
        void drawFish(){    
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
        
                
        void move(){
            x_pos += -this.speed;
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
