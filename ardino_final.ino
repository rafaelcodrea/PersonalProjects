#include <Wire.h> 
#include <LiquidCrystal_I2C.h>

#define echoPin 8 
#define trigPin 9 
#define led0 10
#define led1 11 
#define led2  12
#define led3 13
#define buzzer 7

LiquidCrystal_I2C lcd(0x27,20,4);  // set the LCD address to 0x27 for a 16 chars and 2 line display

long duration; 
int distance; 

unsigned long time;


void setup() {

//  lcd.begin(16,4);               // initialize the lcd 
//  lcd.home ();                   // go home


  lcd.init();                      // initialize the lcd 
  lcd.init();


   lcd.backlight();
  lcd.setCursor(3,0);

  lcd.print("Distance:");
  lcd.setCursor(2,1);
  lcd.print(distance);
  
  pinMode(trigPin, OUTPUT); 
  pinMode(echoPin, INPUT); 
  pinMode(led0, OUTPUT);
  pinMode(led2, OUTPUT);
  pinMode(led1, OUTPUT);
  pinMode(led3, OUTPUT);
  
 // lcd.begin(16, 2);
  Serial.begin(9600); 
  Serial.begin(9600); 
  
}

void loop() {
  digitalWrite(trigPin, LOW);
  digitalWrite(led0, LOW);
  digitalWrite(led1, LOW);
  digitalWrite(led2, LOW);
  digitalWrite(led3, LOW);
  
  lcd.setCursor(3,0);
  lcd.print("Distance:");
  lcd.setCursor(2,1);
  lcd.print(distance);
  delay(500);
  lcd.clear();
//  lcd.setCursor(0, 0);
 // lcd.print("Distance"); 
 // delayMicroseconds(2);
  
  
  digitalWrite(trigPin, HIGH);
  delayMicroseconds(10);
  digitalWrite(trigPin, LOW);
  
  duration = pulseIn(echoPin, HIGH);
  
  distance = duration * 0.034 / 2;
 
//  lcd.setCursor(0, 1);
//  lcd.print(distance); 


  if(distance < 25 && distance >= 15)
  {
    Serial.println("ALERT");

    digitalWrite(led0, HIGH);
    
    tone(buzzer, 500);
    delay(100);
    
    
    
  }
  else if(distance < 15 && distance >= 10){
    
     Serial.println("Danger aproaching rapidly!");
     
     digitalWrite(led0, HIGH);
     digitalWrite(led1, HIGH);
     
     tone(buzzer, 1500);
     delay(100);
  }
  else if(distance < 10 && distance >= 5){
    
     Serial.println("Imminent impact!!!");
     
     digitalWrite(led0, HIGH);
     digitalWrite(led1, HIGH);
     digitalWrite(led2, HIGH);
     
     tone(buzzer, 2000);
     delay(100);
      
  }else if(distance < 5){
    
    Serial.println("Intruder broke in! Engaging self-defense mechanism.");

    digitalWrite(led0, HIGH);
    digitalWrite(led1, HIGH);
    digitalWrite(led2, HIGH);
    digitalWrite(led3, HIGH);
    
    tone(buzzer, 2500); 
    delay(100);
    
  }
  if(distance > 25)
  {
    noTone(buzzer);   
     Serial.println("No danger detected.");  
  }
  
  Serial.print("Distance: ");
  Serial.print(distance);
  Serial.println(" cm");
  
  delay(500);
  
 // lcd.clear(); 
  
}
