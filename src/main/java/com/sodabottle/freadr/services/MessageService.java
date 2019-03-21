package com.sodabottle.freadr.services;

import com.sodabottle.freadr.request.MessageRequest;

public interface MessageService {

	void sendMessage(MessageRequest messageRequest);
	
}
