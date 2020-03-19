package com.dogo.chat;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MessageController {
	
	@Autowired
	MessageRepository dao;
	
	@RequestMapping
	public String chat() {
		return "chat";
	}
	
	@GetMapping("/chat")
	public List<Message> getMessages() {
		List<Message> foundMessages = dao.findAll();
		return foundMessages;
	}
	
	@PostMapping("/chat")
	public ResponseEntity<Message> postMessage(@RequestBody Message message) {
		Message createdMessage = dao.save(message);
		return ResponseEntity.ok(createdMessage);
	}
	
	@GetMapping("/chat/{id}")
	public ResponseEntity<Message> getMessage(@PathVariable(value="id") Integer id) {
		Message foundMessage = dao.findById(id).orElse(null);
		
		if(foundMessage == null) {
			return ResponseEntity.notFound().header("Message", "Nothing found with that id").build();
		}
		return ResponseEntity.ok(foundMessage);
		
	}
	
	@DeleteMapping("/chat/{id}")
	public ResponseEntity<Message> deleteMessage(@PathVariable(value="id") Integer id) {
		Message foundMessage = dao.findById(id).orElse(null);
		
		if(foundMessage == null) {
			return ResponseEntity.notFound().header("Message", "Nothing found with that id").build();
		} else {
			dao.delete(foundMessage);
		}
		
		return ResponseEntity.ok().build();
	}
	
	@PutMapping("/chat/{id}")
	public ResponseEntity<Message> updateMessage(@PathVariable(value="id") Integer id, @RequestBody Message message) {
		Message foundMessage = dao.findById(id).orElse(null);
		
		if(foundMessage == null) {
			return ResponseEntity.notFound().header("Message", "Nothing found with that id").build();
		} else {
			if(message.getName() != null) {
				foundMessage.setName(message.getName());
			}
			if(message.getContent() != null) {
				foundMessage.setContent(message.getContent());
			}
			dao.save(foundMessage);
		}
		return ResponseEntity.ok(foundMessage);
	}
	
}
