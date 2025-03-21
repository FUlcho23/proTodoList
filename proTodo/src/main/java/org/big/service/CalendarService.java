package org.big.service;

import java.util.List;

import org.big.dto.TodoDto;

public interface CalendarService {
	 List<TodoDto> selectTodo() throws Exception;
	 void addTodo(TodoDto todo) throws Exception;
	 void deleteTodo(String tdId) throws Exception;
	 void updateTodo(TodoDto todo) throws Exception;
}
