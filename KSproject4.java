//// Kevin Schaefer
//// CST 112 EVE

//// 5 balls, cue, 4 buttons ////
//Ball a,b,c,d,e, cue;
Button aa, bb, cc, dd;

int nb=16;
Ball[] kev= new Ball[nb];
int nt=4;
Button[] sch= new Button[nt];

// Cloud aaa[]= Cloud[7];
//int numClouds=3;

//// OTHER GLOBALS:  strings, pool table, etc ////
String news=   "Click any ball to reset it to right half of table.  ('r' resets all)";
String author=  "Kevin Schaefer";
String display= "Score:";

// Table boundaries
float left=120, right=520, top=165, bottom=315;
float middle=250;
boolean wall=true;                                
// Set booleans
boolean ratclick=false;
boolean birdmove=false;
boolean bombdrop=false;
// Initializes rat position/movement
float ratX = left;
float ratDX = 8;
float ratDY = 0;
float ratY= random(top, bottom);
// Initializes bird and bomb start location
float birdX = 0;
float bombY = 110;
float bombDY = 0;
// Creates green pool table
int tableRed=150, tableGreen=250, tableBlue=150;
// Initializes count and score
int score=0,m=0,k=0;
int count= 0;


//// SETUP:  size, table, balls, buttons
void setup() {
  // Keeps table centered w/ changing size
  size ( 1000, 800 );
  
  left = width/5;
  top = height/4; 
  right = width*(0.8);
  bottom = height*(0.75) ;
  middle = (width/2);
  
  // Creates the balls
  kev[0]= new Ball(0,255,255,255);
  kev[0].r= 255;
  kev[0].g= 255;
  kev[0].b= 255;
  kev[0].x = (left+right)/3;
  kev[0].y = (top+bottom)/2;
  kev[0].dx = 0; 
  kev[0].dy = 0;
  for (int i=1; i<nb; i++) {
    kev[i]= new Ball( 0, 255,255,255);
  }
/*  
  a = new Ball();
  a.r= 255; a.name= "1";
  b = new Ball();
  b.g= 255; b.name= "2";
  c = new Ball();
  c.g= 255; c.b= 255; c.name= "3";
  d = new Ball();
  d.r= 255; d.g=255; d.name= "4";
  e = new Ball();
  e.r= 200; e.b=255; e.name= "5";
  cue = new Ball();
  cue.r= 255; cue.g= 255; cue.b= 255;
*/  
  // Creates the buttons
  aa = new Button();
  aa.name= "RESET"; aa.x=100; aa.y=50; aa.w=60; aa.h=20;
  bb = new Button();
  bb.name= "WALL"; bb.x=180; bb.y=50; bb.w=60; bb.h=20;
  cc = new Button();
  cc.name= "BIRD"; cc.x=260; cc.y=50; cc.w=60; cc.h=20;
  dd = new Button();
  dd.name= "RAT"; dd.x=340; dd.y=50; dd.w=60; dd.h=20;
  
  // aaa = new Cloud();
  
  reset() ;
}

// Creates the reset/default game start
void reset() {
  // Starts score at zero and initial reset position
  score= 0;
  wall=true;
  ratX=left;
  ratclick=false;
  tableRed=150; tableGreen=250; tableBlue=150;
}

//// Draws the scene and calls functions
void draw() {
  background( 250,250,200 );
  rectMode( CORNERS );
  table( left, top, right, bottom );  
  ballDisplay();
  balls();
  grass();
  clouds();
  count += 1;
  // Handles mouse/bird/bomb draw each frame
  if (key == 'm' && ratX >left ) { rat(); }
  if (ratclick = true && ratX > left) { ratDY=random(-3,3); rat(); }
  bomb();
  if (birdmove == true) { bird(); }
  //messages();
  buttons();
}

//// Different functions when different keys pressed
void keyPressed() {
  if (key == 'q') { exit();  }
  if (key == 'r') { reset(); }
  if (key == 'w') { wall=false; }  // Remove wall
  if (key == 'p') { tableRed= 250; tableGreen=150; tableBlue=235; } // Pink table

  /// Number keys reset balls (reset reduces score)
/*  if (key == '1') { a.reset(); score -= 5; }
  if (key == '2') { b.reset(); score -= 5; }
  if (key == '3') { c.reset(); score -= 5; }
  if (key == '4') { d.reset(); score -= 5; }
  if (key == '5') { e.reset(); score -= 5; }
  if (key == 'c') { cue.x= (left+right)/3; cue.y= (top+bottom)/2; cue.dx=0; cue.dy=0; score -= 5; }
  
  // f key freezes ball movement for diagnostic purposes
  if (key == 'f') { a.dx=0; a.dy=0; b.dx=0; b.dy=0; c.dx=0; c.dy=0; d.dx=0; d.dy=0; e.dx=0; e.dy=0; cue.dx=0; cue.dy=0; }
*/
}

/// Resets balls when clicked (reset reduces score)
void mouseClicked() {
/*  if ( dist(a.x,a.y, mouseX,mouseY) < 18) { a.reset(); score -= 5; }
  if ( dist(b.x,b.y, mouseX,mouseY) < 18) { b.reset(); score -= 5; }
  if ( dist(c.x,c.y, mouseX,mouseY) < 18) { c.reset(); score -= 5; }
  if ( dist(d.x,d.y, mouseX,mouseY) < 18) { d.reset(); score -= 5; }
  if ( dist(e.x,e.y, mouseX,mouseY) < 18) { e.reset(); score -= 5; }
  if ( dist(cue.x,cue.y, mouseX,mouseY) < 18) { 
    cue.x= (left+right)/3; cue.y= (top+bottom)/2; cue.dx=0; cue.dy=0; score-= 5;}
*/    
  if ( dist(ratX,ratY, mouseX,mouseY) < 30) { ratX=left; score += 50; }

    
//// BUTTONS 
  // RESET 
  if ( mouseX > aa.x && mouseX < aa.x+aa.w &&
    mouseY > aa.y && mouseY < aa.y+aa.h ) {
      reset(); 
    }
  // WALL 
  if ( mouseX > bb.x && mouseX < bb.x+bb.w &&
    mouseY > bb.y && mouseY < bb.y+bb.h ) {
      wall=false;
    }
  // BOMB IF BIRD FLYING 
  if ( mouseX > cc.x && mouseX < cc.x+cc.w &&
    mouseY > cc.y && mouseY < cc.y+cc.h && birdmove == true ) {
      drop();
    }    
  // BIRD FLY 
  if ( mouseX > cc.x && mouseX < cc.x+cc.w &&
    mouseY > cc.y && mouseY < cc.y+cc.h ) {
      birdmove=true;
    }
  // RAT 
  if ( mouseX > dd.x && mouseX < dd.x+dd.w &&
    mouseY > dd.y && mouseY < dd.y+dd.h ) {
      rat();
    }    
}

//// Scene: Table w/ wall in middle
void table( float east, float north, float west, float south ) {
  fill( tableRed, tableGreen, tableBlue );        // pool table
  strokeWeight(20);
  stroke( 127, 0, 0 );                            // Brown walls
  rect( east-20, north-20, west+20, south+20 );   // Table border

  // Start with a WALL down the middle of the table
  if (wall==true) {
    float middle=  (east+west)/2;    
    stroke( 0, 127, 0 );
    line( middle,north+10, middle,south-10 );
  }
  stroke(0);
  strokeWeight(1);
}

//// Creates the bird shape and starting location
void bird() {
  if (birdX > width) {
    birdX = 0; birdmove=false; }
  fill(0);
  ellipse(birdX,100,35,15);        // Bird body
  ellipse(birdX+16,94,15,15);      // Bird head
  if (count/30 % 2 == 0) {    
    triangle(birdX-10,97, birdX,77, birdX+10,97); }        // up wing
    else {
      triangle(birdX-10,103, birdX,123, birdX+10,103); }   // down wing
  birdX += 4;  
}

/// Creates the bomb shape
void bomb() {
  if (bombdrop == true) {
    stroke(0);
    fill(255,255,255);
    ellipse(birdX-10, bombY, 10,10);
    bombY *= bombDY;
  }
}

/// Bomb droping function and initialization
void drop() {
  bombdrop = true;
  bombY = 112;
  bombDY = 1.03;
}

/// Creates the rat shape
void rat() {
  if (ratX < right) {
    stroke(245,150,220);                          // tail
    strokeWeight(3);
    line(ratX-40, ratY, ratX,ratY); 
    stroke(0,0,0);
    strokeWeight(2);
    
    if (count/30 % 2 == 0) {
      line(ratX-20,ratY, ratX-20,ratY+15);  // back left
      line(ratX+10,ratY, ratX+10,ratY+15);  // front right
      line(ratX-15,ratY, ratX-15,ratY+15);  // back right
      line(ratX+5,ratY, ratX+5,ratY+15);    // front left
    } else {
      line(ratX-20,ratY, ratX-15,ratY+15);  // angle back left
      line(ratX+10,ratY, ratX+13,ratY+15);  // front right
      line(ratX-15,ratY, ratX-12,ratY+15);  // back right      
      line(ratX+5,ratY, ratX+10,ratY+15);   // angle front left
    }
    
    strokeWeight(1);
    fill(125,125,125);
    ellipse(ratX,ratY, 50,20);      // body   
    ellipse(ratX+2,ratY-11,17,17);  // ears    
    fill(0,0,0);
    ellipse(ratX+14,ratY-4,3,3);    // eyes
    ellipse(ratX+25,ratY,3,3);      // nose
    ratX += ratDX;
    ratY += ratDY;
  } else { ratX = left; ratY= random(top, bottom); ratDX= random(5, 10);       // sets rat back at start location
  }
}
  
  // Determines distance between ball and rate to adjust score
/*  if ( dist(a.x,a.y, ratX,ratY) < 10) { ratX += 15; a.dx=0; a.dy=0; score -= 10; }
  if ( dist(b.x,b.y, ratX,ratY) < 10) { ratX += 15; b.dx=0; b.dy=0; score -= 10; }
  if ( dist(c.x,c.y, ratX,ratY) < 10) { ratX += 15; c.dx=0; c.dy=0; score -= 10; }
  if ( dist(d.x,d.y, ratX,ratY) < 10) { ratX += 15; d.dx=0; d.dy=0; score -= 10; }
  if ( dist(e.x,e.y, ratX,ratY) < 10) { ratX += 15; e.dx=0; e.dy=0; score -= 10; }
  if ( dist(cue.x,cue.y, ratX,ratY) < 10) { ratX += 15; cue.dx=0; cue.dy=0; score -= 10; }
}
*/

/// Display messages
/*
void messages() {
  fill(0);
  text( news, width/9, 30 );
  text( author, 10, height-5 );
  text( display, width-200, 30);
  text( score, width-140, 30);
}
*/

/// Draws the moving grass across bottom
void grass() {
  stroke(40,165,60);
  strokeWeight(3);
  int x = 0;
  float y = bottom + 30;
  while (y < height+10) {
    for (x=0; x<width+10; x += 10) {
      if (count/30 % 2 == 0) {            // Grass movement
        strokeWeight(3);
        line(x,y, x+7, y-7); }
      else {
        strokeWeight(3);
        line(x,y, x-7, y-7); }            // Grass movement
    }
    y += 6; 
  strokeWeight(1);
  }
}

/// Creates the clouds shape across the top
void clouds() {
  for (int i=100; i<width; i += 100) {
    int r = 130;
    noStroke();
    fill(230,230,230);
    ellipse(i+10,r-10, 30,30);
    ellipse(i-10,r-7, 20,20);
    ellipse(i,r, 70,20);
  }
  
  /*
  //// new clouds
  for (int i=0; i<numClouds; i++) {
    aaa[i].move();
    aaa[i].show();
  }
  */
}
  //if (aaa[0].i > width {
    //numClouds...
//}

/// Function to display the buttons
void buttons() {
  aa.show();
  bb.show();
  cc.show();
  dd.show();
}

/// Ball collisions/show/move
void balls() {
  for( int i=0; i<nb-1; i++) {
    for( int j=i+1; j<nb; j++) {    
      collision( kev[i], kev[j] );
    }
  }
  //// Move all balls.
  for( int i=0; i<nb; i++) {
    kev[i].move();
  }
}

/// Action when two balls collide (adds to score)
void collision( Ball p, Ball q ) {
  if ( p.hit( q.x,q.y ) ) {
    float tmp;
    tmp=p.dx;  p.dx=q.dx;  q.dx=tmp;      // Swap the velocities.
    tmp=p.dy;  p.dy=q.dy;  q.dy=tmp;
    score += 1;
  }
}

/// Button class gives properties
class Button {
  float x,y,w,h;
  String name="";
  
  void show() {
    strokeWeight(1);
    stroke(0);
    fill(255,175,0);
    rectMode( CORNER );
    rect ( x,y,w,h, 7 );
    fill(0,0,0);
    text(name, x+7, y+14);
  }
}

/*
//// Cloud class gives properties
class Cloud {
  
  float i, r;
  
  Cloud( float i, float r) {
    this.i= i;
    this.r= r;
  }
  void move() {
    i += random(1,5);
    r += random(0,2);
  }
  
  void show() {
    noStroke();
    fill(230,230,230);
    ellipse(i+10,r-10, 30,30);
    ellipse(i-10,r-7, 20,20);
    ellipse(i,r, 70,20);
  }
}
*/

void ballDisplay() {
  for( int i=0; i<nb; i++) {
    kev[i].show();
  }
}

//// Ball class gives properties
class Ball {
  float x,y, dx, dy;
  float r,g,b;
  int number;
  //// CONSTRUCTORS:
  /*Ball( int n ) {
    number=  n;
    randomize();
  }*/
  Ball( int n, float r, float g, float b ) {
    number=  n;
    this.r=  r;
    this.g=  g;
    this.b=  b;
    randomize();
  }
  /*Ball( int n, float x, float y ) {
    number=  n;
    randomize();
  }*/
  void show() {
    // Ball properties
    fill(r,g,b);
    ellipse( x,y, 30,30);
    fill(0,0,0);
    text(number, x-4,y+4);
  }
  void randomize() {
    r=  random(255);
    g=  random(255);
    b=  random(255);
    x=  random( (width/2)+20, (width*(0.8))-20 );
    y=  random( (height/4)+20, (height*(0.75))-20 );
    dx= random( -3, 3 );
    dy= random( -3, 3 );
  }
  void move() {
    // WALL COLLISION
    if (wall == true && x<middle+23) {
      dx *= -1;
    }
    
    // BOUNDARY COLLISIONS
    x += dx;
    if (x> right || x< left) { dx = -dx; }
    y += dy;
    if (y>bottom || y<top) { dy = -dy; }
  }

  void reset() {
  x= random(middle+40, right); y= random(top, bottom);
  dx=  random( -3,3 );   dy=  random( -3,3 );
  }
  
  // BALL COLLISIONS
  boolean hit( float x, float y ) {
    if (dist( x,y, this.x,this.y ) < 30 ) return true;
    else return false;
  }  
}
