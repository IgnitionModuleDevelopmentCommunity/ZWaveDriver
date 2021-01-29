# Z-Wave Driver Module for Ignition
The Z-Wave Driver Module for Ignition allows you to observe and control connected Z-Wave devices. This project is an opensource Ignition Z-Wave driver supporting controller devices based on the Sigma Designs Serial API. If you have a device supporting this Serial API protocol most likely this driver will work for you. The driver adds a new tag provider to Ignition that automatically discovers all of the nodes and command classes in the network and generates tags.

This Z-Wave library has been tested on the following controllers:  

- [Aeon Labs Z-Stick Gen5](https://aeotec.com/z-wave-usb-stick/)

Most likely but not confirmed works also on:

- Vision USB stick Z-Wave
- Z-Wave.me Z-StickC
- Sigma UZB ZWave-Plus

## What is Z-Wave?
Z-Wave is a wireless communication protocol designed for home automation. It uses a low power, and low bandwidth, mesh network that allows devices that aren’t within direct range of each other to communicate indirectly, via other nodes. Any device that’s permanently powered (not battery powered) will help build the mesh, if you don’t have enough powered devices, or you locate these poorly, your mesh will be unreliable.

## Requirements
You will need the following to use this module:

 - Z-Wave controller
 - One or more Z-Wave devices that are paired to the controller
 - Ignition v.8.0.5+

## Getting started
You will need to first install the ZWaveDriver-unsigned.modl into Ignition. The module is unsigned and requires that Ignition is in developer mode or setup to allow installation of unsigned modules. You can simply add a switch to your ignition.conf file to allow installation of unsigned modules. Open your ignition.conf configuration file:

Windows  
`C:\Program Files\Inductive Automation\Ignition\data\ignition.conf`

Linux  
`\var\lib\ignition\data\ignition.conf`

Add an additonal Java parameter in the 'Java Additional Parameters' section below the first parameter:

`wrapper.java.additional.2=-Dignition.allowunsignedmodules=true`

If you cannot load unsigned modules, you will have to create a certificate and sign the module on your own. You can follow instructions on [Inductive Automation's GitHub page](https://github.com/inductiveautomation/module-signer).

Once installed and running, you will need to configure the Z-Wave Driver Module by setting the port to the USB controller and the network key if using secure Z-Wave devices. You can do that by going to the Z-Wave -> Settings in the Gateway Configuration page.

### Port
The port where your device is connected on your Ignition server. Z-Wave sticks will generally be /dev/ttyACM0 and GPIO hats will generally be /dev/ttyAMA0.

Windows  
`COM3`

Linux  
`/dev/ttyACM0`

### Network Key
Security Z-Wave devices require a network key. Some devices only expose their full capabilities when included this way. You should always read the manual for your device to find out the recommended inclusion method. Note, secure devices that had been connected to another hub/network in the past may have a 'theft protection' feature which requires to first exclude the device successfully from the previous hub using the previous hub/Software setup before it can be enrolled in a new hub/network.

A valid network key will be a 16 byte value, defined in the zwave section of your configuration, such as the following example:

`0x01, 0x02, 0x03, 0x04, 0x05, 0x06, 0x07, 0x08, 0x09, 0x0A, 0x0B, 0x0C, 0x0D, 0x0E, 0x0F, 0x10`

Each individual value in the defined key can be anywhere from 0x00 to 0xFF. Define your own key by making changes to the above example key.

## Adding and Removing Z-Wave Devices
Once the driver is installed and configured, the next step is add your Z-Wave devices to your hub. If you have already paired Z-Wave devices with your hub, the driver should automatically pick them up. 

### Add Device
To add a Z-Wave device:
1. Open your Ignition Designer
2. Select the ZWave tag provider in the Tag Browser dropdown list. The provider is found under All Providers on older versions of Ignition.
3. Expand the Homes folder to find your home identifier. If you do not see a home, please restart the Z-Wave module.
4. If you have a secure device, set the AddSecureNode tag to true to put your hub in inclusion mode. If you have a non-secure device, set the AddNode tag to true. 
5. Follow your devices instructions to activate inclusion. Typically this involves pressing a button.
6. The module should automatically pick up the device and tags will get added to your home nodes folder, identified by the device number starting 01. **This process may take some time. You can watch the progress by looking at the QueueSize tag. Once it reaches 0 it is complete.**
7. If the process fails, try removing the device and trying again.
8. Follow the same procedure to add more devices.

### Remove Device
To remove a Z-Wave device:
1. Open your Ignition Designer
2. Select the ZWave tag provider in the Tag Browser dropdown list. The provider is found under All Providers on older versions of Ignition.
3. Expand the Homes folder to find your home identifier. If you do not see a home, please restart the Z-Wave module.
4. Set the RemoveNode tag to true to put your hub in exlusion mode.
5. Follow your devices instructions to activate exclusion. Typically this involves pressing a button.
6. The module should automatically remove the device from your home nodes folder.
7. Follow the same procedure to remove more devices.

## Status
This driver is still under active development. Here are the messages currently supported:

- Memory Get ID
- Get Version
- Get Init Data
- Application Node Info
- Identify Node
- Get Routing Info
- No Operation
- Request Node Info
- Is Failed Node
- Application Update
- Application Command
- Add Node (insecure and secure)
- Remove Node
- Request Node Neighbor Update
- Delete Return Route
- Assign Return Route
- Remove Failed Node
- Replace Failed Node
- Send Date (used with command classes below)

Here are the command classes that are currently supported:

- Basic
- Application Status
- Switch Binary
- Multi-Level Switch
- Sensor Binary
- Multi-Level Sensor
- Meter
- Color Switch
- Thermostat Mode
- Thermostat Operating State
- Thermostat Setpoint
- Thermostat Fan Mode
- Thermostat Fan State
- Device Reset
- Z-Wave Plus Info
- Multi-instance
- Door Lock
- User Code
- Barrier Operator
- Configuration
- Notification
- Manufacturer Specific
- Power Level
- Protection
- Battery
- Clock
- Wakeup
- Association
- Version
- Security0
