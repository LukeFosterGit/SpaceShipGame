import processing.core.*; 
import processing.data.*; 
import processing.event.*; 
import processing.opengl.*; 

import java.util.HashMap; 
import java.util.ArrayList; 
import java.io.File; 
import java.io.BufferedReader; 
import java.io.PrintWriter; 
import java.io.InputStream; 
import java.io.OutputStream; 
import java.io.IOException; 

public class FinalGameMastered extends PApplet {

// Luke Foster
// Space Trip Gamified and Condensed
PFont f; 
PFont g; 
int[] StarX = new int[9];
int[] StarY = new int[9];
float[] RoidX = new float[9];
float[] RoidY = new float[9];
float dif;
int shape1, shape2;
int[] Col = new int[3];
int count = 3;
PImage img;
float shipY;
public void setup()
{
   
   
   f = loadFont( "NirmalaUI-Bold-48.vlw" );
   g = loadFont( "OCRAExtended-48.vlw" );
   dif = 5;
   shape1 = (int) random(0, 30);
   shape2 = (int) random(0, 40);
   
   
   for (int i=0; i<StarX.length; i++){
     StarX[i] = (int) random(0, 800);
     StarY[i] = (int) random(0, 800);
   }

   
   for (int i=0; i<Col.length; i++){
     Col[i] = (int) random(0, 255);
   }
    
    
  
   for (int i=0; i<RoidX.length; i++){
     RoidX[i] = (int) random(-1500,0);
     RoidY[i] = (int) random(0, 800);
   }
}


public void draw()
{
   stroke(0);
  background(0,0,0);
  textFont(f,40);
  fill(255);
  text("LIVES:" + count,650,40);
  textFont(g,40);
  fill(Col[0],Col[1],Col[2]);
  text("SCORE:" + frameCount,10,40);
  fill(0);
 
   
// HitBox (for demonstration)
   rect(mouseX-15,mouseY-17,95,35);

   
// SpaceShip
   fill(178,172,172);
   quad(mouseX,mouseY,mouseX+45,mouseY,mouseX+80,mouseY-6,mouseX+10,mouseY+40);
   ellipse(mouseX+33,mouseY,100,40);
   noStroke();
   rect(mouseX+63,mouseY,5,-30);
   stroke(1);
   fill(255,255,255);
   bezier(mouseX-12,mouseY-8,mouseX-17,mouseY-30,mouseX+3,mouseY-30,mouseX+33,mouseY-20);
   fill(178,172,172);
   quad(mouseX-6,mouseY,mouseX+38,mouseY,mouseX+73,mouseY-6,mouseX+3,mouseY+40);
   fill(198,201,255);
   ellipse(mouseX+65,mouseY-25,10,10);


// flashing ship light.      
   if((frameCount%100)<50){
     fill(255,18,10);  
     ellipse(mouseX+65,mouseY-25,10,10);  
   }
   
   
  // stars.
   fill(255,252,185);
   for (int i=0; i<StarX.length; i++){
     ellipse(StarX[i], StarY[i], 7, 7);
   }

   
 // Asteroids
   stroke(255);
   fill(139,44,44);
   for (int i=0; i<RoidX.length; i++){
     quad(RoidX[i]+38, RoidY[i]+31, RoidX[i]+86, RoidY[i]+20, RoidX[i]+69, RoidY[i]+63, RoidX[i]+30, RoidY[i]+76);
   }
    
    
// Score color change   
   for (int i=0; i<Col.length; i++){
     Col[i] = Col[i]+1;
   }
   
   
// Asteroid interaction
   for (int i=0; i<RoidX.length; i++){
    if
     ((RoidX[i]+38 > mouseX-15 && RoidX[i]+38 < mouseX-15 + 95 && RoidY[i]+31 > mouseY-17 && RoidY[i]+31 < mouseY-17 + 35)||
      (RoidX[i]+86 > mouseX-15 && RoidX[i]+86 < mouseX-15 + 95 && RoidY[i]+20 > mouseY-17 && RoidY[i]+20 < mouseY-17 + 35)||
      (RoidX[i]+69 > mouseX-15 && RoidX[i]+69 < mouseX-15 + 95 && RoidY[i]+63 > mouseY-17 && RoidY[i]+63 < mouseY-17 + 35)||
      (RoidX[i]+30 > mouseX-15 && RoidX[i]+30 < mouseX-15 + 95 && RoidY[i]+76 > mouseY-17 && RoidY[i]+76 < mouseY-17 + 35))
      {  
    setup();
    count --;
      }
   }
   

// Asteroid movement
   for (int i=0; i<RoidX.length; i++)
     RoidX[i] = RoidX[i]+dif;

//Progressing Difficulty
   if((frameCount%300 == 0)){
     dif = dif + 1;
   }
   
   
// Star movement
   for (int i=0; i<StarX.length; i++){
     StarX[i] = StarX[i]+12;
   }
  
   
// score color
   for (int i=0; i<Col.length; i++){
    if(Col[i] >  254) {
      Col[i] = 0;
    }
   }

   
// star reset
   for (int i=0; i<StarX.length; i++){
     if(StarX[i] >  800) {
       StarX[i] = 0;
       StarY[i] = (int) random(20, 780);
     }
   }
   

   
// Roid reset
   for (int i=0; i<RoidX.length; i++){
     if(RoidX[i] >  800) {
       RoidX[i] = (int) random(-1500,-30);
       RoidY[i] = (int) random(0, 800);
     }
   }

   
// End Game


   if(count == 0){
     noLoop(); 
     background(0,0,0);
     textFont(f,100);
     fill(255);
     text("GAME OVER",110,250);
     textFont(g,100);
     fill(33,18,255);
     text("SCORE:" + frameCount,120,450);
      
   }
}
  public void settings() {  size(800, 800);  smooth(); }
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "FinalGameMastered" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
