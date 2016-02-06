package processing.test.hackathon;

import processing.core.*; 
import processing.data.*; 
import processing.event.*; 
import processing.opengl.*; 

import ketai.sensors.*; 
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

public class hackathon extends PApplet {




  
OscP5 oscP5;
NetAddress remote;
KetaiSensor sensor;
//String ip = "172.25.16.43"; // raspberry pi
String ip = "128.237.251.252"; // raspberry pi
int portThis = 12001;
int port = 12001;
final int screenPPI = 432;
float buttonWidth = inchesToPixels(0.5f);
float yCoordinate;
boolean mouseBackOn, mouseForOn, mouseLeftOn, mouseRightOn;

String direction, move;
PImage img, upImg, downImg;
public void setup()   {
    
    sensor = new KetaiSensor(this);
    sensor.start();
    orientation(LANDSCAPE);
    
    oscP5 = new OscP5(this, portThis);
    remote = new NetAddress(ip, port);
    yCoordinate = 0;
    
    // debugging strings
    //textFont(createFont("Arial", 40));
    move = "";
    direction = "";
    img = loadImage("wheel.jpg");
    upImg = loadImage("up.jpg");
    downImg = loadImage("down.jpg");
}

public void draw() {
  background(80);
  image(img, 0, 0, width, height);
  image(downImg,0, height-buttonWidth, buttonWidth, buttonWidth);
  image(upImg, width-buttonWidth, height-buttonWidth, buttonWidth, buttonWidth);
  
  //rect(0, height-buttonWidth, buttonWidth, buttonWidth);
  //rect(width-buttonWidth, height-buttonWidth, buttonWidth, buttonWidth);
  //text("MOVING : " + move, width/2, height/2-40);
  //text("DIRECTION : " + direction, width/2, height/2+40);
  if (mouseForOn) {
    OscMessage dirMessage = new OscMessage("/dir");
    if (mouseLeftOn) {
      dirMessage.add("forward-left");
    } else if (mouseRightOn) {
      dirMessage.add("forward-right");
    } else {
      dirMessage.add("forward");
    }
    oscP5.send(dirMessage, remote);
  } else if (mouseBackOn) {
    OscMessage dirMessage = new OscMessage("/dir");
    if (mouseLeftOn) {
      dirMessage.add("backward-left");
    } else if (mouseRightOn) {
      dirMessage.add("backward-right");
    } else {
      dirMessage.add("backward");
    }
    oscP5.send(dirMessage, remote);
  }
}

public void mousePressed() {
  if (mouseX >= 0 && mouseX <= buttonWidth && mouseY >= height-buttonWidth && mouseY <= height) {
    mouseBackOn = true;
    mouseForOn = false;
    move = "backward";
  } else if (mouseX >= width-buttonWidth && mouseX <= width && mouseY >= height-buttonWidth && mouseY <= height) {
    mouseForOn = true;
    mouseBackOn = false;
    move = "forward";
  } else {
    mouseBackOn = false;
    mouseForOn = false;
    move = "";
  }
}

public void mouseReleased() {
  mouseForOn = false;
  mouseBackOn = false;
}

public float inchesToPixels(float inch)
{
  return inch * screenPPI;
}

public void onAccelerometerEvent(float x, float y, float z) {
  yCoordinate = y;
  if (y <= -3) {
   mouseLeftOn = true;
   mouseRightOn = false;
   direction = "left";
  }
  else if (y >= 3) {
   mouseLeftOn = false;
   mouseRightOn = true;
   direction = "right";
  }
  else {
    mouseLeftOn = false;
    mouseRightOn = false;
    direction = "";
  }
}
  public void settings() {  size(1920, 1080); }
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "hackathon" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
