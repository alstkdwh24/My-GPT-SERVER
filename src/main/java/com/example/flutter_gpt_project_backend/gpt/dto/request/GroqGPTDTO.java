package com.example.flutter_gpt_project_backend.gpt.dto.request;

import java.util.List;

public class GroqGPTDTO {

    public GroqGPTDTO() {}


    private List<Message>   messages;

    public List<Message> getMessages() {
        return messages;
    }
    public void setMessages(List<Message> messages) {
        this.messages = messages;


    }

    public static class Message {



        private String message;
        private String role;
        private String content;


        public String getRole(){
            return role;
        }
        public void setRole(String role){
            this.role = role;
        }

        public String getMessage() {
            return message;

        }

        public void setMessage(String message) {
            this.message = message;
        }


        public String getContent() {
            return content;
        }
        public void setContent(String content) {
            this.content = content;
        }
    }
}
