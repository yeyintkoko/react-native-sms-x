import { NativeModules } from 'react-native';

interface SendSMS {
  send(
    id: number,
    phoneNumber: string,
    message: string,
    callback: (
      messageId: number,
      message: 'SMS sent' | 'Generic failure' | 'No service' | 'Radio off' | 'Null PDU'
    ) => void
  ): void;
}

export default NativeModules.SendSMS as SendSMS;
