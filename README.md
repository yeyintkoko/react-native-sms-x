# react-native-sms-x

A react-native API to send SMS messages. It works only on android. This component uses android native APIs to send SMS and provides a callback to react-native. This react-native library doesn't link to the mobile default messenger view. It will programmatically send the message in the background.

## Usage
```js
import SendSMS from 'react-native-sms-x';
// You can put any number as Id to identify which message is being processed
SendSMS.send(
  123, // <- Message ID
  "+959254687254", "Hey.., this is me!\nGood to see you. Have a nice day.", // <- Message Body
  (msgId, msg) => {
    console.log(`message ID: ${msgId}, message: ${msg}`);
  } // <- The callback which is called after sending the SMS
  // Output if successful -> "message ID: 123, message: SMS sent"
);
```

Response msg string will be one of the following:

+ "SMS sent"        - for successful message
+ "Generic failure" - for general failure
+ "No service"      - for no mobile operator service
+ "Radio off"       - for no mobile signal
+ "Null PDU"        - for no PDU


> Note: Minimum android version is `4.1` and supported `RN >= v0.29`.

## Installation
```
npm install react-native-sms-x --save
```

## **Android Setup**

1.In your `android/settings.gradle` file, make the following additions:

```gradle
include ':react-native-sms-x'
project(':react-native-sms-x').projectDir = new File(rootProject.projectDir, '../node_modules/react-native-sms-x/android/app')
```

2.In your `AndroidManifest.xml` file, add a user permission for sending SMS.

```xml
<uses-permission android:name="android.permission.SEND_SMS" />
```

> You shouldn't do the next steps for React Native >= v0.72.  
Or you will receive errors during compilation.

3.In your `android/app/build.gradle` file, add the ':react-native-sms-x' project as a compile-time dependency:

```gradle
...
dependencies {
    ...
    compile project(':react-native-sms-x')
}
```

4.Update the `MainApplication.java` file as follow:

```java
import com.facebook.react.ReactApplication;
...
import com.someone.sendsms.SendSMSPackage; // <--- add here!


public class MainApplication extends Application implements ReactApplication {

  private final ReactNativeHost mReactNativeHost = new ReactNativeHost(this) {
    @Override
    protected boolean getUseDeveloperSupport() {
      return BuildConfig.DEBUG;
    }

    @Override
    protected List<ReactPackage> getPackages() {
      return Arrays.<ReactPackage>asList(
          new MainReactPackage(),
          new SendSMSPackage()     // <--- add here!
      );
    }
  };

  @Override
  public ReactNativeHost getReactNativeHost() {
      return mReactNativeHost;
  }
}
```

---
**Example**

```js
import React, { Component } from 'react';
import {
  AppRegistry,
  StyleSheet,
  Text,
  View,
  TouchableOpacity,
  ToastAndroid
} from 'react-native';
import SendSMS from 'react-native-sms-x';

export default class RNSMS extends Component {
  sendSMSFunction() {
    SendSMS.send(123, "+95912345678", "Hey.., this is me!\nGood to see you. Have a nice day.",
      (msgId, msg)=>{
        ToastAndroid.show(msg, ToastAndroid.SHORT);
      }
    );
  }
  render() {
    return (
      <View style={styles.container}>        
        <TouchableOpacity style={styles.button} onPress={this.sendSMSFunction.bind(this)}>
          <Text>Send SMS</Text>
        </TouchableOpacity>
      </View>
    );
  }
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    justifyContent: 'center',
    alignItems: 'center',
    backgroundColor: '#F5FCFF',
  },  
  button: {
    padding: 10,
    borderWidth: .5,
    borderColor: '#bbb',
    margin: 10,
    alignItems: 'center',
    justifyContent: 'center'
  }
});

AppRegistry.registerComponent('RNSMS', () => RNSMS);
```

## Support on Beerpay
Hey dude! Help me out for a couple of :beers:!

[![Beerpay](https://beerpay.io/yeyintkoko/react-native-sms-x/badge.svg?style=beer-square)](https://beerpay.io/yeyintkoko/react-native-sms-x)  [![Beerpay](https://beerpay.io/yeyintkoko/react-native-sms-x/make-wish.svg?style=flat-square)](https://beerpay.io/yeyintkoko/react-native-sms-x?focus=wish)
