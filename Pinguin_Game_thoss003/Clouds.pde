class Clouds{
  float c1 = random(0,100);
  float c2 = random(50,150);
  float s1 = random(0,480);
  float s2 = random(50,100);
  int speed = 2;
  int x1 = width-width;
  int x2=  width;

  void drawCloud(){
    

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
