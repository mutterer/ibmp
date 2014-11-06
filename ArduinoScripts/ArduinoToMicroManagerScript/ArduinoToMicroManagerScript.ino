int aVals[6];
unsigned long buttonTimes[11];
unsigned long lastSendTime = millis();
int cap = 9000;

void setup() {
  //NonLEDS
  for(int i = 0; i<=10; i++){
    pinMode(i,INPUT);
    buttonTimes[i] = millis();
  }
  //LEDS
  for(int i = 11; i <=13; i++){
   pinMode(i, OUTPUT);
  }
  //AnalogValues
  for(int i = 0; i < 6; i++){
    aVals[i] = 0;
  }
  Serial.begin(9600);
}

void loop() {
  
  //ports 0 and 1 are spamming
  //If a Place isn't taken it goes off if the one next to it goes off
    /*for(int i = 0; i<=13; i++){
      digitalWrite(i,LOW);
    }*/
  for(int i = 0; i<=9; i++){
    if(digitalRead(i) == HIGH && millis() - buttonTimes[i] > 400){
      
      String value = String(i)+",0";
      buttonTimes[i] = millis();
      serialPrintln(String(value));
    }
    //To prevent a bug where after a while the buttons start spamming
    if(millis()-buttonTimes[i] > 1500){
      buttonTimes[i] = millis()-500;
      
    }
  }
  
  //If the place isnt taken it spams repeatedly
  //The signal goes from 0 to a cap of 999 to not have to deal with overload
  if(!(analogRead(A0) - aVals[0] <2 && analogRead(A0) - aVals[0] > -2)){
    aVals[0] = analogRead(A0);
    if(aVals[0] > cap){
      aVals[0] = cap;
    }
    serialPrintln("10," + String(aVals[0]));
  }/*
    if(!(analogRead(A1) - aVals[1] <2 && analogRead(A1) - aVals[1] > -2)){
    aVals[1] = analogRead(A1);
    if(aVals[1] > cap){
      aVals[1] = cap;
    }
    Serial.println("11," + String(aVals[1]));
  }
    if(!(analogRead(A2) - aVals[2] <2 && analogRead(A2) - aVals[2] > -2)){
    aVals[2] = analogRead(A2);
    if(aVals[2] > cap){
      aVals[2] = cap;
    }
    Serial.println("12," + String(aVals[2]));
  }
    if(!(analogRead(A3) - aVals[3] <2 && analogRead(A3) - aVals[3] > -2)){
    aVals[3] = analogRead(A3);
    if(aVals[3] > cap){
      aVals[3] = cap;
    }
    Serial.println("13," + String(aVals[3]));
  }
    if(!(analogRead(A4) - aVals[4] <2 && analogRead(A4) - aVals[4] > -2)){
    aVals[4] = analogRead(A4);
    if(aVals[4] > cap){
      aVals[4] = cap;
    }
    Serial.println("14," + String(aVals[4]));
  }
    if(!(analogRead(A5) - aVals[5] <2 && analogRead(A5) - aVals[5] > -2)){
    aVals[5] = analogRead(A5);
    if(aVals[5] > cap){
      aVals[5] = cap;
    }
    Serial.println("15," + String(aVals[5]));
  }*/
  }
  
  void serialPrintln(String msg){
    int msgPerSecond = 15;
    if(millis()-lastSendTime > 1000/msgPerSecond || millis() < 2000){
       Serial.println(msg); 
       lastSendTime = millis();
    }
  }
  

