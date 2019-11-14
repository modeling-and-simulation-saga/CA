CA sys;//Define a instance of CA class
final int rr=1000;//canvas size
int n=200;//number of sites
int rule=90;//rule number
double initDensity=0.01;
float r;
double t=0;
boolean stop = false;

//Initial setup for canvas
void setup() {
  size(1000,1000);
  smooth();
  frameRate(10);
  sys = new CA(n, rule);
//  sys.initialSingle();
  sys.initialize(initDensity);
  r=rr/n;
}

void draw() {
  if (t%n == 0) {//refresh canvas
    if(stop){noLoop();}
    else{
      background(0, 128, 0);
      t=0;
    }
  }
  int cells[]=sys.update();
  t ++;
  for (int i=0; i<n; i++) {
    int k = cells[i];
//    println(k);
    fill(256*k, 0,128*k);//setting colors for filling shapes
    noStroke();//not drawing lines for shapes
    float xx = (float)(r*i);
    float yy = (float)(r*t);
    rect(xx, yy, r, r);//drawing rectangle
  }
}

void mousePressed(){
  stop=!stop;
  if(!stop){
    loop();
    t=0;
  }
}
