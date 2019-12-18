import React from 'react';
import {AppRegistry, StyleSheet, Text, View, TouchableOpacity} from 'react-native';
import { NativeModules } from 'react-native';

class HelloWorld extends React.Component {
  render() {
    console.log(NativeModules)
    console.log(NativeModules.RNInteropModule)
    return (
      <View style={styles.container}>
        <Text style={styles.hello}>Cock burgers</Text>
        <TouchableOpacity onPress={() => {
          NativeModules.RNInteropModule.configureAmiga();
        }}>
          <Text style={styles.hello}>Configure Amiga</Text>
        </TouchableOpacity>
        <TouchableOpacity onPress={() => {
          NativeModules.RNInteropModule.startAmiga();
        }}>
          <Text style={styles.hello}>Start Amiga</Text>
        </TouchableOpacity>
      </View>
    );
  }
}
var styles = StyleSheet.create({
  container: {
    flex: 1,
    justifyContent: 'center',
  },
  hello: {
    fontSize: 20,
    textAlign: 'center',
    margin: 10,
  },
});

AppRegistry.registerComponent('MyReactNativeApp', () => HelloWorld);