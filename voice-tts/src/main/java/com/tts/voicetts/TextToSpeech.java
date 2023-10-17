package com.tts.voicetts;

import com.sun.speech.freetts.Voice;
import com.sun.speech.freetts.VoiceManager;

public class TextToSpeech {

    public static void main(String[] args) {
        // 初始化FreeTTS语音引擎
        VoiceManager voiceManager = VoiceManager.getInstance();
        Voice voice = voiceManager.getVoice("alan"); // 使用Kevin16音色

        if (voice == null) {
            System.err.println("找不到指定的语音。");
            System.exit(1);
        }

        voice.allocate();
        
        // 要转换为语音的文本
        String textToSpeak = "你好，这是一个语音播报示例。";

        // 播报文本
        voice.speak(textToSpeak);

        // 关闭语音引擎
        voice.deallocate();
    }
}
