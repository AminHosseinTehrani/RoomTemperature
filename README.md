**<p align="center">Arduino Schematic</p>**

                                          
                                          
                                          
                     


  <p align="center">   <img width="460" height="500" src="https://github.com/AminHosseinTehrani/RoomTemperature/assets/112990793/67697379-f9e2-444c-9c62-092e2ebe0c09" 
</p>



                                   
                                  
  ```   const int sensorPin = A0;
char inputByte;

void setup() {
  

Serial.begin(9600);




}

void loop() {

int sensorVal = analogRead(sensorPin);

//Serial.print("Sensor Value: ");
//Serial.print(sensorVal);

float voltage = (sensorVal/1024.0) * 5.0;
//Serial.print(", Volts: "); Can use serials to test on Ardiuno IDE
//Serial.print(voltage);

//Serial.print(", degress C: ");


float temperature = (voltage - .5) * 100;
Serial.println(temperature);

delay(3000);



}

