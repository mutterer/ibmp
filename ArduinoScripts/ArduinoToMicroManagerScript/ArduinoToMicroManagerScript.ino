int aVals[6];
int buttonTimes[11];

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
  for(int i = 2; i<=10; i++){
    if(digitalRead(i) == HIGH && millis() - buttonTimes[i] > 500){
      int value = i*1000;
      buttonTimes[i] = millis();
      Serial.println(String(value));
    }
    //To prevent a bug where after a while the buttons start spamming
    if(millis()-buttonTimes[i] > 1500){
      buttonTimes[i] = millis()-501;
    }
  }
  
  //If the place isnt taken it spams repeatedly
  //The signal goes from 0 to a cap of 999 to not have to deal with overload
  if(!(analogRead(A0) - aVals[0] <2 && analogRead(A0) - aVals[0] > -2)){
    aVals[0] = analogRead(A0);
    if(aVal[0] > 999){
      aVal[0] = 999;
    }
    Serial.println(String((10*1000) + aVals[0]));
  }/*
    if(!(analogRead(A1) - aVals[1] <2 && analogRead(A1) - aVals[1] > -2)){
    aVals[1] = analogRead(A1);
    if(aVal[1] > 999){
      aVal[0] = 999;
    }
    Serial.println(String((11*1000) + aVals[1]));
  }
    if(!(analogRead(A2) - aVals[2] <2 && analogRead(A2) - aVals[2] > -2)){
    aVals[2] = analogRead(A2);
    if(aVal[2] > 999){
      aVal[0] = 999;
    }
    Serial.println(String((12*1000) + aVals[2]));
  }
    if(!(analogRead(A3) - aVals[3] <2 && analogRead(A3) - aVals[3] > -2)){
    aVals[3] = analogRead(A3);
    if(aVal[3] > 999){
      aVal[0] = 999;
    }
    Serial.println(String((13*1000) + aVals[3]));
  }
    if(!(analogRead(A4) - aVals[4] <2 && analogRead(A4) - aVals[4] > -2)){
    aVals[4] = analogRead(A4);
    if(aVal[4] > 999){
      aVal[0] = 999;
    }
    Serial.println(String((14*10000) + aVals[4]));
  }
    if(!(analogRead(A5) - aVals[5] <2 && analogRead(A5) - aVals[5] > -2)){
    aVals[5] = analogRead(A5);
    if(aVal[5] > 999){
      aVal[0] = 999;
    }
    Serial.println(String((15*1000) + aVals[5]));
  }*/
  }
  

