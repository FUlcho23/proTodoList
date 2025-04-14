package org.big.service;

import java.util.List;

import org.big.dto.TodoDto;

public interface CalendarService {
	 List<TodoDto> selectTodo(String tdWorkM);
	 void addTodo(TodoDto todo) throws Exception;
	 void deleteTodo(String tdId) throws Exception;
	 void updateTodo(TodoDto todo) throws Exception;
	 String getTeamName(String tdWorkM) throws Exception;
	 void updateStatus(List<Integer> ids, String status);
	 void updateHidden(List<Integer> ids, int hidden);
	 List<TodoDto> selectTodoByTeam(String memberId);
}
