## react-native-sms-x
***
**SendSMS**

> ##### A react-native api to send SMS messages. It works only for android. This component used android native api to send sms and response a callback to react-native. This react-native component doesn't link to the mobile default messenger view. It will programmatically send the message to a phone number which is provided as a parameter and will response a callback with a string indicating message was sent or failure or no service.

*usage*
```js
import SendSMS from 'react-native-sms-x';
// you can put any number as Id to identify which message being process
SendSMS.send(123, "+959254687254", "Hey.., this is me!\nGood to see you. Have a nice day.", (msg)=>{ alert(msg) });
```

Response msg string will be one of the following:

+ "SMS sent"        - for successful message
+ "Generic failure" - for general failure
+ "No service"      - for no mobile operator service
+ "Radio off"       - for no mobile signal
+ "Null PDU"        - for no PDU

###### *Note:*

###### Minimum android version is `4.1` and supported `RN >= v0.29`.
---
#### Installation
```
npm install react-native-sms-x --save
```
---
##### **Android Setup**

1.In your `android/settings.gradle` file, make the following additions:

```
include ':react-native-sms-x'
project(':react-native-sms-x').projectDir = new File(rootProject.projectDir, '../node_modules/react-native-sms-x/android/app')
```

2.In your `android/app/build.gradle` file, add the ':react-native-sms-x' project as a compile-time dependency:

```
...
dependencies {
    ...
    compile project(':react-native-sms-x')
}
```

3.Update the `MainApplication.java` file as follow:

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

4.In your `AndroidManifest.xml` file, add a user permission for sending SMS.

```
<uses-permission android:name="android.permission.SEND_SMS" />
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
      (msg)=>{
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
