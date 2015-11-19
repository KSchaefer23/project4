//// Arrays of objects.


int nb=16;
Ball[] b=  new Ball[nb];         // Array of Ball objects.

int nt=8;
Button[] t=  new Button[nt];     // Array of Button objects.

float left, right, top, bottom, middle;



//// SETUP:  create arrays, initialize, ec.
void setup() {
  size(640, 480);
  
  // Create cue and 15 balls.
  b[0]=  new Ball( 0, 255, 255, 255 );
  b[0].x=  200;
  b[0].y=  200;
  for (int i=1; i<nb; i++) {
    b[i]=  new Ball( 0, 255, 255, 255 );
  }
}

//// NEXT:  Draw ball, after moving and colliding.
void draw() {
  background( 100,150,250 );
  scene();
  action();
  show();
  messages();
}

//// Table, buttons, etc.
void scene() {
}

//// ACTION:  move, collide, etc.
void action() {
  for( int i=0; i<nb-1; i++) {
    for( int j=i+1; j<nb; j++) {    
      collide( b[i], b[j] );
    }
  }
  //// Move all balls.
  for( int i=0; i<nb; i++) {
    b[i].move();
  }
}
//// Elastic collisions -- swap dx,dy
void collide( Ball p, Ball q ) {
  // If they are close, swap speeds
  if( dist( p.x,p.y, q.x,q.y) <30 ) {
    swapSpeeds (p,q); 
  }
}
 
 // swap dx, dy   
void swapSpeeds( Ball p, Ball q) {  
  //dx swap
  float tmp;
  tmp= p.dx;
  p.dx= q.dx;
  q.dx= tmp;
  //dy swap
  tmp=p.dy;
  p.dy=q.dy;
  q.dy=tmp;
}

//// Display 
void show() {
  for( int i=0; i<nb; i++) {
    b[i].show();
  }
}

//// ????
void messages() {
}



//////// CLASSES ////////
class Ball {
  float x,y, dx,dy;
  float r,g,b;
  int number;
  //// CONSTRUCTORS:
  Ball( int n ) {
    number=  n;
    randomize();
  }
  Ball( int n, float r, float g, float b ) {
    number=  n;
    this.r=  r;
    this.g=  g;
    this.b=  b;
    randomize();
  }
  Ball( int n, float x, float y ) {
    number=  n;
    randomize();
  }
  void randomize() {
    r=  random(255);
    g=  random(255);
    b=  random(255);
    x=  random( width/2, width-100);
    y=  random( height/4, height*3/4);
    dx= random( -2, 2 );
    dy= random( -2, 2 );
  }
  
  //// METHODS ////
  void move() {
    //+++++
    x += dx;
    y += dy;
  }
  void show() {
    //+++++
    fill(r,g,b);
    ellipse( x, y, 30, 30 );
  }
}


class Button {
}
