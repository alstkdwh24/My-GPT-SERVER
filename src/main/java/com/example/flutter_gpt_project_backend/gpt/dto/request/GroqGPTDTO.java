package com.example.flutter_gpt_project_backend.gpt.dto.request;

import java.util.List;

public class GroqGPTDTO {
    private List<Message>   messages;

    public static class Message {
        private String role;
        private String content;

        public Message() {}

        public Message(String role, String content) {
            this.role = role;
            this.content = content;
        }

        public String getRole() {
            return role;
        }

        public void setRole(String role) {
            this.role = role;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

    }
        public GroqGPTDTO() {}
        public GroqGPTDTO(List<Message> messages) {
            this.messages = messages;
        }
        public List<Message> getMessages() {
            return messages;
        }
        public void setMessages(List<Message> messages) {
            this.messages = messages;
        }


}
