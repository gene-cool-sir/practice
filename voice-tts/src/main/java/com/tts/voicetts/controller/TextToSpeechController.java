package com.tts.voicetts.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.sun.speech.freetts.Voice;
import com.sun.speech.freetts.VoiceManager;
import com.sun.speech.freetts.audio.AudioPlayer;
import com.sun.speech.freetts.audio.JavaStreamingAudioPlayer;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;

@Controller
public class TextToSpeechController {

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @PostMapping("/synthesize")
    public String synthesize(@RequestParam("text") String text, Model model) {
        try {
            // 初始化FreeTTS语音引擎
            VoiceManager voiceManager = VoiceManager.getInstance();
            Voice voice = voiceManager.getVoice("kevin16");
            voice.allocate();

            // 合成语音
            AudioPlayer audioPlayer = new JavaStreamingAudioPlayer();
            voice.setAudioPlayer(audioPlayer);
            voice.speak(text);
            audioPlayer.close();

            // 生成语音文件URL
            String audioUrl = "data:audio/wav;base64," + Arrays.toString(audioPlayer.toString().getBytes(StandardCharsets.UTF_8));

            model.addAttribute("audioUrl", audioUrl);

            // 关闭语音引擎
            voice.deallocate();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "index";
    }
}
