package com.quangnv.uet.listener;

import org.springframework.hateoas.server.EntityLinks;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class EventHanler {

	private SimpMessagingTemplate simpMessagingTemplate;
	private EntityLinks entityLinks;
	
}
