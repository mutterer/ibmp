const int LED = 13;
bool lightOn = false;
const int margin = 530;

void setup() {
  pinMode(LED, OUTPUT);
  Serial.begin(9600);
}

void loop() {
  if(lightOn != analogRead(A0) > margin)
  {
    lightOn = analogRead(A0) > margin;
    if(lightOn){
    digitalWrite(LED,HIGH);
    Serial.println("LightOn");
  }
  else{
    digitalWrite(LED,LOW);
    Serial.println("LightOff");
  }
  }
  else if(analogRead(A0) == 0){
     Serial.println("off"); 
  }
  }
  

