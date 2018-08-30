## CyberBiology

CyberBiology - life simulator on computer

## Requirements
* Install Java
```bash
sudo apt-get install \
    openjdk-8-jdk \
    openjdk-8-jre
```

* Install Grandle
```bash
sudo mkdir /opt/gradle

cd /opt/gradle

sudo wget https://services.gradle.org/distributions/gradle-4.9-bin.zip

sudo unzip gradle-4.9-bin.zip

sudo rm gradle-4.9-bin.zip

export PATH=$PATH:/opt/gradle/gradle-4.9/bin

chmod +x ./gradlew
```

## Build and Run

```
./gradlew build

./gradlew run
```