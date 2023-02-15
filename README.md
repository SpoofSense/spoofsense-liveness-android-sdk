# SpoofSense


Tag: [![Release](https://jitpack.io/v/SpoofSense/spoofsense-liveness-android-sdk.svg)](https://jitpack.io/#SpoofSense/spoofsense-liveness-android-sdk)


## Initialization

Add it in your root build.gradle at the end of repositories:

```
allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
```
Add the dependency:
```
	dependencies {
	         implementation 'com.github.SpoofSense:spoofsense-liveness-android-sdk:Tag'
	}
```

## What SpoofSense does?
SpoofSense check the liveness of the scanned face and returns you with a simple resultCallBack.
Guidelines to follow, while scanning your face: 
 - Ample lighting on the face.
 - No face tilt.
 - No multiple face detection.


## SpoofSense library Components:
This flow consist of mainly 3 screens:

```
1 Splash screen
2 Guideline screen
3 Camera screen
```
respectively shown in the below image.

![image](https://user-images.githubusercontent.com/104752632/218302185-0fc9f765-41df-4e6f-853b-34b6f2d0e5c8.jpg)

## Setup

### API Key
Set Api key before launching the flow `Note: API Key is Mandatory.`

```kotlin
SpoofSense.apiKey = "Set_Your_Api_Key"
```

### Button properties
This will set the button background color for the entire flow. 

```kotlin
SpoofSense.buttonTitleColor = Color.BLACK
SpoofSense.buttonBackgroundColor = Color.RED
```

## Splash Screen

### Set Splash Screen Visibility
You can set the splash screen visibility by simply turning flag true/false. Default is True.

```kotlin
SpoofSense.showSplashScreen = true
```

### Set button text title
```kotlin
SpoofSense.splashButtonTextTitle = "Check Liveness"
```

### Set logo image
Supported Size: 40 * 40

```kotlin
SpoofSense.appLogo = ContextCompat.getDrawable(context, R.drawable.ic_logo)
```

### Set App name
We have provided support for App First Name and Last Name. You can set Empty String if you want. Also you can set different color for first/last name. 

```kotlin
SpoofSense.appFirstName = "YourAppFirstName"
SpoofSense.appFirstNameColor = Color.RED
```

```kotlin
SpoofSense.appLastName = "YourAppLastName"
SpoofSense.appLastNameColor = Color.GREEN
```

### Set Version Number
You can set Empty String if you want. Also you can set version number string color. 

```kotlin
SpoofSense.versionNumberString = ""
SpoofSense.versionNumberColor = Color.Black
```

## Guidelines Screen

### Set Guidelines Screen Visibility
You can set the guidelines screen visibility by simply turning flag true/false. Default is True.

```kotlin
SpoofSense.showFaceGuidelinesScreen = true
```

### Set button text title
```kotlin
SpoofSense.guidelinesButtonTextTitle = "Check Liveness"
```

## Launch SpoofSense

```kotlin
SpoofSense.launch(context) { resultJson->
    //result json example -> {"message":"Liveness Confirmed",status:"true"}
}
```

## License

SpoofSense is released under the MIT license. [See LICENSE](http://www.opensource.org/licenses/MIT) for details.
