# Future Generation Surveillance Camera

---

Project for TartanHacks 2016 at Carnegie Mellon Unviersity. 
The Future Generation Surveillance Camera is an RC car controlled by a mobile device via WiFi and supports real-time video streaming. 

#### Team Members
* Kyuin Lee (kyuinl)
* Alice Park (seongyeo)
* Won Chung (wonchung)
* Jiyeon Hwang (jhwang1)

---
---

## INSTRUCTIONS

1. Set up for Raspberry Pi
2. Set up for Android application

---

### 1. Set up for Raspberry Pi 

1. Download Raspbian OS on your Raspberry Pi
2. Install Pi4J
3. Download OscP5 library (.jar file) to your Raspberry Pi. 

### 2. Set up for Android application

0. Install Processing on your laptop if you do not have it. Import ketai and oscP5 libraries to your Processing.
1. Find your Raspberry Pi's IP address. Replace it in ip string variable in RC controller app/hackathon.pde
2. Click File -> Export Signed Package (Make sure you're in Android mode in Processing) to build .apk file.
3. Download the .apk file to your Android mobile device.
