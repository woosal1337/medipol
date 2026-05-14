// variables
int GREEN1 = 4;
int YELLOW1 = 3;
int RED1 = 2;
int GREEN2 = 5;
int YELLOW2 = 6;
int RED2 = 7;

int INPUT_0;
int INPUT_1;
int INPUT_2;
int BINARY;
int TURN = 0;
int check = 0;

void setup()
{
  Serial.begin(9600);
  pinMode(GREEN1, OUTPUT); //2
  pinMode(YELLOW1, OUTPUT); //3
  pinMode(RED1, OUTPUT); //4
  pinMode(RED2, OUTPUT); //7
  pinMode(YELLOW2, OUTPUT); //6
  pinMode(GREEN2, OUTPUT); //5
  digitalWrite(GREEN1, LOW);
  digitalWrite(YELLOW1, LOW);
  digitalWrite(RED1, LOW);
  digitalWrite(YELLOW2, LOW);
  digitalWrite(GREEN2, LOW);
  digitalWrite(RED2, LOW);

  pinMode(8, INPUT); // INPUT 0
  pinMode(9, INPUT); // INPUT 1
  pinMode(10, INPUT); // INPUT 2
}

void loop() {
  delay(1000);
  INPUT_0 = digitalRead(8);
  INPUT_1 = digitalRead(9);
  INPUT_2 = digitalRead(10);
  BINARY = (INPUT_0) + (INPUT_1 * 2) + (INPUT_2 * 4);
  if (BINARY == 0) {
    TURN += 1;
    delay(500);
  }
  Serial.print("Turn=");
  Serial.println(TURN);
  check = TURN % 2;
  Serial.print("Check=");
  Serial.println(check);
  if (check == 0) {
        if (BINARY >= 4) {
          Serial.print("State=");
          Serial.println("1");
          digitalWrite(YELLOW1, LOW);
          digitalWrite(RED1, LOW);
          digitalWrite(YELLOW2, LOW);
          digitalWrite(GREEN2, LOW);
          digitalWrite(GREEN1, HIGH);
          digitalWrite(RED2, HIGH);     
          
    
        } else{
          Serial.print("State=");
          Serial.println("2");
          digitalWrite(GREEN1, LOW);
          digitalWrite(RED1, LOW);
		  digitalWrite(GREEN2, LOW);
          digitalWrite(YELLOW1, HIGH);
          digitalWrite(YELLOW2, HIGH);
          digitalWrite(RED2, HIGH);
          
        }
  } else {
        if (BINARY >= 4) {
          Serial.print("State=");
          Serial.println("3");
          digitalWrite(RED2, LOW);
          digitalWrite(GREEN1, LOW);
          digitalWrite(YELLOW1, LOW);
          digitalWrite(YELLOW2, LOW);
          digitalWrite(GREEN2, HIGH);
		  digitalWrite(RED1, HIGH);
          
        }
        else{
          Serial.print("State=");
          Serial.println("4");
          digitalWrite(GREEN1, LOW);
          digitalWrite(GREEN2, LOW);
		  digitalWrite(RED2, LOW);
          digitalWrite(YELLOW1, HIGH);
          digitalWrite(RED1, HIGH);
          digitalWrite(YELLOW2, HIGH);
          
        }
  }
}
