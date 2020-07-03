class Mountains {
float m1 = 100;
float m2 = 100;
  void drawMountain(){
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
